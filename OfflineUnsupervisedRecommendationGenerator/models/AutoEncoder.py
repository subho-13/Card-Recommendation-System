import collections

import tensorflow as tf
from sklearn.preprocessing import MinMaxScaler

from lib.CommonFunctions1 import *


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
        print('zero division error')
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


def get_card_probability(user_embeddings, initial_cards, card_data, df, card_mapper, similarity_count):
    '''Returns the probability of each card to be allotted for every user'''

    user_card_probabilities = []

    for i in range(len(user_embeddings)):
        similar_user_list = similar_users(i, user_embeddings, similarity_count)
        card_list = initial_cards[similar_user_list]
        card_prob = card_count_converter(card_list, len(card_data))

        for j, card in enumerate(card_data):
            card_name = card["Card_Name"]
            profession = df.loc[i, 'job']
            credit = df.loc[i, 'credit_score']
            #             if (card['Card_ID'] - 1) == df.loc[i, 'card_type']:
            #                 card_prob[j] = 0.0
            #                 continue
            if card_name == 'College' and profession != 'student':
                card_prob[j] = 0.0
                continue
            if credit < card['Credit_Score']:
                card_prob[j] = 0.0
                continue
            try:
                card_prob[j] *= (max(600, card['Credit_Score']) / (df.groupby('card_type').size()[j]))
            except:
                pass

        if not (np.any(np.array(card_prob))):
            card_prob[card_mapper['Credit Builder']] = 1.0

        user_card_probabilities.append(card_prob)
    return user_card_probabilities


def auto_encoder(df):
    # =============================================================================
    #     df = pd.read_csv('Updated User Database.csv')
    #     df_1, empty_list = new_user_remover(df)
    # =============================================================================

    df_cat = df.drop(['User_Id', 'job', 'card_issue_date', 'card_type'], axis=1)

    user_list = df_cat.values
    scaled_user_list = standardize_data(user_list)

    model = AutoEncoder()
    model.compile(optimizer='adam', loss='mse')
    output = model.fit(x=scaled_user_list, y=scaled_user_list, epochs=50, batch_size=128, shuffle=True, verbose=0)

    user_embeddings = model.encoder(scaled_user_list).numpy()

    initial_cards = df['card_type'].values
    card_data = card_data_generator()
    card_mapper = card_map_generator(card_data)
    user_final_list = get_card_probability(user_embeddings, initial_cards, card_data, df, card_mapper, 40)

    user_final_list = standardize(user_final_list)
    # =============================================================================
    #     user_final_list1 = np.concatenate((user_final_list, empty_list))
    # =============================================================================
    # np.save('Model_Weights/auto_encoder_user_final_list', user_final_list)
    user_final_tuple_list = user_tuple_generator(user_final_list, 'Autoencoder Based')
    return user_final_tuple_list
