#!/bin/sh
cd ../
PGPASSWORD=themonksofteamb1 psql -h localhost -U teamb1 -d offline_unsupervised_recommendation_generator_db -c 'DROP TABLE IF EXISTS feature_vector_one;'
PGPASSWORD=themonksofteamb1 psql -h localhost -U teamb1 -d redeem_reward_points_db -c 'DROP TABLE IF EXISTS customer_details cascade;'
PGPASSWORD=themonksofteamb1 psql -h localhost -U teamb1 -d redeem_reward_points_db -c 'DROP TABLE IF EXISTS expenditure_details;'
PGPASSWORD=themonksofteamb1 psql -h localhost -U teamb1 -d redeem_reward_points_db -c 'DROP TABLE IF EXISTS reward_details;'
PGPASSWORD=themonksofteamb1 psql -h localhost -U teamb1 -d nearline_supervised_recommendation_trainer_db -c 'DROP TABLE IF EXISTS user_details;'
PGPASSWORD=themonksofteamb1 psql -h localhost -U teamb1 -d nearline_supervised_recommendation_generator_db -c 'DROP TABLE IF EXISTS feature_vector_one;'
