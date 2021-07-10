import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.preprocessing import MinMaxScaler
from mlxtend.preprocessing import TransactionEncoder
from mlxtend.frequent_patterns import fpgrowth, apriori, association_rules
import json
import pickle
import openpyxl
from lib.CommonFunctions import *

def user_list_generator(user_dict):
    '''Converts the spendings of user from dict form to list form'''
    
    cats = ['Education', 'Entertainment', 'Food', 'Gas_trans', 'Grocery_net',
            'Grocery_pos', 'Health', 'Home', 'Hotel', 'Kids_pets', 'Misc_net',
            'Misc_pos', 'Personal', 'Shop_net', 'Shop_pos', 'Travel']
    
    user_list = []
    for cat in cats:
        user_list.append(user_dict[cat])
    user_list = np.array(user_list)
    return user_list

def user_cat_list_generator(user):
    '''Returns a list of categories for each user in which maximum
       amount is spent by the user'''
    
    cat_list = ['Education', 'Entertainment', 'Food', 'Gas_trans', 'Grocery_net', 'Grocery_pos',
                'Health', 'Home', 'Hotel', 'Kids_pets', 'Misc_net', 'Misc_pos', 'Personal', 'Shop_net', 'Shop_pos', 'Travel']
    
    user_cat = sorted(zip(user,cat_list),reverse=True)[:3]    # Sort user list and pick max 3 categories
    user_cat_list = [cat[1] for cat in user_cat]                  # Extract cat no.
    
    return user_cat_list

def association_generator(user_cat_list, cat_dict):
    '''Generates the list of association categories for the user'''
    
    user = user_cat_list

    # Find out all pairs of categories that may be present (max size -> 3)
    check_list = [ tuple([user[0]]), tuple([user[1]]), tuple([user[2]]),
                   tuple([user[0],user[1]]), tuple([user[0],user[2]]) ,tuple([user[1],user[2]]),
                   tuple([user[0],user[1],user[2]])
                 ]

    # Check if a group of cats is present in cat_dict which contains association categories, then add them too
    for check in check_list:
        if check in cat_dict.keys():
            user += cat_dict[check]
    user = list(set(user))         # Extracting only unique categories
    
    return user

def find_suggestions(user_association_list, df_prod, reward_points, factor = 1.5):
    '''Given the product database and interest categories of user, returns
    the list of 3 most favorable products to recommend to the user'''
    
    reward_points *= 1.5       # Find the reward point threshold
    
    # Find products in user's categories and which have reward points less then threshold
    df_user = df_prod.loc[ (df_prod['product_category'].isin(user_association_list)) & (df_prod['reward_points_required'] <= reward_points)]

    # Sort and find out top 3 products wrt to reward points
    df_user = df_user.sort_values('reward_points_required', ascending=False)
    val = df_user.values
    products = list(val[:3,0])
        
    return products

def get_rewards(expenditure_details, reward_points):    
    '''Return product suggestions for a particular user'''
    user_dict = expenditure_details

    df_prod = pd.read_excel('Product_Database.xlsx')
    
    with open('cat_dict.pickle', 'rb') as handle:
        cat_dict = pickle.load(handle)
        
    user_list = user_list_generator(user_dict)
    user_cat_list = user_cat_list_generator(user_list)
    user_association_list = association_generator(user_cat_list, cat_dict)       # Add association categories to user_list
    suggestion_list = find_suggestions(user_association_list, df_prod, reward_points)     # Find suggestions
    return suggestion_list

