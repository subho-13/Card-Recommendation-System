package com.wf.recommendationprovider.service;

import com.wf.contractlib.contracts.CompiledRecommendation;
import com.wf.contractlib.contracts.featurevector.FeatureVectorOne;
import com.wf.recommendationprovider.entity.CompiledRec;
import com.wf.recommendationprovider.entity.FeatureVector;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class DetailsGenerator {
    public FeatureVector generate(@NotNull FeatureVectorOne featureVectorOne) {
        FeatureVector featureVector = new FeatureVector();
        featureVector.setCustomerID(featureVectorOne.getCustomerID());
        featureVector.setJob(featureVectorOne.getJob());
        featureVector.setCreditScore(featureVectorOne.getCreditScore());
        featureVector.setNewUser(featureVectorOne.getNewUser());
        featureVector.setCardType(featureVectorOne.getCardType());
        featureVector.setPurchaseExpenditureMap(featureVectorOne.getPurchaseExpenditureMap());
        return featureVector;
    }

    public CompiledRec generate(@NotNull CompiledRecommendation compiledRecommendation) {
        CompiledRec compiledRec = new CompiledRec();
        compiledRec.setCustomerID(compiledRecommendation.getCustomerID());
        compiledRec.setCardConfidenceMap(compiledRecommendation.getCardConfidenceMap());
        return compiledRec;
    }
}
