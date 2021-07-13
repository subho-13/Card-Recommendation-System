from lib.CommonFunctions1 import *


def card_allocator(user_data_df, Card_Data, cat_map, User_ID, indices, category_start, card_mapper):
    '''Generates a list for every user containing the information regarding 
    potential reward point gained using each card for last six months (size of dataset). 
    Also checks eligibility of each user for every card.'''

    user_list = []
    credit = user_data_df.loc[User_ID, 'credit_score']
    profession = user_data_df.loc[User_ID, 'job']
    user = np.array(user_data_df[user_data_df["User_Id"] == User_ID])
    user_data = user[0][category_start:]  # the columns from which categories start
    user_data = user_data.astype(float)

    for card in Card_Data:
        card_name = card["Card_Name"]
        card_percent = np.array(cat_map[card_name])
        card_percent = card_percent / 10

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


def card_list_generator(df, card_data, cat_map, indices, card_mapper):
    '''Generates a list of the potential gains of all users.'''

    best_card = []
    user_final_list = []
    category_start = df.columns.get_loc('Education')
    for user_num in range(len(df)):
        user_list = card_allocator(df, card_data, cat_map, user_num, indices, category_start, card_mapper)
        user_final_list.append(user_list)
        best_card.append(user_list.index(max(user_list)))
    return user_final_list


def rule_learning(df):
    # =============================================================================
    #     df = pd.read_csv("Updated User Database.csv")
    #     df_1, empty_list = new_user_remover(df)
    # =============================================================================
    card_data = card_data_generator()
    card_mapper = card_map_generator(card_data)
    cat_map, indices = category_card_mapper()

    user_final_list = card_list_generator(df, card_data, cat_map, indices, card_mapper)
    user_final_list = standardize(user_final_list)
    # =============================================================================
    #     user_final_list1 = np.concatenate((user_final_list, empty_list))
    # =============================================================================
    # np.save('Model_Weights/rule_based_user_final_list', user_final_list)
    user_final_tuple_list = user_tuple_generator(user_final_list, 'Rule Learning')
    return user_final_tuple_list
