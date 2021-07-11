import pickle

import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
import seaborn as sns
from sklearn.preprocessing import StandardScaler


def card_data_generator():
    with open('card_data.pickle', 'rb') as handle:
        card_data = pickle.load(handle)
    return card_data


def card_map_generator(card_data):
    '''Generates dictionary for mapping Card Names to Card ID.'''

    card_mapper = {}
    for i, card in enumerate(card_data):
        card_mapper[card['Card_Name']] = i
    return card_mapper


def inv_card_map_generator(card_data):
    '''Generates dictionary for mapping Card Names to Card ID.'''

    inv_card_mapper = {}
    for i, card in enumerate(card_data):
        inv_card_mapper[i] = card['Card_Name']
    return inv_card_mapper


def category_card_mapper():
    '''Generates mapping from Card Name to Card Number (Index)'''

    cat_map = pd.read_excel("Cat_Map.xlsx")
    cat_map.sort_values(by='Category_Name', inplace=True)
    indices = pd.Series(cat_map.index, index=cat_map['Category_Name'])
    return cat_map, indices


def data_preparation(df):
    '''Cleaning and Scaling the data'''

    dataset = df.copy()
    dataset.drop(labels=['User_Id', 'card_issue_date', 'job', 'card_type'], axis=1, inplace=True)
    X = dataset.iloc[:, :].values
    sc = StandardScaler()
    X = sc.fit_transform(X)
    return sc, X


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
        user_tuple = tuple(single_user)
        user_tuple_list.append(user_tuple)
    return user_tuple_list


def card_dict_converter(user_list, card_data):
    '''Converts the user_list into dictionary for further use.'''

    card_list_dict = []
    for i, user in enumerate(user_list):
        card_dict = {}
        for j in range(len(card_data)):
            card_dict[card_data[j]["Card_Name"]] = user[j]
            card_dict = dict(sorted(card_dict.items(), key=lambda item: item[1], reverse=True))
        card_dict["user_id"] = i
        card_list_dict.append(card_dict)
    return card_list_dict


def card_type_dict_generator(df):
    '''Generates dictionary for mapping User_Id to card_type.'''

    card_type_dict = {}
    for i in range(len(df)):
        card_type_dict.update({i: df.loc[i].at["card_type"]})
    return card_type_dict


def complimentary_card_generator():
    '''Generates a list of Complimentary Cards for each card
    sorted by the combined benefits provided by both'''

    cat_map = pd.read_excel('Cat_Map.xlsx')
    cat_map.sort_values(by='Category_Name', inplace=True)
    card_data = card_data_generator()

    cat_map.drop(['Category_Name'], axis=1, inplace=True)
    cat_map_values = cat_map.values.T

    complimentary_card_dict = {}
    for i, card1 in enumerate(cat_map_values):
        card_list = []
        for j, card2 in enumerate(cat_map_values):
            if (i == j):
                continue
            card = [max(card1[k], card2[k]) for k in range(len(cat_map_values[0]))]
            #             card_sum = sum(card)
            card_list.append((card_data[j]['Card_Name'], card, card_data[j]['Credit_Score']))
        card_list.sort(key=lambda x: x[1], reverse=True)
        complimentary_card_dict[card_data[i]['Card_Name']] = card_list

    return complimentary_card_dict


def complimentary_recommendation_generator(user_arr, complimentary_card_dict, card_type_ind, cred_score_ind, job_ind):
    '''Given a user's details, returns the best complimentary card for the user'''

    card_data = card_data_generator()
    card_mapper = inv_card_map_generator(card_data)
    complimentary_vector = complimentary_card_dict[card_mapper[user_arr[card_type_ind]]]
    user = user_arr[3:]
    max_rew = 0
    cat_sum = 0
    comp_card = []
    for card in complimentary_vector:
        if card[0] == 'College' and user_arr[job_ind] != 'student':
            continue
        if (card[2] <= user_arr[cred_score_ind]):
            card_percent = list(np.array(card[1]) / 10)
            rew = np.dot(card_percent, user)
            if rew > max_rew:
                comp_card = []
                comp_card.append(card[0])
                cat_sum = sum(card[1])
                max_rew = rew
                continue
            if rew == max_rew and sum(card[1]) > cat_sum:
                comp_card = []
                comp_card.append(card[0])
                cat_sum = sum(card[1])
                max_rew = rew
                continue
            if rew == max_rew and sum(card[1]) == cat_sum:
                comp_card.append(card[0])
    if len(comp_card) == 1:
        return comp_card[0]
    else:
        return np.random.choice(comp_card)


def get_complimentary_card(complimentary_card_dict, df, user_id):
    df_copy = df.copy()
    df_copy.drop(labels=['User_Id', 'card_issue_date'], axis=1, inplace=True)
    cred_score_ind = df_copy.columns.get_loc('credit_score')
    card_type_ind = df_copy.columns.get_loc('card_type')
    job_ind = df_copy.columns.get_loc('job')
    user_arr = df_copy.iloc[user_id, :].tolist()
    comp_card = complimentary_recommendation_generator(user_arr, complimentary_card_dict, card_type_ind, cred_score_ind,
                                                       job_ind)
    return comp_card


def card_generator(card_type_dict, card_dict, card_mapper):
    '''Generates Recommendations - 3 for KPCA Algorithm with K-Means.'''

    rec = []
    for i in range(0, len(card_dict)):
        if card_type_dict[i] != card_mapper[list(card_dict[i])[0]]:
            rec.append([i, list(card_dict[i])[0], 1])
        elif card_dict[i][list(card_dict[i])[1]] != 0:
            rec.append([i, list(card_dict[i])[1], 2])
        else:
            rec.append([i, 'Credit Builder', 2])
    return rec


def initial_cards_plotter(df):
    '''Used to plot initial card distribution.'''

    plt.figure(figsize=(12, 8))
    sns.countplot(x='card_type', data=df)
    plt.xlabel('Card Type (Card_Id)')
    plt.ylabel('Number of Customers')
    plt.title('Initial Cards Distribution')
    plt.show()


def recommended_cards_plotter(recommendation_3):
    '''Used to plot recommended card distribution.'''

    #     recommendation_3 = np.array(recommendation_3)
    df_extra = pd.DataFrame(recommendation_3, columns=['User_Id', 'Card_Name', 'Rec_Type'])
    plt.figure(figsize=(12, 8))
    sns.countplot(x='Card_Name', data=df_extra)
    plt.xlabel('Card Name')
    plt.ylabel('Number of Customers')
    plt.title('Recommended Cards Distribution')
    plt.show()
    return df_extra


def confusion_matrix_plotter(rule_result, model_result):
    '''Plots the confusion matrix between Rule Based Recommendations
       and recommendations generated using KPCA with K-Means.'''

    from sklearn.metrics import confusion_matrix
    cm = confusion_matrix(rule_result, model_result)
    pal = sns.color_palette("coolwarm", 20)
    plt.figure(figsize=(8, 8))
    sns.heatmap(cm, annot=True, cmap=pal, fmt=".0f", annot_kws={"size": 14}, linewidths=0.5)
    plt.title("Confusion Matrix")
    plt.xlabel("Predicted Values")
    plt.ylabel("Actual Values")
    plt.show()
    print('Accuracy Comparison with Rule Based Result: ', cm.trace() / 983)
