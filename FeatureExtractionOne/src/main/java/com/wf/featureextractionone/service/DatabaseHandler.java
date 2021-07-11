package com.wf.featureextractionone.service;

import com.wf.contractlib.contracts.AbstractedTransaction;
import com.wf.contractlib.contracts.featurevector.FeatureVectorOne;
import com.wf.featureextractionone.entity.FeatureVector;
import com.wf.featureextractionone.repository.FeatureVectorRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DatabaseHandler {
    private FeatureVectorRepository featureVectorRepository;
    private DetailsGenerator detailsGenerator;

    @Autowired
    public void setFeatureVectorRepository(FeatureVectorRepository featureVectorRepository) {
        this.featureVectorRepository = featureVectorRepository;
    }

    @Autowired
    public void setDetailsGenerator(DetailsGenerator detailsGenerator) {
        this.detailsGenerator = detailsGenerator;
    }

    public FeatureVectorOne handle(AbstractedTransaction abstractedTransaction) {
        FeatureVector featureVector = getFeatureVector(abstractedTransaction);
        return detailsGenerator.convert(featureVector);
    }

    @NotNull
    @Transactional
    public FeatureVector getFeatureVector(AbstractedTransaction abstractedTransaction) {
        Optional<FeatureVector> optionalFeatureVector =
                featureVectorRepository.findByCustomerID(abstractedTransaction.getCustomerID());

        FeatureVector featureVector;
        if (optionalFeatureVector.isPresent()) {
            featureVector = optionalFeatureVector.get();
            featureVector = detailsGenerator.createUpdatedFeatureVector(featureVector, abstractedTransaction);
        } else {
            featureVector = detailsGenerator.createNewFeatureVector(abstractedTransaction);
        }

        featureVectorRepository.save(featureVector);
        detailsGenerator.updateMeanPurchaseExpenditureMap(abstractedTransaction);
        return featureVector;
    }
}
