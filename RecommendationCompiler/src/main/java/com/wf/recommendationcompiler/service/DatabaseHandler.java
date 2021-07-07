package com.wf.recommendationcompiler.service;

import com.wf.recommendationcompiler.entity.RecommendationDetails;
import com.wf.recommendationcompiler.repository.RecommendationDetailsRepository;
import com.wf.contractlib.contracts.GeneratedRecommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class DatabaseHandler {
    private RecommendationDetailsRepository recommendationDetailsRepository;
    private DetailsGenerator detailsGenerator;

    @Autowired
    public void setRecommendationDetailsRepository(RecommendationDetailsRepository recommendationDetailsRepository) {
        this.recommendationDetailsRepository = recommendationDetailsRepository;
    }

    @Autowired
    public void setDetailsGenerator(DetailsGenerator detailsGenerator) {
        this.detailsGenerator = detailsGenerator;
    }

    @Transactional
    public void handle(GeneratedRecommendation generatedRecommendation) {
        RecommendationDetails recommendationDetails = detailsGenerator.generate(generatedRecommendation);

        Optional<RecommendationDetails> details =
                recommendationDetailsRepository.findByCustomerIDAndModelName(
                        recommendationDetails.getCustomerID(),
                        recommendationDetails.getModelName());

        if (details.isPresent()) {
            recommendationDetails.setRecommendationID(details.get().getRecommendationID());
        }

        recommendationDetailsRepository.save(recommendationDetails);
    }
}
