package com.wf.recommendationprovider.service;

import com.wf.contractlib.contracts.CompiledRecommendation;
import com.wf.contractlib.contracts.featurevector.FeatureVectorOne;
import com.wf.recommendationprovider.entity.CustomerDetails;
import org.springframework.stereotype.Service;

@Service
public class DetailsGenerator {
    public CustomerDetails generate(FeatureVectorOne featureVectorOne) {
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setCustomerID(featureVectorOne.getCustomerID());
        customerDetails.setJob(featureVectorOne.getJob());
        customerDetails.setCreditScore(featureVectorOne.getCreditScore());
        customerDetails.setNewUser(featureVectorOne.getNewUser());
        customerDetails.setCardType(featureVectorOne.getCardType());
        customerDetails.setPurchaseExpenditureMap(featureVectorOne.getPurchaseExpenditureMap());
        return customerDetails;
    }

    public CustomerDetails generate(CompiledRecommendation compiledRecommendation) {
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setCustomerID(compiledRecommendation.getCustomerId());
        customerDetails.setCardConfidenceMap(compiledRecommendation.getCardConfidenceMap());
        return customerDetails;
    }
}
