package com.notification.system.notificationservice.repository.impl;

import com.notification.system.notificationservice.model.NotificationModel;
import com.notification.system.notificationservice.repository.NotificationModelRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class NotificationModelRepositoryCustomImpl implements NotificationModelRepositoryCustom {

    private final JdbcTemplate jdbcTemplate;

    private static final String TABLE_NAME = "USER_NOTIFICATIONS";

    @Override
    public void saveAllNotifications(String userId, List<NotificationModel> notificationModels, Boolean isUserLogin) {
        try {
            this.jdbcTemplate.batchUpdate("INSERT INTO " + TABLE_NAME + " VALUES(?,?,?,?,?,?)",
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            NotificationModel notificationModel = notificationModels.get(i);
                            ps.setString(1, notificationModel.getId());
                            ps.setString(2, userId);
                            ps.setLong(3, notificationModel.getTimestamp());
                            ps.setString(4, notificationModel.getMessage());
                            ps.setString(5, notificationModel.getData());
                            ps.setBoolean(6, isUserLogin);
                        }

                        @Override
                        public int getBatchSize() {
                            return notificationModels.size();
                        }
                    });
        } catch (Exception e) {
            LOGGER.error("Unable to insert notifications for user" + userId + ", " + e.getMessage(), e);
        }
    }

    @Override
    public List<NotificationModel> getAllNotificationsForUser(String userId) {
        try {
            return this.jdbcTemplate.query("SELECT ID, USERID, DATA, MESSAGE, TIMESTAMP FROM " + TABLE_NAME + " WHERE USERID = '" + userId + "' AND SEEN = false", (rs, rowNum) -> NotificationModel
                    .builder()
                    .id(rs.getString("id"))
                    .data(rs.getString("data"))
                    .message(rs.getString("message"))
                    .timestamp(rs.getLong("timestamp"))
                    .userId(rs.getString("userid"))
                    .build()
            );
        } catch (Exception e) {
            LOGGER.error("Unable to fetch user notification for userid {}, {}", userId, e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    @Override
    public void updateNotificationFlag(List<String> notificationId) {
        try {
            this.jdbcTemplate.batchUpdate("UPDATE " + TABLE_NAME + " SEEN=? WHERE ID=?",
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            ps.setBoolean(1, true);
                            ps.setString(2, notificationId.get(i));
                        }

                        @Override
                        public int getBatchSize() {
                            return notificationId.size();
                        }
                    });
        } catch (Exception e) {
            LOGGER.error("Unable to update notifications flag for ids" + notificationId + ", " + e.getMessage(), e);
        }
    }

    @Override
    public void updateNotificationFlag(String userId) {
        try {
            this.jdbcTemplate.update("UPDATE " + TABLE_NAME + " SET SEEN=? WHERE USERID=? AND SEEN=?", true, userId, false);
        } catch (Exception e) {
            LOGGER.error("Unable to update notifications flag for userid" + userId + ", " + e.getMessage(), e);
        }
    }
}
