from sklearn.cluster import KMeans
from sklearn.decomposition import PCA
from yellowbrick.cluster import KElbowVisualizer

from lib.CommonFunctions1 import *


def embedding_generator(X):
    '''Generate embedding for every user'''

    pca = PCA(n_components=0.95)
    Z = pca.fit_transform(X)
    num_comp = pca.n_components_
    X = pca.fit_transform(X)
    return X


def cluster_generator(X):
    '''Generate clusters of users based on their similarity'''

    visualizer = KElbowVisualizer(estimator=KMeans(), k=(4, 30))
    Z = visualizer.fit(X)
    elbow = visualizer.elbow_value_
    plt.close()
    kmeans = KMeans(n_clusters=elbow, init='k-means++', max_iter=300, n_init=10, random_state=0)
    clusters = kmeans.fit_predict(X)
    return clusters


def mapping_generator(clusters):
    '''Return the mappings for each cluster representing the users in a cluster'''

    from collections import defaultdict
    mappings = defaultdict()
    cluster_num = np.unique(clusters)
    for num in cluster_num:
        mappings[num] = [i for i in range(len(clusters)) if clusters[i] == num]

    return mappings, cluster_num


def cluster_dict_generator(mappings, cluster_num, df, card_data):
    '''Converts the user_list into dictionary for further use'''

    mapping_dict = {}
    for i in range(len(cluster_num)):
        cluster = mappings[i]
        if len(cluster) > 0:
            cluster_series = pd.value_counts(df[df['User_Id'].isin(cluster)]['card_type'])
            total = cluster_series.sum()
            cluster_series = cluster_series / total
            cluster_dict = dict(cluster_series)
            for k in range(len(card_data)):
                if k not in cluster_dict:
                    cluster_dict[k] = 0.0
            cluster_dict = dict(sorted(cluster_dict.items()))
        mapping_dict[i] = cluster_dict
    return mapping_dict


def recommendation_generator(clusters, mapping_dict, card_data, df, card_mapper):
    '''Returns the recommendation vector generated using PCA'''

    user_final_list = []
    for i, x in enumerate(clusters):
        user_list = list(mapping_dict[x].values())
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


def pca_kmeans(df):
    # =============================================================================
    #     df = pd.read_csv("Updated User Database.csv")
    # =============================================================================
    # =============================================================================
    #     df_1, empty_list = new_user_remover(df)
    # =============================================================================

    cat_map, indices = category_card_mapper()
    card_data = card_data_generator()

    sc, X = data_preparation(df)
    X = embedding_generator(X)

    clusters = cluster_generator(X)
    mappings, cluster_num = mapping_generator(clusters)
    mapping_dict = cluster_dict_generator(mappings, cluster_num, df, card_data)

    card_mapper = card_map_generator(card_data)
    user_final_list = recommendation_generator(clusters, mapping_dict, card_data, df, card_mapper)

    user_final_list = standardize(user_final_list)
    # =============================================================================
    #     user_final_list1 = np.concatenate((user_final_list, empty_list))
    # =============================================================================
    # np.save('Model_Weights/pca_means_user_final_list', user_final_list)
    user_final_tuple_list = user_tuple_generator(user_final_list, 'PCA Based')

    return user_final_tuple_list
