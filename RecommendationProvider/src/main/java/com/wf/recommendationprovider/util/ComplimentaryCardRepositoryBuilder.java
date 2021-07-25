package com.wf.recommendationprovider.util;

import com.wf.contractlib.entities.CardType;
import com.wf.contractlib.entities.PurchaseCategory;
import com.wf.recommendationprovider.entity.CardBenefits;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ComplimentaryCardRepositoryBuilder {
    public static Map<CardType, Map<CardType, CardBenefits>> getComplimentaryCardMap() {
        Map<CardType, Map<CardType, CardBenefits>> complimentaryCardMap = new ConcurrentHashMap<>();
        Map<CardType, CardBenefits> cardBenefitsMap;
        CardBenefits cardBenefits;
        Map<PurchaseCategory, Float> purchaseBenefitMap;
// Mapping the CardType to the benefits it offers
        cardBenefitsMap = new ConcurrentHashMap<>();

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(0);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 12.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 2.5F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("College"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(725);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 5.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Visa Signature"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(625);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 4.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Entertainment"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(650);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Holiday"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(630);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 11.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Hotel"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(692);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 2.5F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Shopping"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(600);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 2.5F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Platinum"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(0);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 2.5F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Credit Builder"), cardBenefits);

// Finally fitting in the cardBenefitMap with the original card
        complimentaryCardMap.put(CardType.convert("Cash Wise"), cardBenefitsMap);
// Mapping the CardType to the benefits it offers
        cardBenefitsMap = new ConcurrentHashMap<>();

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(0);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 12.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 1.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("College"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(725);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 5.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Visa Signature"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(650);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 2.5F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Cash Wise"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(692);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 2.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Shopping"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(625);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 4.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Entertainment"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(650);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Holiday"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(630);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 11.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Hotel"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(0);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 1.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Credit Builder"), cardBenefits);

// Finally fitting in the cardBenefitMap with the original card
        complimentaryCardMap.put(CardType.convert("Platinum"), cardBenefitsMap);
// Mapping the CardType to the benefits it offers
        cardBenefitsMap = new ConcurrentHashMap<>();

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(0);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 12.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 11.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("College"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(725);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 11.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Visa Signature"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(650);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 11.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Cash Wise"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(692);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 11.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Shopping"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(600);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 11.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Platinum"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(625);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 11.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Entertainment"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(650);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 11.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Holiday"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(0);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 11.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Credit Builder"), cardBenefits);

// Finally fitting in the cardBenefitMap with the original card
        complimentaryCardMap.put(CardType.convert("Hotel"), cardBenefitsMap);
// Mapping the CardType to the benefits it offers
        cardBenefitsMap = new ConcurrentHashMap<>();

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(625);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 12.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 4.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Entertainment"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(650);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 12.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Holiday"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(725);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 12.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 5.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Visa Signature"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(630);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 12.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 11.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Hotel"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(650);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 12.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 2.5F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Cash Wise"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(692);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 12.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 2.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Shopping"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(0);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 12.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 0.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Credit Builder"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(600);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 12.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 1.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Platinum"), cardBenefits);

// Finally fitting in the cardBenefitMap with the original card
        complimentaryCardMap.put(CardType.convert("College"), cardBenefitsMap);
// Mapping the CardType to the benefits it offers
        cardBenefitsMap = new ConcurrentHashMap<>();

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(0);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 12.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 5.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("College"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(625);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 5.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Entertainment"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(650);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Holiday"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(630);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 11.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Hotel"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(692);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 5.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Shopping"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(650);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 5.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Cash Wise"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(600);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 5.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Platinum"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(0);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 5.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Credit Builder"), cardBenefits);

// Finally fitting in the cardBenefitMap with the original card
        complimentaryCardMap.put(CardType.convert("Visa Signature"), cardBenefitsMap);
// Mapping the CardType to the benefits it offers
        cardBenefitsMap = new ConcurrentHashMap<>();

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(0);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 12.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("College"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(725);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Visa Signature"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(650);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Cash Wise"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(692);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Shopping"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(600);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Platinum"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(625);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Entertainment"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(0);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Credit Builder"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(630);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 11.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Hotel"), cardBenefits);

// Finally fitting in the cardBenefitMap with the original card
        complimentaryCardMap.put(CardType.convert("Holiday"), cardBenefitsMap);
// Mapping the CardType to the benefits it offers
        cardBenefitsMap = new ConcurrentHashMap<>();

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(0);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 12.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 2.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("College"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(725);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 5.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Visa Signature"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(650);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 2.5F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Cash Wise"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(625);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 4.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Entertainment"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(650);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Holiday"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(630);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 11.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Hotel"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(600);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 2.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Platinum"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(0);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 2.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Credit Builder"), cardBenefits);

// Finally fitting in the cardBenefitMap with the original card
        complimentaryCardMap.put(CardType.convert("Shopping"), cardBenefitsMap);
// Mapping the CardType to the benefits it offers
        cardBenefitsMap = new ConcurrentHashMap<>();

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(0);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 12.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 4.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("College"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(725);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 5.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Visa Signature"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(650);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 4.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Cash Wise"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(692);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 4.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Shopping"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(600);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 4.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Platinum"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(0);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 4.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Credit Builder"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(630);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 11.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Hotel"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(650);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Holiday"), cardBenefits);

// Finally fitting in the cardBenefitMap with the original card
        complimentaryCardMap.put(CardType.convert("Entertainment"), cardBenefitsMap);
// Mapping the CardType to the benefits it offers
        cardBenefitsMap = new ConcurrentHashMap<>();

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(0);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 12.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 0.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("College"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(725);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 5.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Visa Signature"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(650);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 2.5F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 2.5F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Cash Wise"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(692);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 5.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 2.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Shopping"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(600);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 1.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 1.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Platinum"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(625);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 10.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 4.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Entertainment"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(650);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 6.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 8.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 3.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Holiday"), cardBenefits);

// Preparing the CardBenefit class
        cardBenefits = new CardBenefits();
        cardBenefits.setCreditScore(630);

// Mapping the Purchase Benefits to it's Category

        purchaseBenefitMap = new ConcurrentHashMap<>();
        purchaseBenefitMap.put(PurchaseCategory.EDUCATION, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.ENTERTAINMENT, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.FOOD, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.GAS_TRANS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.GROCERY_POS, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HEALTH, 2.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOME, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.HOTEL, 11.0F);
        purchaseBenefitMap.put(PurchaseCategory.KIDS_PETS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.MISC_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.PERSONAL, 4.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_NET, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.SHOP_POS, 0.0F);
        purchaseBenefitMap.put(PurchaseCategory.TRAVEL, 6.0F);

        cardBenefits.setPurchaseBenefitsMap(purchaseBenefitMap);

// Fitting in the cardBenefits with the card type

        cardBenefitsMap.put(CardType.convert("Hotel"), cardBenefits);

// Finally fitting in the cardBenefitMap with the original card
        complimentaryCardMap.put(CardType.convert("Credit Builder"), cardBenefitsMap);
        return complimentaryCardMap;
    }
}
