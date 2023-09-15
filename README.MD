## To Create UserProfile

curl --location --request POST 'http://localhost:8081/api/v1/user' \
--header 'Content-Type: application/json' \
--data-raw '{
"name":"mayur",
"age": 20,
"email": "mayur22lakhani33@gmail.com"
}'

## To create /user/visit api
curl --location --request POST 'http://localhost:8081/api/v1/user/visit?userId=1&visitorId=2' \
--data ''

## To create /user/like api
curl --location --request POST 'http://localhost:8081/api/v1/user/like?userId=1&visitorId=2' \
--data ''

## To get Visiotrs of the user /user/visitors
curl --location --request GET 'http://localhost:8081/api/v1/user/visitors?userId=2'

## Actuator endpoint
curl --location --request GET 'http://localhost:8081/actuator/health'



## Start Apache Kafka
1. Start zookeeper service: 
   .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
2. Start apache kafka server:
   .\bin\windows\kafka-server-start.bat .\config\server.properties
3. Create topic:
   .\bin\windows\kafka-topics.bat --create --topic userprofile --bootstrap-server localhost:9092
4. produce events:
   .\bin\windows\kafka-console-producer.bat --topic userprofile --bootstrap-server localhost:9092
5. consume events:
   .\bin\windows\kafka-console-consumer.bat --topic userprofile --from-beginning --bootstrap-server localhost:9092

## Development: Proposed Architecture version 1.0
![Proposed Architecture.png](Proposed%20Architecture.png)

## Future Aspects
![Future Aspects.png](Future%20Aspects.png)


