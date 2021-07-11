import json
from threading import Thread

from kafka import KafkaConsumer
from kafka.coordinator.assignors.roundrobin import RoundRobinPartitionAssignor

from Configuration import bootstrap_servers


class NearlineTriggerConsumer(Thread):
    def __init__(self, event, rwlock, group_id, generated_recommendation_producer):
        super(NearlineTriggerConsumer, self).__init__()
        self.kafka_consumer = KafkaConsumer("NearlineTrigger", group_id=group_id,
                                            bootstrap_servers=bootstrap_servers,
                                            auto_offset_reset='latest',
                                            value_deserializer=lambda message: json.loads(message.decode('utf-8')),
                                            partition_assignment_strategy=[RoundRobinPartitionAssignor]
                                            )

        self.event = event
        self.rwlock = rwlock
        self.generated_recommendation_producer = generated_recommendation_producer

    def run(self):
        for message in self.kafka_consumer:
            with self.rwlock.gen_wlock():
                self.generated_recommendation_producer.produce(message.value["customerID"])
                if not self.event.is_set():
                    return