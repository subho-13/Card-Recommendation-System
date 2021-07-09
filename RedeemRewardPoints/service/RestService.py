def min_max_scale_value(value, min, max):
    if min == max:
        return 1
    else:
        return (value - min) / (max - min)


class RestService:
    def __init__(self, rwlock, purchase_min_max_dict):
        self.rwlock = rwlock
        self.purchase_min_max_dict = purchase_min_max_dict

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
