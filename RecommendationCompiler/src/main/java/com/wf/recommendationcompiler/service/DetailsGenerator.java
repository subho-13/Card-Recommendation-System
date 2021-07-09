package com.wf.recommendationcompiler.service;

import com.wf.contractlib.contracts.GeneratedRecommendation;
import com.wf.recommendationcompiler.entity.RecommendationDetails;
import org.springframework.stereotype.Service;

@Service
public class DetailsGenerator {
    public RecommendationDetails generate(GeneratedRecommendation generatedRecommendation) {
        RecommendationDetails recommendationDetails = new RecommendationDetails();
        recommendationDetails.setCustomerID(generatedRecommendation.getCustomerID());
        recommendationDetails.setModelName(generatedRecommendation.getModelName());
        recommendationDetails.setCardConfidenceMap(generatedRecommendation.getCardConfidenceMap());
        return recommendationDetails;
    }
}
