from flask import Flask, request

from service.SuggestReward import get_rewards

app = Flask(__name__)


@app.route('/postRewardSuggestion', methods=['POST'])
def post_reward_suggestion():
    data = request.form
    expenditure_details = data['expenditure_details']
    reward_points = data['reward_points']

    list_of_suggestions = get_rewards(expenditure_details, reward_points)
    return list_of_suggestions


if __name__ == '__main__':
    app.run(host="localhost", port=9509)
