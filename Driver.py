#!/usr/bin/python3
import csv
import requests
from time import sleep

url = "http://localhost:9501/transaction/"

with open('./Data/Final Transaction Data.csv', mode='r') as csv_file:
    csv_reader = csv.DictReader(csv_file)
    line_count = 0
    column_headers = []
    for row in csv_reader:
        if line_count > 0:
            requests.post(url, json=row)
            print(line_count)
            sleep(0.025)
        line_count += 1
