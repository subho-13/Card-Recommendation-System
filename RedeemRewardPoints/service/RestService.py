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
            if 0 in self.purchase_min_max_dict['min'] and 0 in self.purchase_min_max_dict['max']:
                expenditure_details.Education = min_max_scale_value(expenditure_details.Education,
                                                                    self.purchase_min_max_dict['min'][0],
                                                                    self.purchase_min_max_dict['max'][0])

            if 0 in self.purchase_min_max_dict['min'] and 0 in self.purchase_min_max_dict['max']:
                expenditure_details.Education = min_max_scale_value(expenditure_details.Education,
                                                                    self.purchase_min_max_dict['min'][0],
                                                                    self.purchase_min_max_dict['max'][0])

            if 0 in self.purchase_min_max_dict['min'] and 0 in self.purchase_min_max_dict['max']:
                expenditure_details.Education = min_max_scale_value(expenditure_details.Education,
                                                                    self.purchase_min_max_dict['min'][0],
                                                                    self.purchase_min_max_dict['max'][0])

            if 0 in self.purchase_min_max_dict['min'] and 0 in self.purchase_min_max_dict['max']:
                expenditure_details.Education = min_max_scale_value(expenditure_details.Education,
                                                                    self.purchase_min_max_dict['min'][0],
                                                                    self.purchase_min_max_dict['max'][0])
