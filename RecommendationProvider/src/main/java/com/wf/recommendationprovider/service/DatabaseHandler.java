package com.wf.recommendationprovider.service;

import com.wf.contractlib.contracts.CompiledRecommendation;
import com.wf.contractlib.contracts.featurevector.FeatureVectorOne;
import com.wf.recommendationprovider.entity.CustomerDetails;
import com.wf.recommendationprovider.repository.CustomerDetailsRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DatabaseHandler {
    private DetailsGenerator detailsGenerator;
    private CustomerDetailsRepository customerDetailsRepository;

    @Getter @Setter
    private Boolean testVariable = false ;

    @Autowired
    public void setDetailsGenerator(DetailsGenerator detailsGenerator) {
        this.detailsGenerator = detailsGenerator;
    }

    @Autowired
    public void setCustomerDetailsRepository(CustomerDetailsRepository customerDetailsRepository) {
        this.customerDetailsRepository = customerDetailsRepository;
    }

    @Transactional
    public void handle(FeatureVectorOne featureVectorOne) {
        CustomerDetails customerDetails = detailsGenerator.generate(featureVectorOne);

        Optional<CustomerDetails> optionalCustomerDetails =
                customerDetailsRepository.findByCustomerID(customerDetails.getCustomerID());

        optionalCustomerDetails.ifPresent(details -> {
            customerDetails.setCardConfidenceMap(details.getCardConfidenceMap())  ;
            testVariable =true ; //to test only
        });

        customerDetailsRepository.save(customerDetails);
    }

    @Transactional
    public void handle(CompiledRecommendation compiledRecommendation) {
        CustomerDetails customerDetails = detailsGenerator.generate(compiledRecommendation);

        Optional<CustomerDetails> optionalCustomerDetails =
                customerDetailsRepository.findByCustomerID(customerDetails.getCustomerID());

        if (optionalCustomerDetails.isPresent()) {
            optionalCustomerDetails.get().setCardConfidenceMap(customerDetails.getCardConfidenceMap());
            customerDetails = optionalCustomerDetails.get();
            testVariable=true ; //for testing
        }

        customerDetailsRepository.save(customerDetails);
    }
}
