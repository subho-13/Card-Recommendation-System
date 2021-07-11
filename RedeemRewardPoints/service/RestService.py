import requests

from service.RewardPointsCalculator import total_amount


def min_max_scale_value(value, min_val, max_val):
    if min_val == max_val:
        return 1
    else:
        return (value - min_val) / (max_val - min_val)


class RestService:
    def __init__(self, rwlock, purchase_min_max_dict):
        self.rwlock = rwlock
        self.purchase_min_max_dict = purchase_min_max_dict
        self.reward_suggestion_url = 'http://localhost:9509'

    def call_reward_redeem_suggestion_service(self, expenditure_details, reward_details):
        expenditure_details = self.min_max_scale_expenditure(expenditure_details)
        reward_points = total_amount(reward_details)

        val = requests.post(self.reward_suggestion_url, data={
            'expenditure_details': expenditure_details.__dict__,
            'reward_points': reward_points
        })

        return val.json()

    def min_max_scale_expenditure(self, expenditure_details):
        with self.rwlock.gen_rlock():
            if 0 in self.purchase_min_max_dict['min'] and 0 in self.purchase_min_max_dict['max']:
                expenditure_details.Education = min_max_scale_value(expenditure_details.Education,
                                                                    self.purchase_min_max_dict['min'][0],
                                                                    self.purchase_min_max_dict['max'][0])

            if 1 in self.purchase_min_max_dict['min'] and 1 in self.purchase_min_max_dict['max']:
                expenditure_details.Entertainment = min_max_scale_value(expenditure_details.Entertainment,
                                                                        self.purchase_min_max_dict['min'][1],
                                                                        self.purchase_min_max_dict['max'][1])

            if 2 in self.purchase_min_max_dict['min'] and 2 in self.purchase_min_max_dict['max']:
                expenditure_details.Food = min_max_scale_value(expenditure_details.Food,
                                                               self.purchase_min_max_dict['min'][2],
                                                               self.purchase_min_max_dict['max'][2])

            if 3 in self.purchase_min_max_dict['min'] and 3 in self.purchase_min_max_dict['max']:
                expenditure_details.Gas_trans = min_max_scale_value(expenditure_details.Gas_trans,
                                                                    self.purchase_min_max_dict['min'][3],
                                                                    self.purchase_min_max_dict['max'][3])

            if 4 in self.purchase_min_max_dict['min'] and 4 in self.purchase_min_max_dict['max']:
                expenditure_details.Grocery_net = min_max_scale_value(expenditure_details.Grocery_net,
                                                                      self.purchase_min_max_dict['min'][4],
                                                                      self.purchase_min_max_dict['max'][4])

            if 5 in self.purchase_min_max_dict['min'] and 5 in self.purchase_min_max_dict['max']:
                expenditure_details.Grocery_pos = min_max_scale_value(expenditure_details.Grocery_pos,
                                                                      self.purchase_min_max_dict['min'][5],
                                                                      self.purchase_min_max_dict['max'][5])

            if 6 in self.purchase_min_max_dict['min'] and 6 in self.purchase_min_max_dict['max']:
                expenditure_details.Health = min_max_scale_value(expenditure_details.Health,
                                                                 self.purchase_min_max_dict['min'][6],
                                                                 self.purchase_min_max_dict['max'][6])

            if 7 in self.purchase_min_max_dict['min'] and 7 in self.purchase_min_max_dict['max']:
                expenditure_details.Home = min_max_scale_value(expenditure_details.Home,
                                                               self.purchase_min_max_dict['min'][7],
                                                               self.purchase_min_max_dict['max'][7])

            if 8 in self.purchase_min_max_dict['min'] and 8 in self.purchase_min_max_dict['max']:
                expenditure_details.Hotel = min_max_scale_value(expenditure_details.Hotel,
                                                                self.purchase_min_max_dict['min'][8],
                                                                self.purchase_min_max_dict['max'][8])

            if 9 in self.purchase_min_max_dict['min'] and 9 in self.purchase_min_max_dict['max']:
                expenditure_details.Kids_pets = min_max_scale_value(expenditure_details.Kids_pets,
                                                                    self.purchase_min_max_dict['min'][9],
                                                                    self.purchase_min_max_dict['max'][9])

            if 10 in self.purchase_min_max_dict['min'] and 10 in self.purchase_min_max_dict['max']:
                expenditure_details.Misc_net = min_max_scale_value(expenditure_details.Misc_net,
                                                                   self.purchase_min_max_dict['min'][10],
                                                                   self.purchase_min_max_dict['max'][10])
            if 11 in self.purchase_min_max_dict['min'] and 11 in self.purchase_min_max_dict['max']:
                expenditure_details.Misc_pos = min_max_scale_value(expenditure_details.Misc_pos,
                                                                   self.purchase_min_max_dict['min'][11],
                                                                   self.purchase_min_max_dict['max'][11])
            if 12 in self.purchase_min_max_dict['min'] and 12 in self.purchase_min_max_dict['max']:
                expenditure_details.Personal = min_max_scale_value(expenditure_details.Personal,
                                                                   self.purchase_min_max_dict['min'][12],
                                                                   self.purchase_min_max_dict['max'][12])
            if 13 in self.purchase_min_max_dict['min'] and 13 in self.purchase_min_max_dict['max']:
                expenditure_details.Shop_net = min_max_scale_value(expenditure_details.Shop_net,
                                                                   self.purchase_min_max_dict['min'][13],
                                                                   self.purchase_min_max_dict['max'][13])
            if 14 in self.purchase_min_max_dict['min'] and 14 in self.purchase_min_max_dict['max']:
                expenditure_details.Shop_pos = min_max_scale_value(expenditure_details.Shop_pos,
                                                                   self.purchase_min_max_dict['min'][14],
                                                                   self.purchase_min_max_dict['max'][14])
            if 15 in self.purchase_min_max_dict['min'] and 15 in self.purchase_min_max_dict['max']:
                expenditure_details.Travel = min_max_scale_value(expenditure_details.Travel,
                                                                 self.purchase_min_max_dict['min'][15],
                                                                 self.purchase_min_max_dict['max'][15])

        return expenditure_details
