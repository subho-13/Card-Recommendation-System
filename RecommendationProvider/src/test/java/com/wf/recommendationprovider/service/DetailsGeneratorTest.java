package com.wf.recommendationprovider.service;

import com.wf.contractlib.contracts.CompiledRecommendation;
import com.wf.contractlib.contracts.featurevector.FeatureVectorOne;
import com.wf.contractlib.entities.CardType;
import com.wf.contractlib.entities.JobType;
import com.wf.contractlib.entities.PurchaseCategory;
import com.wf.recommendationprovider.entity.CompiledRec;
import com.wf.recommendationprovider.entity.FeatureVector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;


class DetailsGeneratorTest {

    private DetailsGenerator detailsGenerator;

    @BeforeEach
    void setUp() {
        detailsGenerator = new DetailsGenerator();
    }

    @Test
    public void generateFromFeatureVector() {
        Map<PurchaseCategory, Float> purchaseExpenditureMap = new HashMap<>();
        purchaseExpenditureMap.put(PurchaseCategory.EDUCATION, 2345f);
        purchaseExpenditureMap.put(PurchaseCategory.ENTERTAINMENT, 1432f);
        purchaseExpenditureMap.put(PurchaseCategory.FOOD, 768.30f);
        purchaseExpenditureMap.put(PurchaseCategory.GAS_TRANS, 345f);
        purchaseExpenditureMap.put(PurchaseCategory.GROCERY_NET, 789.09f);
        purchaseExpenditureMap.put(PurchaseCategory.GROCERY_POS, 975f);
        purchaseExpenditureMap.put(PurchaseCategory.HEALTH, 2765f);
        purchaseExpenditureMap.put(PurchaseCategory.HOME, 1976f);
        purchaseExpenditureMap.put(PurchaseCategory.HOTEL, 578f);
        purchaseExpenditureMap.put(PurchaseCategory.KIDS_PETS, 2983f);
        purchaseExpenditureMap.put(PurchaseCategory.TRAVEL, 678f);
        purchaseExpenditureMap.put(PurchaseCategory.MISC_NET, 789f);
        purchaseExpenditureMap.put(PurchaseCategory.MISC_POS, 980f);
        purchaseExpenditureMap.put(PurchaseCategory.PERSONAL, 897F);
        purchaseExpenditureMap.put(PurchaseCategory.SHOP_NET, 1632f);
        purchaseExpenditureMap.put(PurchaseCategory.SHOP_POS, 532f);

        FeatureVectorOne featureVectorOne = new FeatureVectorOne();
        featureVectorOne.setCustomerID(1);
        featureVectorOne.setCardType(CardType.CASH_WISE);
        featureVectorOne.setJob(JobType.ACCOUNTANT);
        featureVectorOne.setPurchaseExpenditureMap(purchaseExpenditureMap);
        featureVectorOne.setNewUser(false);
        featureVectorOne.setCardIssueUnixTime(987654L);
        featureVectorOne.setCreditScore(360);

        FeatureVector featureVector = new FeatureVector();
        featureVector.setCustomerID(1);
        featureVector.setJob(JobType.ACCOUNTANT);
        featureVector.setCreditScore(360);
        featureVector.setNewUser(false);
        featureVector.setCardType(CardType.CASH_WISE);
        featureVector.setPurchaseExpenditureMap(purchaseExpenditureMap);

        assertEquals(featureVector, detailsGenerator.generate(featureVectorOne));

    }

    @Test
    public void generatorTest() {

        Map<CardType, Float> confidenceMap = new HashMap<>();
        confidenceMap.put(CardType.CASH_WISE, 0.1f);
        confidenceMap.put(CardType.COLLEGE, 0.02f);
        confidenceMap.put(CardType.CREDIT_BUILDER, 0.03f);
        confidenceMap.put(CardType.ENTERTAINMENT, 0.07f);
        confidenceMap.put(CardType.HOLIDAY, 0.08f);
        confidenceMap.put(CardType.HOTEL, 0.12f);
        confidenceMap.put(CardType.PLATINUM, 0.18f);
        confidenceMap.put(CardType.SHOPPING, 0.1f);
        confidenceMap.put(CardType.VISA_SIGNATURE, 0.09f);

        CompiledRecommendation compiledRecommendation = new CompiledRecommendation();
        compiledRecommendation.setCustomerID(1);
        compiledRecommendation.setCardConfidenceMap(confidenceMap);

        CompiledRec compiledRec = new CompiledRec() ;
        compiledRec.setCustomerID(1);
        compiledRec.setCardConfidenceMap(confidenceMap);

        assertEquals(compiledRec, detailsGenerator.generate(compiledRecommendation));
    }
}
