from flask import Flask
from repository.DatabaseHandler import *
from service.RestService import RestService
from threading import Thread

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
    list_of_thread = [

    ]
    app.run()
