import collections
import json

import numpy as np
import pandas as pd
import tensorflow as tf
from sklearn.preprocessing import MinMaxScaler


def standardize_data(data):
    '''Standardizing the data using MinMaxScaler'''

    scaler = MinMaxScaler()
    scaled_data = scaler.fit_transform(data)
    return scaled_data


class AutoEncoder(tf.keras.Model):
    '''Architecture for AutoEncoder to find out the embeddings of each user'''

    def __init__(self, num_feat=17, num_hidden=12, num_encoding=10, dropout_rate=0.2):
        super().__init__()

        self.encoder = tf.keras.Sequential(
            layers=[

                tf.keras.layers.BatchNormalization(),
                tf.keras.layers.Dense(units=num_hidden, activation="relu"),
                tf.keras.layers.Dropout(rate=dropout_rate),

                # Bottleneck Layer
                tf.keras.layers.BatchNormalization(),
                tf.keras.layers.Dense(units=num_encoding, activation="relu"),
            ]
        )

        self.decoder = tf.keras.Sequential(
            layers=[
                tf.keras.layers.BatchNormalization(),
                tf.keras.layers.Dense(units=num_hidden, activation="relu"),
                tf.keras.layers.Dropout(rate=dropout_rate),

                tf.keras.layers.BatchNormalization(),
                tf.keras.layers.Dense(units=num_feat, activation="sigmoid"),
            ]
        )

    def call(self, x):
        encoding = self.encoder(x)
        decoding = self.decoder(encoding)
        return decoding


def similar_users(user_id, user_embeddings, n=40):
    '''Find out the top n similar users for a particular user'''

    try:
        normalized_embeddings = user_embeddings / np.linalg.norm(user_embeddings, axis=1).reshape((-1, 1))
    except:
        pass

    dists = np.dot(normalized_embeddings, normalized_embeddings[user_id])
    similar_user_list = dists.argsort()[::-1][:n]
    return similar_user_list


def card_count_converter(card_list, card_num):
    '''Normalizes the card vectors depending upon their frequency'''

    count_list = collections.Counter(card_list)
    updated_card_list = []
    for i in range(card_num):
        updated_card_list.append(count_list[i] / len(card_list))
    return updated_card_list


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
    '''Maps the Card Names to their indices'''

    card_mapper = {}
    for i, card in enumerate(card_data):
        card_mapper[card['Card_Name']] = i
    return card_mapper


def get_card_probability(user_embeddings, initial_cards, card_data, df, card_mapper):
    '''Returns the probability of each card to be allotted for every user'''

    user_card_probabilities = []

    for i in range(len(user_embeddings)):
        similar_user_list = similar_users(i, user_embeddings)
        card_list = initial_cards[similar_user_list]
        card_prob = card_count_converter(card_list, len(card_data))

        for j, card in enumerate(card_data):
            card_name = card["Card_Name"]
            profession = df.loc[i, 'job']
            credit = df.loc[i, 'credit_score']
            if (card['Card_ID'] - 1) == df.loc[i, 'card_type']:
                card_prob[j] = 0.0
                continue
            if card_name == 'College' and profession != 'student':
                card_prob[j] = 0.0
                continue
            if credit < card['Credit_Score']:
                card_prob[j] = 0.0
                continue
            card_prob[j] *= (max(600, card['Credit_Score']) / (df.groupby('card_type').size()[j]))

        if not (np.any(np.array(card_prob))):
            card_prob[card_mapper['Credit Builder']] = 1.0

        user_card_probabilities.append(card_prob)
    return user_card_probabilities


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


def auto_encoder(table_name, engine):
    df = pd.read_sql_table(table_name, engine)
    df.drop('new_user', axis=1, inplace=True)
    df.sort_values(by=['User_Id'], inplace=True, ignore_index=True)

    df_cat = df.drop(['User_Id', 'job', 'card_issue_date', 'card_type'], axis=1)

    user_list = df_cat.values
    scaled_user_list = standardize_data(user_list)

    model = AutoEncoder()
    model.compile(optimizer='adam', loss='mse')
    model.fit(x=scaled_user_list, y=scaled_user_list, epochs=50, batch_size=128, shuffle=True, verbose=0)

    user_embeddings = model.encoder(scaled_user_list).numpy()

    initial_cards = df['card_type'].values
    card_data = card_data_generator()
    card_mapper = card_map_generator(card_data)
    user_final_list = get_card_probability(user_embeddings, initial_cards, card_data, df, card_mapper)

    user_final_list = standardize(user_final_list)
    user_final_tuple_list = user_tuple_generator(user_final_list, 'Autoencoder Based')

    # return user_final_tuple_list

    user_tuple_list = []
    for user in user_final_tuple_list:
        user[0] = int(df.loc[user[0], 'User_Id'])
        user_tuple_list.append(tuple(user))
    return user_tuple_list
