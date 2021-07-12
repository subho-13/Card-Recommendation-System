#!/bin/sh
cd ../binaries/kafka_2.13-2.8.0/bin/
./kafka-console-consumer.sh --topic $1 --bootstrap-server localhost:9092
