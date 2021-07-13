#!/bin/python
import requests
import pandas as pd
import json
import time

df = pd.read_csv("./Data/Final Transaction Data.csv")
dicts = df.to_dict(orient='records')
del df
url = "http://localhost:9501/transaction"
count = 0
for body in dicts:
    count += 1
    requests.post(url, json=body)
    print(count)
