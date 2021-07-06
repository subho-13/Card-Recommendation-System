import threading

from readerwriterlock import rwlock
from time import sleep

from service.FeatureVectorConsumer import FeatureVectorConsumer
from service.RecommendationProducer import RecommendationProducer
from service.TriggerConsumer import TriggerConsumer


def main(model_driver, model_name, to_send, topic, generate_feature_vector_one, write_to_db):
    event = threading.Event()
    event.set()

    mutex = threading.Lock()
    mutex.acquire()

    rwlock_writer = rwlock.RWLockWriteD()

    threads = [
        TriggerConsumer(model_name, event, mutex),
        RecommendationProducer(event, mutex, rwlock_writer, model_driver, to_send),
        FeatureVectorConsumer(event, rwlock_writer, topic, generate_feature_vector_one, write_to_db)
    ]

    for thread in threads:
        thread.start()
    
    try:
        while True:
            sleep(2)
    except KeyboardInterrupt:
        event.clear()

        if mutex.locked():
            mutex.release()
        
        for thread in threads:
            thread.join()