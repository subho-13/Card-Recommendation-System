package com.wf.recommendationprovider.service;

import com.wf.contractlib.entities.CardType;
import com.wf.contractlib.entities.JobType;
import com.wf.contractlib.entities.PurchaseCategory;
import com.wf.recommendationprovider.entity.CardBenefits;
import com.wf.recommendationprovider.entity.FeatureVector;
import com.wf.recommendationprovider.util.ComplimentaryCardRepositoryBuilder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ComplimentaryCardProvider {
    private final Map<CardType, Map<CardType, CardBenefits>> complimentaryCardMapping =
            ComplimentaryCardRepositoryBuilder.getComplimentaryCardMap();

    private float getSumOfPurchaseBenefits(Map<PurchaseCategory, Float> purchaseBenefits) {
        float sum = 0;

        for (Map.Entry<PurchaseCategory, Float> purchaseBenefitsEntry : purchaseBenefits.entrySet()) {
            sum += purchaseBenefitsEntry.getValue();
        }

        return sum;
    }

    private float getRewardPoints(Map<PurchaseCategory, Float> purchaseExpenditureMap,
                                  Map<PurchaseCategory, Float> purchaseBenefitMap) {
        float rewardPoints = 0;

        for (Map.Entry<PurchaseCategory, Float> purchaseExpenditureEntry : purchaseExpenditureMap.entrySet()) {
            float expenditure = purchaseExpenditureEntry.getValue();
            PurchaseCategory purchaseCategory = purchaseExpenditureEntry.getKey();
            float benefit = purchaseBenefitMap.get(purchaseCategory);
            rewardPoints += expenditure * benefit;
        }

        return rewardPoints / 10;
    }

    public CardType getComplimentaryCard(FeatureVector featureVector) {
        CardType proposedCard = featureVector.getCardType();
        Map<CardType, CardBenefits> cardBenefitsMap = complimentaryCardMapping.get(proposedCard);

        CardType recommendedCard = CardType.UNKNOWN;

        float maxRewardPoints = 0;
        float maxBenefitsSum = 0;

        for (Map.Entry<CardType , CardBenefits> cardBenefitsEntry : cardBenefitsMap.entrySet()) {
            CardType card = cardBenefitsEntry.getKey();
            CardBenefits cardBenefits = cardBenefitsEntry.getValue();

            if (card == CardType.COLLEGE && featureVector.getJob() != JobType.STUDENT) {
                continue;
            }

            if (featureVector.getCreditScore() < cardBenefits.getCreditScore()) {
                continue;
            }

            float rewardPoints = getRewardPoints(featureVector.getPurchaseExpenditureMap(),
                    cardBenefits.getPurchaseBenefitsMap());

            if (rewardPoints > maxRewardPoints) {
                recommendedCard = card;
                continue;
            } else if (rewardPoints < maxRewardPoints) {
                continue;
            }

            float benefitsSum = getSumOfPurchaseBenefits(cardBenefits.getPurchaseBenefitsMap());

            if (benefitsSum >= maxBenefitsSum) {
                recommendedCard = card;
            }
        }

        return recommendedCard;
    }

}
