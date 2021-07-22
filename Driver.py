#!/usr/bin/python3
import csv
import requests
from time import sleep
from random import randint

url = "http://localhost:9501/transaction/"

with open('./Data/Final Transaction Data.csv', mode='r') as csv_file:
    csv_reader = csv.DictReader(csv_file)
    line_count = 0
    column_headers = []
    for row in csv_reader:
        if line_count > 0:
            requests.post(url, json=row)
            print("Transaction Number :: " , line_count)
            sleep(randint(20, 40)/1000)
        line_count += 1
