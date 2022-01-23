# kafka-learning

#Before you run this service don't forget for run kafka service on your pc

#1.RUN ZOO KEEPER : 
$ bin/zookeeper-server-start.sh config/zookeeper.properties

#2. RUN KAFKA SERVER : 
$ bin/kafka-server-start.sh config/server.properties

#3. CREATE TOPIC : 
$ bin/kafka-topics.sh --create --partitions 1 --replication-factor 1 --topic quickstart-events --bootstrap-server localhost:9092

#4. RUN this ms : 
mvn spring-boot:run

#documentation :
https://kafka.apache.org/quickstart


-Kafka Avro
Documentation : https://www.confluent.io/installation 
Start confluent service : confluent local services start
Stop confluent service : confluent local services stop

