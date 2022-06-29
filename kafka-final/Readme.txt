https://kafka.apache.org/downloads
https://data-flair.training/blogs/kafka-architecture/


to start zookeeper:
zookeeper-server-start.bat config\zookeeper.properties

to start kafka:
kafka-server-start.bat config\server.properties


kafka-topics --create --topic my_topic --bootstrap-server localhost:9092 --replication-factor 1 --partitions 3