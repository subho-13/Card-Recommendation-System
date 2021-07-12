from mlxtend.frequent_patterns import fpgrowth, association_rules
from mlxtend.preprocessing import TransactionEncoder
from sklearn.preprocessing import MinMaxScaler

from lib.CommonFunctions1 import *


def user_cat_list_generator(df):
    '''Returns a list of categories for each user in which maximum
       amount is spent by the user'''

    df_cat = df.drop(['User_Id', 'job', 'credit_score', 'card_issue_date', 'card_type'], axis=1)
    cat_val = df_cat.values
    min_max_scaler = MinMaxScaler()
    cat_scaled = min_max_scaler.fit_transform(cat_val)
    cat_list = list(df_cat.columns)
    user_cat_list = []
    cat_list = list(df_cat.columns)
    for user in cat_scaled:
        user_cat = sorted(zip(user, cat_list), reverse=True)[:3]
        user_list = [cat[1] for cat in user_cat]
        user_cat_list.append(user_list)

    te = TransactionEncoder()
    te_ary = te.fit(user_cat_list).transform(user_cat_list)
    df_final = pd.DataFrame(te_ary, columns=te.columns_)
    return df_final, user_cat_list


def frequent_pattern_growth_algo(df, min_sup, metric='lift', threshold=3.5):
    '''Returns the association rules by Frequent Pattern Growth Algo'''

    rules_fp = fpgrowth(df, min_support=min_sup, use_colnames=True)
    rules_fp = association_rules(rules_fp, metric=metric, min_threshold=threshold)
    return rules_fp


def dict_generator(rules):
    '''Compiles all the rules in a dictionary'''

    rules_left = list(np.array(rules[['antecedents']])[:, 0])
    rules_right = list(np.array(rules[['consequents']])[:, 0])
    new_dict = {}
    for i in range(len(rules_left)):
        new_dict[tuple(rules_left[i])] = tuple(rules_right[i])

    return new_dict


def association_generator(user_cat_list, cat_dict):
    '''Generates the list of association categories for every user'''

    user_association_list = []
    for user in user_cat_list:
        user_list = user
        check_list = [tuple([user[0]]), tuple([user[1]]), tuple([user[2]]),
                      tuple([user[0], user[1]]), tuple([user[0], user[2]]), tuple([user[1], user[2]]),
                      tuple([user[0], user[1], user[2]])
                      ]
        for check in check_list:
            if check in cat_dict.keys():
                user_list += cat_dict[check]
        user_list = list(set(user_list))
        user_association_list.append(user_list)
    return user_association_list


def card_allocator(User_Data, Card_Data, cat_map, User_ID, indices, category_start, association_list, card_mapper):
    '''Generates a list for every user containing the information regarding 
    potential reward point gained using each card for last six months (size of dataset). 
    Also checks eligibility of each user for every card.'''

    user_list = []
    user = np.array(User_Data[User_Data["User_Id"] == User_ID])
    user_data = user[0][category_start:]  # the columns from which categories start
    user_data = user_data.astype(float)
    associations = [(User_Data.columns.get_loc(x) - category_start) for x in association_list]

    for i in range(len(user_data)):
        if i not in associations:
            user_data[i] = 0.0

    for card in Card_Data:
        card_name = card["Card_Name"]
        card_percent = np.array(cat_map[card_name])
        card_percent = card_percent / 10
        credit = User_Data.loc[User_ID, 'credit_score']
        profession = User_Data.loc[User_ID, 'job']

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


def card_list_generator(df, card_data, cat_map, indices, association_list, card_mapper):
    '''Generates a list of the potential gains of all users.'''

    best_card = []
    user_final_list = []
    new_df = df.copy()
    category_start = df.columns.get_loc('Education')
    for user_num in range(len(df)):
        user_list = card_allocator(df, card_data, cat_map, user_num, indices, category_start,
                                   association_list[user_num], card_mapper)
        user_final_list.append(user_list)
        best_card.append(user_list.index(max(user_list)))
    return user_final_list


def association_rule_learning(df):
    # =============================================================================
    #     df = pd.read_csv("Updated User Database.csv")
    #     df_1, empty_list = new_user_remover(df)
    # =============================================================================

    df_final, user_cat_list = user_cat_list_generator(df)
    rules_fp = frequent_pattern_growth_algo(df_final, 0.03)
    fp_dict = dict_generator(rules_fp)

    card_data = card_data_generator()
    card_mapper = card_map_generator(card_data)
    cat_map, indices = category_card_mapper()

    user_association_list = association_generator(user_cat_list, fp_dict)
    user_final_list = card_list_generator(df, card_data, cat_map, indices, user_association_list, card_mapper)
    user_final_list = standardize(user_final_list)

    # =============================================================================
    #     user_final_list1 = np.concatenate((user_final_list, empty_list))
    # =============================================================================
    # np.save('Model_Weights/association_based_user_final_list', user_final_list)
    user_final_tuple_list = user_tuple_generator(user_final_list, 'Association Rule Learning')
    return user_final_tuple_list
