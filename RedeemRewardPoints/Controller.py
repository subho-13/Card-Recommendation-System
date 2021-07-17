import threading
from threading import Thread
from time import sleep

from flask import Flask
from readerwriterlock import rwlock

from repository.DatabaseHandler import *
from service.AbstractedTransactionConsumer import AbstractedTransactionConsumer
from service.RestService import RestService

app = Flask(__name__)
purchase_category_min_max_dict = {
    "min": {},
    "max": {}
}

event = threading.Event()
event.set()

rwlock_reader = rwlock.RWLockReadD()
rest_service = RestService(rwlock_reader, purchase_category_min_max_dict)


@app.route('/get/<customer_id>')
def hello_world(customer_id):
    customer_details = get_customer_details(customer_id)
    expenditure_details = get_expenditure_details(customer_details.card_id)
    reward_details = get_reward_details(customer_details.card_id)
    suggestions = rest_service.call_reward_redeem_suggestion_service(expenditure_details, reward_details)
    return suggestions


if __name__ == '__main__':
    list_of_threads = [
        AbstractedTransactionConsumer(event, rwlock_reader, purchase_category_min_max_dict),
        Thread(target=app.run, args=("localhost", 9503))
    ]

    try:
        for thread in list_of_threads:
            thread.start()

        while True:
            sleep(5)
    except KeyboardInterrupt:
        event.clear()

        for thread in list_of_threads:
            thread.join()
