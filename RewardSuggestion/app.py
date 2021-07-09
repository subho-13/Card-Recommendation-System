import threading

from flask import Flask, request, jsonify

from SuggestReward import get_rewards

app = Flask(__name__)


@app.route('/postRewardSuggestion', methods=['POST'])
def post_reward_suggestion():
    data = request.form
    expenditure_details = data['expenditure_details']
    reward_details = data['reward_details']
    list_of_suggestions = get_rewards(expenditure_details, reward_details)
    return list_of_suggestions


if __name__ == '__main__':
    app.run(host="localhost", port=8080)