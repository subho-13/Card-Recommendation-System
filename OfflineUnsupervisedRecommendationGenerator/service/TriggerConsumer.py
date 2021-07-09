import json

from kafka.coordinator.assignors.roundrobin import RoundRobinPartitionAssignor

from Configuration import bootstrap_servers
from threading import Thread
from kafka import KafkaConsumer


class TriggerConsumer(Thread):
    def __init__(self, offline_trigger_gid, event, mutex):
        super(TriggerConsumer, self).__init__()
        self.kafka_consumer = KafkaConsumer('OfflineTrigger', group_id=offline_trigger_gid,
                                            bootstrap_servers=bootstrap_servers,
                                            auto_offset_reset='latest',
                                            value_deserializer=lambda message: json.loads(message.decode('utf-8')),
                                            partition_assignment_strategy=[RoundRobinPartitionAssignor]
                                            )
        self.event = event
        self.mutex = mutex

    def run(self):
        for _ in self.kafka_consumer:
            print("Consumed :: Trigger")
            if self.mutex.locked():
                self.mutex.release()
            if not self.event.is_set():
                return