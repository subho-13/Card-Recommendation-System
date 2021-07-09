from entities.RewardDetails import RewardDetails
from entities.ExpenditureDetails import ExpenditureDetails
from lib.CommonFunctions import *

def update_rewards(reward_details,cat,ans):
    if cat == 0:
        reward_details.Education += ans
    elif cat == 1:
        reward_details.Entertainment += ans
    elif cat == 2:
        reward_details.Food += ans
    elif cat == 3:
        reward_details.Gas_trans += ans
    elif cat == 4:
        reward_details.Grocery_net += ans
    elif cat == 5:
        reward_details.Grocery_pos += ans
    elif cat == 6:
        reward_details.Health += ans
    elif cat == 7:
        reward_details.Home += ans
    elif cat == 8:
        reward_details.Hotel += ans
    elif cat == 9:
        reward_details.Kids_pets += ans
    elif cat == 10:
        reward_details.Misc_net += ans
    elif cat == 11:
        reward_details.Misc_pos += ans
    elif cat == 12:
        reward_details.Personal += ans
    elif cat == 13:
        reward_details.Shop_net += ans
    elif cat == 14:
        reward_details.Shop_pos += ans
    elif cat == 15:
        reward_details.Travel += ans
    return reward_details
        
def update_exp(expenditure_details,cat,amount):
    if cat == 0:
        expenditure_details.Education += amount
    elif cat == 1:
        expenditure_details.Entertainment += amount
    elif cat == 2:
        expenditure_details.Food += amount
    elif cat == 3:
        expenditure_details.Gas_trans += amount
    elif cat == 4:
        expenditure_details.Grocery_net += amount
    elif cat == 5:
        expenditure_details.Grocery_pos += amount
    elif cat == 6:
        expenditure_details.Health += amount
    elif cat == 7:
        expenditure_details.Home += amount
    elif cat == 8:
        expenditure_details.Hotel += amount
    elif cat == 9:
        expenditure_details.Kids_pets += amount
    elif cat == 10:
        expenditure_details.Misc_net += amount
    elif cat == 11:
        expenditure_details.Misc_pos += amount
    elif cat == 12:
        expenditure_details.Personal += amount
    elif cat == 13:
        expenditure_details.Shop_net += amount
    elif cat == 14:
        expenditure_details.Shop_pos += amount
    elif cat == 15:
        expenditure_details.Travel += amount
    return expenditure_details
    
def total_amount(expenditure_details):
    s = 0
    s += expenditure_details.Education
    s += expenditure_details.Entertainment
    s += expenditure_details.Food
    s += expenditure_details.Gas_trans
    s += expenditure_details.Grocery_net
    s += expenditure_details.Grocery_pos
    s += expenditure_details.Health
    s += expenditure_details.Home
    s += expenditure_details.Hotel
    s += expenditure_details.Kids_pets
    s += expenditure_details.Misc_net
    s += expenditure_details.Misc_pos
    s += expenditure_details.Personal
    s += expenditure_details.Shop_net
    s += expenditure_details.Shop_pos
    s += expenditure_details.Travel
    return s
    
def cat_wise_amt(expenditure_details,cat):
    if cat == 0:
       return expenditure_details.Education
    elif cat == 1:
        return expenditure_details.Entertainment
    elif cat == 2:
        return expenditure_details.Food 
    elif cat == 3:
        return expenditure_details.Gas_trans 
    elif cat == 4:
        return expenditure_details.Grocery_net 
    elif cat == 5:
        return expenditure_details.Grocery_pos 
    elif cat == 6:
        return expenditure_details.Health 
    elif cat == 7:
        return expenditure_details.Home 
    elif cat == 8:
        return expenditure_details.Hotel 
    elif cat == 9:
        return expenditure_details.Kids_pets 
    elif cat == 10:
        return expenditure_details.Misc_net 
    elif cat == 11:
        return expenditure_details.Misc_pos 
    elif cat == 12:
        return expenditure_details.Personal 
    elif cat == 13:
        return expenditure_details.Shop_net 
    elif cat == 14:
        return expenditure_details.Shop_pos 
    elif cat == 15:
        return expenditure_details.Travel 

def calculate_reward_points(abstracted_transaction, expenditure_details, reward_details):
    if(expenditure_details is None or reward_details is None):
        expenditure_details, reward_details = ExpenditureDetails(), RewardDetails()
    
    card_data = card_data_generator()
    card_mapper = card_map_generator(card_data)
    inv_card_map = inv_card_map_generator(card_data)
    cat_map, indices = category_card_mapper()
    
    amount = abstracted_transaction.transaction_amount ## amount spent
    card_type = abstracted_transaction.card_type
    card_name = inv_card_map[card_type] ## Card used
    card = card_data[card_type] ## Details of the card used
    card_no = abstracted_transaction.card_id ## Card Number(here it also works as user number)
    cat =  abstracted_transaction.purchase_category ## Purchase Category
    card_percent = cat_map[card_name][cat] ## Percent rewards in that category for the card used
    card_percent = card_percent/10
    
    ans = card_percent*amount ## Rewards

    reward_details = update_rewards(reward_details,cat,ans) ## add rewards to the dictionary corresponding to the purchase category
    expenditure_details = update_exp(expenditure_details,cat,amount) ## amount spent in the category
    
    if 'Intro' in card.keys():
        for intro in card['Intro']: ## Intro benefits of the card used
            category = intro['Category'] ## Intro benefit on which category
            threshold = intro['Threshold'] ## The amount threshold
            reward = intro['Reward'] ## The Intro reward

            if category == 'Any': ## If reward is given on just the amount spent not a particular category
                if total_amount(expenditure_details) > threshold and reward_details.is_any_reward_given == False:
                    ans = ans + reward
                    reward_details = update_rewards(reward_details,cat,reward)
                    reward_details.is_any_reward_given == True
            elif cat_wise_amt(expenditure_details,cat) >= threshold: ## If reward is on a particular category
                x = reward_details.is_reward_given_in_category
                if (x & (1 << (cat - 1))) >> (cat - 1) == 0:
                    ans = ans + reward
                    reward_details = update_rewards(reward_details,cat,reward) 
                    reward_details.is_reward_given_in_category = (x | (1 << (cat-1))) ## Intro reward given
    
    return expenditure_details, reward_details