package com.wf.recommendationprovider.service;

import com.wf.contractlib.contracts.CompiledRecommendation;
import com.wf.contractlib.contracts.featurevector.FeatureVectorOne;
import com.wf.recommendationprovider.entity.CompiledRec;
import com.wf.recommendationprovider.entity.FeatureVector;
import com.wf.recommendationprovider.repository.CompiledRecRepository;
import com.wf.recommendationprovider.repository.FeatureVectorRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DatabaseHandler {
    private DetailsGenerator detailsGenerator;
    private CompiledRecRepository compiledRecRepository;
    private FeatureVectorRepository featureVectorRepository;

    @Getter @Setter
    private Boolean testVariable= false ;//for testing only

    @Autowired
    public void setDetailsGenerator(DetailsGenerator detailsGenerator) {
        this.detailsGenerator = detailsGenerator;
    }

    @Autowired
    public void setFeatureVectorRepository(FeatureVectorRepository featureVectorRepository) {
        this.featureVectorRepository = featureVectorRepository;
    }

    @Autowired
    public void setCompiledRecRepository(CompiledRecRepository compiledRecRepository) {
        this.compiledRecRepository = compiledRecRepository;
    }

    @Transactional
    public void handle(FeatureVectorOne featureVectorOne) {
        FeatureVector featureVector = detailsGenerator.generate(featureVectorOne);
        Optional<FeatureVector> optionalFeatureVector =
                featureVectorRepository.findByCustomerID(featureVector.getCustomerID());

        if (optionalFeatureVector.isPresent()) {
            FeatureVector temp = optionalFeatureVector.get();
            temp.setPurchaseExpenditureMap(featureVector.getPurchaseExpenditureMap());
            featureVector = temp;
            testVariable=true ;
        }

        System.out.println(featureVector);
        featureVectorRepository.save(featureVector);
    }

    @Transactional
    public void handle(CompiledRecommendation compiledRecommendation) {
        CompiledRec compiledRec = detailsGenerator.generate(compiledRecommendation);

        Optional<CompiledRec> optionalCompiledRec =
                compiledRecRepository.findByCustomerID(compiledRec.getCustomerID());

        if (optionalCompiledRec.isPresent()) {
            CompiledRec temp = optionalCompiledRec.get();
            temp.setCardConfidenceMap(compiledRec.getCardConfidenceMap());
            compiledRec = temp;
            testVariable=true ;
        }

        System.out.println(compiledRec);
        compiledRecRepository.save(compiledRec);
    }
}
