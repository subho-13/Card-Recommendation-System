import requests

res = requests.post("http://127.0.0.1:5000/postRewardSuggestion", {
	'expenditure_details': 50,
	'reward_details': 20
	})

print(res.content)