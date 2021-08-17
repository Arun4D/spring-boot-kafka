# Read Me First

# Getting Started

This project help to understand the kafka producer and consumer.

OrderController api to publish data to kafka topic and consumer service to consume kafka topic.  

Change datasource based on your database usage

````
spring.datasource.url=jdbc:postgresql://localhost:5432/test
spring.datasource.username=postgres
spring.datasource.password=postgres
````
Create kafka topic based on needs.

https://kafka.apache.org/quickstart


````
study.kafka.bootstrap-servers=localhost:9092
study.kafka.topic.consumer=ad-study-consumer-topic
study.kafka.topic.producer=ad-study-producer-topic
study.kafka.topic.group=ad-event-group
````

### Build
````
gradle clean build
````
### Ready to Docker
````
 mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)
````
###  Docker Build - Command Line
````
docker build --build-arg DEPENDENCY=build/dependency -t arun4d/spring-boot-kafka .
````
###  Docker Build - Gradle
````
$ ./gradlew bootBuildImage --imageName=arun4d/spring-boot-kafka
````

###  Post Sample Curl Request
````
curl -X POST -d '{
    "key":"1",
    "Data": {
       "itemId":"11",
       "customerId":"111"
    },
    "coreEventMetadata": {
        "eventId":"E1",
         "eventName" : "EV1",
         "eventGroup" :"EG1",
         "isBusinessProcess" : true
    }
' -H 'Content-Type:application/json' http://localhost:8080/study/event/publish/
````