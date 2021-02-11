# Notification System Design

## Design Diagram
![Design Diagram](/images/NotificationSystem%20Architecture.png)

## TechStack
1. Springboot v2.4.2
2. Java 15
3. Redis
4. Postgres DB
5. Apache Kafka v2.7.0

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

## To create kafka-producer, Open new terminal and run following command
```
./bin/windows/kafka-console-producer.bat --topic notification-topic --bootstrap-server localhost:9092
```

## To start the application
```
1. git clone git@github.com:NirakhRastogi/Springboot-Sleuth-Zipkin.git
2. cd Springboot-Sleuth-Zipkin
3. Run all the applications
```

# Endpoints
## Create Order
```
POST/ http://localhost:8080/order/

Body - 
{
    "customerName": "TestCustomer",
    "deliveryAddress": "TestAddress",
    "deliveryPhoneNumber": 8899887788,
    "orderStatus": "PLACED",
    "paymentStatus": "INCOMPLETE",
    "items": [
        {
            "id": "yu7yuyg",
            "name": "Item1",
            "price": 78.5,
            "quantity": 7
        }
    ]
}
```

## Track Order
```
GET/ http://localhost:8082/order-tracking/{orderId}
```

## Change Order Status
```
GET/ http://localhost:8080/{orderId}/status/{status}
```

## Change Payment Status
```
GET/ http://localhost:8080/{orderId}/payment/status/{status}
```