from threading import Thread


class FeatureVectorOneConsumer(Thread):
    def __init__(self):
        super(FeatureVectorOneConsumer, self).__init__()
        self.kafka_consumer = Kaf