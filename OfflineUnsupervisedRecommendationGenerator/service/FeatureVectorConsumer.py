from Configuration import bootstrap_servers
from Configuration import feature_vector_consumer_gid
from multiprocessing import Process
from kafka import KafkaConsumer

class FeatureVectorConsumer(Process):
    def __init__(self):
        super(FeatureVectorConsumer, self).__init__()
        self.kafka_consumer = KafkaConsumer()