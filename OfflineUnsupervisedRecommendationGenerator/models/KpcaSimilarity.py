import json

import numpy as np
import pandas as pd
from sklearn.decomposition import KernelPCA
from sklearn.decomposition import PCA
from sklearn.preprocessing import StandardScaler


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


def embedding_generator(X):
    '''Generate embedding for every user'''

    pca = PCA(n_components=0.95)
    Z = pca.fit_transform(X)
    num_comp = pca.n_components_
    kpca = KernelPCA(n_components=num_comp, kernel='sigmoid')
    X = kpca.fit_transform(X)
    return X


def similarity_generator(X):
    '''Generates User to User Similarity'''

    emb = pd.DataFrame(X)
    emb = emb.T
    usermatrix = emb.corr(method='pearson')
    user_user = []
    for i in range(0, len(usermatrix)):
        a = list(usermatrix.nlargest(51, i).index)
        for j in range(0, len(a)):
            if a[j] == i:
                a.pop(j)
                break
        user_user.append(a)
    return user_user


def cluster_dict_generator(user_user, df, card_data):
    '''Converts the user_list into dictionary for further use'''

    mapping_dict = {}
    for i in range(len(user_user)):
        sim_series = pd.value_counts(df[df['User_Id'].isin(user_user[i])]['card_type'])
        #     print(cluster_series)
        total = sim_series.sum()
        sim_series = sim_series / total
        sim_dict = dict(sim_series)
        for k in range(len(card_data)):
            if k not in sim_dict:
                sim_dict[k] = 0.0
        sim_dict = dict(sorted(sim_dict.items()))
        mapping_dict[i] = sim_dict
    return mapping_dict


def recommendation_generator(user_user, mapping_dict, card_data, df, card_mapper):
    '''Returns the recommendation vector generated using KPCA'''

    user_final_list = []
    for i in range(0, len(user_user)):
        user_list = list(mapping_dict[i].values())
        for j, card in enumerate(card_data):
            card_name = card["Card_Name"]
            profession = df.loc[i, 'job']
            credit = df.loc[i, 'credit_score']
            if (card['Card_ID'] - 1) == df.loc[i, 'card_type']:
                user_list[j] = 0.0
                continue
            if card_name == 'College' and profession != 'student':
                user_list[j] = 0.0
                continue
            if credit < card['Credit_Score']:
                user_list[j] = 0.0
                continue

            user_list[j] *= (max(600, card['Credit_Score']) / (df.groupby('card_type').size()[j]))
        if not (np.any(np.array(user_list))):
            user_list[card_mapper['Credit Builder']] = 1.0
        user_final_list.append(user_list)

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


def kpca_similarity(table_name, engine):
    df = pd.read_sql_table(table_name, engine)
    df.drop('new_user', axis=1, inplace=True)
    df.sort_values(by=['User_Id'], inplace=True, ignore_index=True)

    cat_map, indices = category_card_mapper()
    card_data = card_data_generator()

    sc, X = data_preparation(df)
    X = embedding_generator(X)

    sim_matrix = similarity_generator(X)
    mapping_dict = cluster_dict_generator(sim_matrix, df, card_data)

    card_mapper = card_map_generator(card_data)
    user_final_list = recommendation_generator(sim_matrix, mapping_dict, card_data, df, card_mapper)

    user_final_list = standardize(user_final_list)
    user_final_tuple_list = user_tuple_generator(user_final_list, 'Kpca Similarity')

    # return user_final_tuple_list

    user_tuple_list = []
    for user in user_final_tuple_list:
        user[0] = int(df.loc[user[0], 'User_Id'])
        user_tuple_list.append(tuple(user))
    return user_tuple_list
