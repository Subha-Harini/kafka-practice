
https://data-flair.training/blogs/kafka-architecture/


Installation:
JAVA 8  & above - required
Kafka official site:
https://kafka.apache.org/downloads
unzip the file in c folder and try opening kafka-topics.bat file through command prompt to check if installation is scuccessful

create a folder called data in kafka folder and create zookeeper and kafka folder inside that which hold topic and kafka brokers related information.

Please have a look into server.properties file and zookeeper properties file which is added in this repo and check if all the configurations look similar.

to start zookeeper:
zookeeper-server-start.bat config\zookeeper.properties

to start kafka:
kafka-server-start.bat config\server.properties

to create a topic:
kafka-topics --create --topic my_topic --bootstrap-server localhost:9092 --replication-factor 1 --partitions 3
