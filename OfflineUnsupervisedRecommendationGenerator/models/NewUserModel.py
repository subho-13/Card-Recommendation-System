import json

import numpy as np
import pandas as pd


def card_data_generator():
    '''Generates Card Data as a list of dictionaries (one dictionary for each card).'''

    card1 = '{"Card_ID": 1, "Card_Name" : "Cash Wise", "Credit_Score": 645, "Card_Desc": "Cash Wise Card", "Intro" : [{"Intro_ID" : 1, "Category" : "Any", "Threshold" : 1000, "Reward" : 1500}]}'
    card2 = '{"Card_ID": 2, "Card_Name" : "Platinum", "Credit_Score": 600, "Card_Desc": "Platinum Card"}'
    card3 = '{"Card_ID": 3, "Card_Name" : "Hotel", "Credit_Score": 635, "Card_Desc": "Hotel Card", "Intro" : [{"Intro_ID" : 1, "Category" : "Hotel", "Threshold" : 1000, "Reward" : 1500}]}'
    card4 = '{"Card_ID": 4, "Card_Name" : "College", "Credit_Score": 0, "Card_Desc": "College Card", "Intro" : [{"Intro_ID" : 1, "Category" : "Education", "Threshold" : 800, "Reward" : 1000}]}'
    card5 = '{"Card_ID": 5, "Card_Name" : "Visa Signature", "Credit_Score": 680, "Card_Desc": "Visa Signature Card"}'
    card6 = '{"Card_ID": 6, "Card_Name" : "Holiday", "Credit_Score": 618, "Card_Desc": "Holiday Card", "Intro" : [{"Intro_ID" : 1, "Category" : "Travel", "Threshold" : 1000, "Reward" : 500}]}'
    card7 = '{"Card_ID": 7, "Card_Name" : "Shopping", "Credit_Score": 660, "Card_Desc": "Shopping Card", "Intro" : [{"Intro_ID" : 1, "Category" : "Shop_pos", "Threshold" : 500, "Reward" : 1500},\
                                                                                                                    {"Intro_ID" : 2, "Category" : "Shop_net", "Threshold" : 500, "Reward" : 1000}]}'
    card8 = '{"Card_ID": 8, "Card_Name" : "Entertainment", "Credit_Score": 630, "Card_Desc": "Entertainment Card", "Intro" : [{"Intro_ID" : 1, "Category" : "Entertainment", "Threshold" : 1000, "Reward" : 1500}]}'
    card9 = '{"Card_ID": 9, "Card_Name" : "Credit Builder", "Credit_Score": 0, "Card_Desc": "Credit Builder Card"}'
    card1 = json.loads(card1)
    card2 = json.loads(card2)
    card3 = json.loads(card3)
    card4 = json.loads(card4)
    card5 = json.loads(card5)
    card6 = json.loads(card6)
    card7 = json.loads(card7)
    card8 = json.loads(card8)
    card9 = json.loads(card9)
    card_data = []
    card_data.append(card1)
    card_data.append(card2)
    card_data.append(card3)
    card_data.append(card4)
    card_data.append(card5)
    card_data.append(card6)
    card_data.append(card7)
    card_data.append(card8)
    card_data.append(card9)
    return card_data


def card_map_generator(card_data):
    '''Generates dictionary for mapping Card Names to Card ID.'''

    card_mapper = {}
    for i, card in enumerate(card_data):
        card_mapper[card['Card_Name']] = i
    return card_mapper


def available_cards(card_data, df):
    '''Generates Card Scores for Users based on Credit Scores (For New Users)'''

    user_final_list = []
    for i in range(len(df)):
        user_list = []
        max_credit = 0
        credit = df.loc[i, 'credit_score']
        for j, card in enumerate(card_data):
            if (card['Card_Name'] == "College" and df.loc[i, 'job'] == 'student'):
                if (df.loc[i, 'card_type'] != j):
                    user_list.append(1.0)
                    continue

            if (card['Card_Name'] != "College" and credit >= card['Credit_Score'] and df.loc[i, 'card_type'] != j):
                if card['Credit_Score'] >= max_credit:
                    user_list = (np.zeros(len(user_list))).tolist()
                    user_list.append(1.0)
                    max_credit = card['Credit_Score']
                    continue

            user_list.append(0.0)

        user_final_list.append(user_list)
    return user_final_list


def user_tuple_generator(user_final_list, model_name):
    '''Generates a tuple for each user containing User_Id,
    Model Name and user's card probability vector.'''

    user_tuple_list = []
    for i, user in enumerate(user_final_list):
        single_user = [i, model_name, user]
        user_tuple_list.append(single_user)
    return user_tuple_list


def new_user_model(table_name, engine):
    df = pd.read_sql_table(table_name, engine)
    df.drop('new_user', axis=1, inplace=True)
    df.sort_values(by=['User_Id'], inplace=True, ignore_index=True)
    # print(df.info())
    card_data = card_data_generator()
    user_final_list = available_cards(card_data, df)
    user_list = user_tuple_generator(user_final_list, 'New User')

    user_tuple_list = []
    for user in user_list:
        user[0] = int(df.loc[user[0], 'User_Id'])
        user_tuple_list.append(tuple(user))
    return user_tuple_list
