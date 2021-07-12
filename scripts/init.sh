#!/bin/sh
cd ../
PGPASSWORD=themonksofteamb1 psql -h localhost -U teamb1 -d offline_unsupervised_recommendation_generator_db -c 'DROP TABLE IF EXISTS feature_vector_one;'
./binaries/kafka_2.13-2.8.0/bin/zookeeper-server-start.sh ./binaries/kafka_2.13-2.8.0/config/zookeeper.properties 
./binaries/kafka_2.13-2.8.0/bin/kafka-server-start.sh ./binaries/kafka_2.13-2.8.0/config/server.properties
