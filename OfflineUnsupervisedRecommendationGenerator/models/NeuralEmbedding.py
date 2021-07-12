import collections

from tensorflow.keras.layers import Input, Embedding, Dot, Reshape
from tensorflow.keras.models import Model

from lib.CommonFunctions1 import *


def standardize_data(data):
    '''Standardizing the data using StandardScaler'''

    scaler = StandardScaler()
    scaled_data = scaler.fit_transform(data)
    return scaled_data


def extract_pairs(user_list):
    '''Assigns values to each user-category pair depending on the user's expenditure in that category'''

    users = []
    cat = []
    val = []
    for i, user in enumerate(user_list):
        for j in range(len(user)):
            users.append(i)
            cat.append(j)
            val.append(user_list[i][j])
    user_cat_dict = {
        'user': np.array(users),
        'category': np.array(cat)
    }
    return user_cat_dict, np.array(val)


def user_embedding_model(user_list, index_cat, embedding_size=10):
    """Model to embed users wrt their spending in different categories"""

    user = Input(name='user', shape=[1])
    category = Input(name='category', shape=[1])

    user_embedding = Embedding(name='user_embedding',
                               input_dim=len(user_list),
                               output_dim=embedding_size)(user)

    category_embedding = Embedding(name='category_embedding',
                                   input_dim=len(index_cat),
                                   output_dim=embedding_size)(category)

    merged = Dot(name='dot_product', normalize=True, axes=2)([user_embedding, category_embedding])
    merged = Reshape(target_shape=[1])(merged)

    model = Model(inputs=[user, category], outputs=merged)
    model.compile(optimizer='Adam', loss='mse')

    return model


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


def get_card_probability(user_embeddings, initial_cards, card_data, df, card_mapper, similarity_count):
    '''Returns the probability of each card to be allotted for every user'''
    user_final_list = []

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

        user_final_list.append(card_prob)

    return user_final_list


def neural_embedding(df):
    # =============================================================================
    #     df = pd.read_csv('Updated User Database.csv')
    #     df_1, empty_list = new_user_remover(df)
    # =============================================================================

    initial_cards = df['card_type'].values
    df_cat = df.drop(['User_Id', 'job', 'credit_score', 'card_issue_date', 'card_type'], axis=1)
    index_cat = list(df_cat.columns)
    cat_index = {cat: idx for idx, cat in enumerate(index_cat)}
    user_list = df_cat.values
    scaled_user_list = standardize_data(user_list)
    X, y = extract_pairs(scaled_user_list)

    model = user_embedding_model(user_list, index_cat)
    output = model.fit(X, y, batch_size=256, epochs=100, shuffle=True, verbose=0)

    user_layer = model.get_layer('user_embedding')
    user_embeddings = user_layer.get_weights()[0]

    card_data = card_data_generator()
    card_mapper = card_map_generator(card_data)
    user_final_list = get_card_probability(user_embeddings, initial_cards, card_data, df, card_mapper, 40)

    user_final_list = standardize(user_final_list)
    # =============================================================================
    #     user_final_list1 = np.concatenate((user_final_list, empty_list))
    # =============================================================================
    # np.save('Model_Weights/neural_embedding_user_final_list', user_final_list)
    user_final_tuple_list = user_tuple_generator(user_final_list, 'Neural Based')
    return user_final_tuple_list
