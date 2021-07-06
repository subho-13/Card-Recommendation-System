from datetime import datetime

from lib.CommonDicts import purchase_category_dict, card_dict, job_dict


def generate_feature_vector_one(message):
    temp = {
        'User_Id': message['customerID'],
        'job': job_dict[message['job']],
        'new_user': message['newUser'],
        'card_issue_date': get_card_issue_date(message['cardIssueUnixTime']),
        'card_type': card_dict[message['cardType']],
        'credit_score': message['creditScore']
    }

    for category in purchase_category_dict:
        if category in message['purchaseExpenditureMap']:
            temp[purchase_category_dict[category]] = float(message['purchaseExpenditureMap'][category])
        else:
            temp[purchase_category_dict[category]] = 0.0

    return temp


def get_card_issue_date(card_issue_unix_time):
    time = datetime.fromtimestamp(card_issue_unix_time)
    return time.strftime('%Y-%m-%d')
