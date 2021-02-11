package com.notification.system.notificationredisservice.scheduler;

import com.notification.system.notificationredisservice.repository.NotificationRepository;
import com.notification.system.notificationredisservice.repository.UserCacheRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Component
public class NotificationCacheInvalidateScheduler {

    @Value("${history.time.in-hours}")
    private Integer hours;
    private final UserCacheRepository userCacheRepository;
    private final NotificationRepository notificationRepository;
    private final ExecutorService executorService;

    public NotificationCacheInvalidateScheduler(UserCacheRepository userCacheRepository, NotificationRepository notificationRepository, ExecutorService executorService) {
        this.userCacheRepository = userCacheRepository;
        this.notificationRepository = notificationRepository;
        this.executorService = executorService;
    }

    @Scheduled(cron = "${notification.cache.invalidate}")
    void invalidateNotificationCache() {
        Map<String, Long> userLogins = this.userCacheRepository.getAllUsersDetails();
        Map<String, Long> userLoginToDelete = userLogins.entrySet().stream()
                .filter(userLogin -> validateLastLoginTime(userLogin.getValue(), hours))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        userLoginToDelete.keySet()
                .forEach(this::submitUserToDelete);
    }

    private void submitUserToDelete(String userId) {
        executorService.submit(() -> deleteUserDetail(userId));
    }

    private void deleteUserDetail(String userId) {
        this.userCacheRepository.deleteUserLoginDetail(userId);
        this.notificationRepository.deleteAllNotificationsForUser(userId);
    }

    private boolean validateLastLoginTime(Long value, Integer days) {
        return (System.currentTimeMillis() - hours * 60 * 60 * 1000) > value;
    }

}
