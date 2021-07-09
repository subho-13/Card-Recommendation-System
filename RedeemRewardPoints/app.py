from flask import Flask

app = Flask(__name__)


@app.route('/get/customerID')
def hello_world():
    return 'Hello World!'


if __name__ == '__main__':
    app.run()
