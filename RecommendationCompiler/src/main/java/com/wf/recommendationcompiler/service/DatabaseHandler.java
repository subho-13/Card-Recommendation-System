package com.wf.recommendationcompiler.service;

import com.wf.recommendationcompiler.entity.RecommendationDetails;
import com.wf.recommendationcompiler.repository.RecommendationDetailsRepository;
import com.wf.contractlib.contracts.GeneratedRecommendation;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class DatabaseHandler {
    private RecommendationDetailsRepository recommendationDetailsRepository;
    private DetailsGenerator detailsGenerator;
    private final AtomicBoolean isNewDataAvailable = new AtomicBoolean(false);

    @Getter //variable added just for testing
    private Boolean testVariable = false ;

    @Autowired
    public void setRecommendationDetailsRepository(RecommendationDetailsRepository recommendationDetailsRepository) {
        this.recommendationDetailsRepository = recommendationDetailsRepository;
    }

    @Autowired
    public void setDetailsGenerator(DetailsGenerator detailsGenerator) {
        this.detailsGenerator = detailsGenerator;
    }

    public boolean getIsNewDataAvailable() {
        return isNewDataAvailable.get();
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
            testVariable=true ;
        }

        recommendationDetailsRepository.save(recommendationDetails);
        isNewDataAvailable.set(true);
    }

    @Transactional
    public List<RecommendationDetails> getAllRecommendationDetails() {
        isNewDataAvailable.set(false);
        return recommendationDetailsRepository.findAll();
    }
}
