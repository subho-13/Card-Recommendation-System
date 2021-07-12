package com.wf.featureextractionone.service;

import com.wf.contractlib.contracts.AbstractedTransaction;
import com.wf.contractlib.contracts.featurevector.FeatureVectorOne;
import com.wf.contractlib.entities.PurchaseCategory;
import com.wf.featureextractionone.entity.FeatureVector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DetailsGenerator {
    private final Map<PurchaseCategory, Float> meanPurchaseExpenditureMap = new ConcurrentHashMap<>();
    private final Map<PurchaseCategory, Long> purchaseCountMap = new ConcurrentHashMap<>();

    @Value("${min-purchase-with-normal-expenditure-count}")
    private int minPurchaseWithNormalExpenditureCount;

    @Value("${percent-of-average}")
    private float percentOfAverage;

    private RestService restService;

    @Autowired
    public void setRestService(RestService restService) {
        this.restService = restService;
    }

    public FeatureVector createNewFeatureVector(AbstractedTransaction abstractedTransaction) {
        FeatureVector featureVector = new FeatureVector();

        Integer customerID = abstractedTransaction.getCustomerID();
        Integer cardID = abstractedTransaction.getCardID();

        featureVector.setCustomerID(customerID);

        featureVector.setJob(restService.getCustomerJob(customerID));
        featureVector.setCreditScore(restService.getCustomerCreditScore(customerID));
        featureVector.setCardIssueUnixTime(restService.getCardIssueUnixTime(cardID));

        featureVector.setNewUser(true);
        featureVector.setCardType(abstractedTransaction.getCardType());

        featureVector.setPurchaseExpenditureMap(new HashMap<>());
        featureVector.getPurchaseExpenditureMap().put(abstractedTransaction.getPurchaseCategory(),
                abstractedTransaction.getTransactionAmount());

        return featureVector;
    }

    public FeatureVector createUpdatedFeatureVector(FeatureVector featureVector,
                                                    AbstractedTransaction abstractedTransaction) {
        FeatureVector newFeatureVector = new FeatureVector();
        newFeatureVector.setCustomerID(featureVector.getCustomerID());
        newFeatureVector.setJob(featureVector.getJob());
        newFeatureVector.setCreditScore(featureVector.getCreditScore());
        newFeatureVector.setCardIssueUnixTime(featureVector.getCardIssueUnixTime());
        newFeatureVector.setCardType(featureVector.getCardType());
        newFeatureVector.setPurchaseExpenditureMap(featureVector.getPurchaseExpenditureMap());

        PurchaseCategory purchaseCategory = abstractedTransaction.getPurchaseCategory();
        float totalExpenditure = getTotalExpenditure(featureVector, abstractedTransaction, purchaseCategory);

        newFeatureVector.getPurchaseExpenditureMap().put(purchaseCategory,
                totalExpenditure);

        newFeatureVector.setNewUser(isNewUser(newFeatureVector));

        return newFeatureVector;
    }

    private float getTotalExpenditure(FeatureVector featureVector, AbstractedTransaction abstractedTransaction,
                                      PurchaseCategory purchaseCategory) {
        Float newExpenditure = abstractedTransaction.getTransactionAmount();

        Float oldExpenditure = 0.0F;

        if (featureVector.getPurchaseExpenditureMap().containsKey(purchaseCategory)) {
            oldExpenditure = featureVector.getPurchaseExpenditureMap().get(purchaseCategory);
        }

        return oldExpenditure + newExpenditure;
    }


    public FeatureVectorOne convert(FeatureVector featureVector) {
        FeatureVectorOne featureVectorOne = new FeatureVectorOne();

        featureVectorOne.setCustomerID(featureVector.getCustomerID());
        featureVectorOne.setJob(featureVector.getJob());
        featureVectorOne.setCreditScore(featureVector.getCreditScore());
        featureVectorOne.setNewUser(featureVector.getNewUser());
        featureVectorOne.setCardType(featureVector.getCardType());
        featureVectorOne.setCardIssueUnixTime(featureVector.getCardIssueUnixTime());
        featureVectorOne.setPurchaseExpenditureMap(featureVector.getPurchaseExpenditureMap());

        return featureVectorOne;
    }

    public void updateMeanPurchaseExpenditureMap(AbstractedTransaction abstractedTransaction) {
        PurchaseCategory purchaseCategory = abstractedTransaction.getPurchaseCategory();

        if (!meanPurchaseExpenditureMap.containsKey(purchaseCategory)) {
            meanPurchaseExpenditureMap.put(purchaseCategory, 0.0F);
        }

        if (!purchaseCountMap.containsKey(purchaseCategory)) {
            purchaseCountMap.put(purchaseCategory, 0L);
        }

        float meanExpenditure = meanPurchaseExpenditureMap.get(purchaseCategory);
        long count = purchaseCountMap.get(purchaseCategory);

        float newMeanExpenditure =
                (meanExpenditure * count + abstractedTransaction.getTransactionAmount()) / (count + 1);

        meanPurchaseExpenditureMap.put(purchaseCategory, newMeanExpenditure);
        purchaseCountMap.put(purchaseCategory, count + 1);
        System.out.println(meanPurchaseExpenditureMap);
        System.out.println(purchaseCountMap);
    }

    private boolean isNewUser(FeatureVector featureVector) {
        int purchaseWithNormalExpenditure = 0;
        Map<PurchaseCategory, Float> userPurchaseExpenditureMap = featureVector.getPurchaseExpenditureMap();

        for (Map.Entry<PurchaseCategory, Float> purchaseExpenditure : userPurchaseExpenditureMap.entrySet()) {
            PurchaseCategory purchaseCategory = purchaseExpenditure.getKey();

            if (! meanPurchaseExpenditureMap.containsKey(purchaseCategory)) {
                purchaseWithNormalExpenditure++;
                continue;
            }

            float meanExpenditure = meanPurchaseExpenditureMap.get(purchaseCategory);
            float userExpenditure = purchaseExpenditure.getValue();

            if (userExpenditure >= percentOfAverage * meanExpenditure) {
                purchaseWithNormalExpenditure++;
            }
        }

        return purchaseWithNormalExpenditure < minPurchaseWithNormalExpenditureCount;
    }
}
