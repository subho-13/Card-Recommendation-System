from sklearn.decomposition import KernelPCA
from sklearn.decomposition import PCA

from lib.CommonFunctions1 import *


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
            #                 user_list[j] *= ((max(600, card['Credit_Score'])**2)/(3600*df.groupby('card_type').size()[j]))
            #                 user_list[j] *= (1 - ((df.groupby('card_type').size()[j])/max(600, card['Credit_Score'])))
            except:
                pass

        if not (np.any(np.array(user_list))):
            user_list[card_mapper['Credit Builder']] = 1.0
        user_final_list.append(user_list)

    return user_final_list


def kpca_similarity(df):
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

    sim_matrix = similarity_generator(X)
    mapping_dict = cluster_dict_generator(sim_matrix, df, card_data)

    card_mapper = card_map_generator(card_data)
    user_final_list = recommendation_generator(sim_matrix, mapping_dict, card_data, df, card_mapper)

    user_final_list = standardize(user_final_list)

    # =============================================================================
    #     user_final_list1 = np.concatenate((user_final_list, empty_list))
    # =============================================================================
    # np.save('Model_Weights/kpca_similarity_user_final_list', user_final_list)
    user_final_tuple_list = user_tuple_generator(user_final_list, 'Kpca Similarity')
    return user_final_tuple_list
