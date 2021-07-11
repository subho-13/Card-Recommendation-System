class SupervisedModel:
    def __init__(self, model, std_scaler):
        self.model = model
        self.std_scaler = std_scaler

    def generate(self):
        return self

    def update_model(self, another_supervised_model):
        return