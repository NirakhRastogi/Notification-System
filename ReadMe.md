# Notification System Design

## Design Diagram
![Design Diagram](/images/NotificationSystem%20Architecture.png)

## TechStack
1. Springboot v2.4.2
2. Java 15
3. Redis
4. Postgres DB
5. Apache Kafka v2.7.0
6. Server Sent Events

## Read More About,
1. Springboot sleuth - https://spring.io/projects/spring-cloud-sleuth
2. Java 15 - https://openjdk.java.net/projects/jdk/15/
3. Redis - https://redis.io/
4. Kafka - https://kafka.apache.org/
5. Postgres - https://www.postgresql.org/
   
## Project Setup

## Kafka Download
```
1. Download kafka(2.7.0) from following link - https://kafka.apache.org/downloads
2. Extract the kafka zip from downloads
```
## To start kafka and zookeeper
```
1. Go to kafka-${version} directory
2. Run following command
    .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
3. Open new terminal and run command to start kafka-server
     .\bin\windows\kafka-server-start.bat .\config\server.properties
```

## To create kafka-topic, Open new terminal and run following command
```
./bin/windows/kafka-topics.bat --create --topic notification-topic --bootstrap-server localhost:9092
```

## To start kafka-producer, Open new terminal and run following command
```
.\bin\windows\kafka-console-producer.bat --topic notification-topic --bootstrap-server localhost:9092 --property "parse.key=true" --property "key.separator=:"
```

## Redis Download
```
1. Download redis from following link - https://redis.io/download
2. Extract the redis and set the path to Environment variables
3. Open the terminal and run - redis-server
```

## To start the application
```
1. git@github.com:NirakhRastogi/Notification-System.git
2. cd Notification-System
3. Run all the applications
```

# Endpoints
## To register a user
```
GET http://localhost:8080/notification-service/user/register/${userId}
```
This will start the event stream with notification service, so all the notifications will come to this particular user in real time.

## To publish notification publish following message
### To send notification only, (userId is user1)
```
user1:[{"userId":"user1","message":"This is test notification","data":"Test Notification"}]
```
### To send notification with mail, (userId is user1)
```
user1:[{"userId":"user1","message":"This is test notification","data":"Test Notification","mail":{"from":"TestUser <test@gmail.com>","cc":["testo@gmail.com"],"bcc":["testo@gmail.com"],"to":["testo@gmail.com"],"subject":"This is an test email","note":"Important Links ahaed","body":"<h1>Hi</h1><h2>Hello</h2>"}}]
```