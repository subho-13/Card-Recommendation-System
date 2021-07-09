from flask import Flask

app = Flask(__name__)


@app.route('/')
def get_reward_suggestion(expenditure_details, reward_details):
    suggested_reward_list = []

    return suggested_reward_list


if __name__ == '__main__':
    app.run()
