from math import *

from minisom import MiniSom
from sklearn.preprocessing import MinMaxScaler

from lib.CommonFunctions1 import *


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

    som = MiniSom(x=n, y=m, input_len=18, sigma=1.5, learning_rate=0.5)
    som.random_weights_init(X)
    som.train_random(X, 5000)

    mappings = som.win_map(X)
    return som, mappings


def cluster_dict_generator(mappings, card_data, df, sc, n, m):
    '''Generates a dictionary mapping each node(coordinate)
    in som to the card scores based on count frequency'''

    mapping_dict = {}
    for i in range(n):
        for j in range(m):
            cluster = mappings[(i, j)]
            cluster_dict = {}
            if len(cluster) > 0:
                cluster = sc.inverse_transform(cluster)
                cluster = cluster[:, 0].astype(int)
                cluster_series = pd.value_counts(
                    df[df['User_Id'].isin(cluster)]['card_type'])
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
            #             if (card['Card_ID'] - 1) == df.loc[i, 'card_type']:
            #                 user_list[j] = 0.0
            #                 continue
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

def self_organizing_maps(df):
    cat_map, indices = category_card_mapper()
    card_data = card_data_generator()
    sc, X = data_preparation(df)

    val = len(df) / 20
    n = int(max(4, ceil(sqrt(val))))
    m = n
    som, mappings = self_organising_map(X, n, m)
    mapping_dict = cluster_dict_generator(mappings, card_data, df, sc, n, m)

    card_mapper = card_map_generator(card_data)
    user_final_list = recommendation_generator(som, mapping_dict, card_data,
                                               df, X, card_mapper)

    user_final_list = standardize(user_final_list)

    user_final_tuple_list = user_tuple_generator(user_final_list, 'SOM Based')
    return user_final_tuple_list
