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


def category_card_mapper():
    '''Loads in the Category-Card Map and generates the mapping from Card Name to Card ID.'''

    cat_map = pd.read_excel("Cat_Map.xlsx")
    cat_map.sort_values(by='Category_Name', inplace=True)
    indices = pd.Series(cat_map.index, index=cat_map['Category_Name'])
    return cat_map, indices


def card_allocator(user_data_df, Card_Data, cat_map, User_ID, indices, category_start, card_mapper):
    '''Generates a list for every user containing the information regarding 
    potential reward point gained using each card for last six months (size of dataset). 
    Also checks eligibility of each user for every card.'''

    user_list = []
    credit = user_data_df.loc[User_ID, 'credit_score']
    profession = user_data_df.loc[User_ID, 'job']
    user = np.array(user_data_df[user_data_df["User_Id"] == User_ID + 1])
    user_data = user[0][category_start:]  # the columns from which categories start
    user_data = user_data.astype(float)

    for card in Card_Data:
        card_name = card["Card_Name"]
        card_percent = np.array(cat_map[card_name])
        card_percent = card_percent / 10
        if (card['Card_ID'] - 1) == user[0][user_data_df.columns.get_loc('card_type')]:
            user_list.append(0)
            continue
        if card_name == 'College' and profession != 'student':
            user_list.append(0)
            continue
        if credit < card['Credit_Score']:
            user_list.append(0)
            continue

        ans = np.dot(card_percent, user_data)

        if 'Intro' in card.keys():
            for intro in card['Intro']:
                category = intro['Category']
                threshold = intro['Threshold']
                reward = intro['Reward']
                if category == 'Any':
                    if user_data.sum() > threshold:
                        ans = ans + reward
                elif user_data[indices[category]] >= threshold:
                    ans = ans + reward

        user_list.append(int(ans))

    if not (np.any(np.array(user_list))):
        user_list[card_mapper['Credit Builder']] = 1.0
    return user_list


def card_list_generator(df, card_data, cat_map, indices, card_mapper):
    '''Generates a list of the potential gains of all users.'''

    # =============================================================================
    #     best_card = []
    # =============================================================================
    user_final_list = []
    category_start = df.columns.get_loc('Education')
    for user_num in range(len(df)):
        user_list = card_allocator(df, card_data, cat_map, user_num, indices, category_start, card_mapper)
        user_final_list.append(user_list)
    # =============================================================================
    #         best_card.append(user_list.index(max(user_list)))
    # =============================================================================
    return user_final_list


def standardize(users):
    '''Standardises the recommendation scores for every user'''

    for i in range(0, len(users)):
        sum = 0
        for j in range(0, len(users[i])):
            sum += users[i][j]
        for j in range(0, len(users[i])):
            users[i][j] /= sum
    return users


def user_tuple_generator(user_final_list, model_name):
    '''Generates a tuple for each user containing User_Id,
    Model Name and user's card probability vector.'''

    user_tuple_list = []
    for i, user in enumerate(user_final_list):
        single_user = [i, model_name, user]
        # user_tuple = tuple(single_user)
        user_tuple_list.append(single_user)
    return user_tuple_list


def rule_learning(table_name, engine):
    df = pd.read_sql_table(table_name, engine)
    df.drop('new_user', axis=1, inplace=True)
    df.sort_values(by=['User_Id'], inplace=True, ignore_index=True)

    card_data = card_data_generator()
    card_mapper = card_map_generator(card_data)
    cat_map, indices = category_card_mapper()

    user_final_list = card_list_generator(df, card_data, cat_map, indices, card_mapper)
    user_final_list = standardize(user_final_list)
    user_final_tuple_list = user_tuple_generator(user_final_list, 'Rule Learning')

    # return user_final_tuple_list
    user_tuple_list = []
    for user in user_final_tuple_list:
        user[0] = int(df.loc[user[0], 'User_Id'])
        user_tuple_list.append(tuple(user))
    return user_tuple_list
