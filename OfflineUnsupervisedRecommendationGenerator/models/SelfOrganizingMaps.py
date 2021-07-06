import json

import numpy as np
import pandas as pd
from minisom import MiniSom
from sklearn.preprocessing import MinMaxScaler


# from pylab import bone, pcolor, colorbar

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
    '''Generates feature array after cleaning, normalization
    to be given as input to self organising maps'''

    df_new = df.drop(labels=['job', 'card_issue_date', 'card_type'], axis=1)
    X = df_new.iloc[:, :].values
    sc = MinMaxScaler(feature_range=(0, 1))
    X = sc.fit_transform(X)
    return sc, X


def self_organising_map(X, n, m):
    '''Applies self organising maps algorithm on input feature array and 
    return som object along with mapping of each user to a node(coordinate) in som'''

    som = MiniSom(x=n, y=m, input_len=18, sigma=1.5, learning_rate=0.3)
    som.random_weights_init(X)
    som.train_random(X, 5000)
    # =============================================================================
    #     print("Quantization Error: ", som.quantization_error(X))
    #     print("Topographic Error: ", som.topographic_error(X))
    #
    #     bone()
    #     pcolor(som.distance_map().T, cmap = 'coolwarm')
    #     colorbar()
    # =============================================================================
    mappings = som.win_map(X)
    return som, mappings


def cluster_dict_generator(mappings, card_data, df, sc, n, m):
    '''Generates a dictionary mapping each node(coordinate)
    in som to the card scores based on count frequency'''

    mapping_dict = {}
    for i in range(n):
        for j in range(m):
            cluster = mappings[(i, j)]
            if len(cluster) > 0:
                cluster = sc.inverse_transform(cluster)
                cluster = cluster[:, 0].astype(int)
                cluster_series = pd.value_counts(df[df['User_Id'].isin(cluster)]['card_type'])
                total = cluster_series.sum()
                cluster_series = cluster_series / total
                cluster_dict = dict(cluster_series)
                for k in range(len(card_data)):
                    if k not in cluster_dict:
                        cluster_dict[k] = 0.0
                cluster_dict = dict(sorted(cluster_dict.items()))
            mapping_dict[(i, j)] = cluster_dict
    return mapping_dict


def recommendation_generator(som, mapping_dict, card_data, df, X, card_mapper):
    '''Generates final user list containing 
    the scores of each card for every user'''

    user_final_list = []
    for i, x in enumerate(X):
        w = som.winner(x)
        user_list = list(mapping_dict[w].values())
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

            try:
                user_list[j] *= (max(600, card['Credit_Score']) / (df.groupby('card_type').size()[j]))
            except:
                pass

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


def self_organizing_maps(table_name, engine):
    df = pd.read_sql_table(table_name, engine)
    df.drop('new_user', axis=1, inplace=True)
    df.sort_values(by=['User_Id'], inplace=True, ignore_index=True)

    cat_map, indices = category_card_mapper()
    card_data = card_data_generator()
    sc, X = data_preparation(df)

    n = 7
    m = 7
    som, mappings = self_organising_map(X, n, m)
    mapping_dict = cluster_dict_generator(mappings, card_data, df, sc, n, m)

    card_mapper = card_map_generator(card_data)
    user_final_list = recommendation_generator(som, mapping_dict, card_data, df, X, card_mapper)

    user_final_list = standardize(user_final_list)
    user_final_tuple_list = user_tuple_generator(user_final_list, 'Som Based')

    # return user_final_tuple_list
    user_tuple_list = []
    for user in user_final_tuple_list:
        user[0] = int(df.loc[user[0], 'User_Id'])
        user_tuple_list.append(tuple(user))
    return user_tuple_list
