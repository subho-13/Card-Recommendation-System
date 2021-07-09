def min_max_scale_value(value, min, max):
    if min == max:
        return 0
    else:
        return (value - min) / (max - min)


class RestService:
    def __init__(self, rwlock, purchase_min_max_dict):
        self.rwlock = rwlock
        self.purchase_min_max_dict = purchase_min_max_dict

    def min_max_scale_expenditure(self, expenditure_details):
        with self.rwlock.gen_rlock():
            

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


            
            
