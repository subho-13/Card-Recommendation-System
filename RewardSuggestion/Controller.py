from flask import Flask, request

from service.SuggestReward import get_rewards

app = Flask(__name__)


@app.route('/post/RewardSuggestion', methods=['POST'])
def post_reward_suggestion():
    data = request.json
    expenditure_details = data['expenditure_details']
    reward_points = data['reward_points']

    print(data)

    list_of_suggestions = get_rewards(expenditure_details, reward_points)

    print(list_of_suggestions)
    return { "product" : list_of_suggestions }


if __name__ == '__main__':
    app.run(host="localhost", port=9509)
