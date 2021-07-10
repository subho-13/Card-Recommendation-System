import threading
from time import sleep

from flask import Flask
from repository.DatabaseHandler import *
from service.AbstractedTransactionConsumer import AbstractedTransactionConsumer
from service.RestService import RestService
from threading import Thread
from readerwriterlock import rwlock

app = Flask(__name__)


@app.route('/get/<customer_id>')
def hello_world(customer_id):
    list_of_cards = get_card_details(customer_id)

    if len(list_of_cards) == 0:
        return {}

    dict_of_suggestions = {}
    for card in list_of_cards:
        expenditure_details = get_expenditure_details(card.card_id)
        reward_details = get_reward_details(card.card_id)
        suggestions = RestService.call_reward_redeem_suggestion_service(expenditure_details, reward_details)
        dict_of_suggestions[card.card_id] = suggestions
            
    return dict_of_suggestions


if __name__ == '__main__':
    event = threading.Event()
    event.set()

    rwlock_reader = rwlock.RWLockReadD()

    purchase_min_max_dict = {
        "min": {},
        "max": {}
    }

    list_of_threads = [
        AbstractedTransactionConsumer(event, rwlock_reader, purchase_min_max_dict),
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