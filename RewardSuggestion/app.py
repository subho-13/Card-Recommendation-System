from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/postRewardSuggestion', methods=['POST'])
def post_reward_suggestion():    
    data = request.form                    
    expenditure_details = data['expenditure_details']
    reward_details = data['reward_details']   
    # call func  
    return dummy


if __name__ == '__main__':
    app.run(debug=True)
