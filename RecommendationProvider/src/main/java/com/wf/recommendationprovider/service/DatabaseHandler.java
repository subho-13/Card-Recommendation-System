package com.wf.recommendationprovider.service;

import com.wf.contractlib.contracts.CompiledRecommendation;
import com.wf.contractlib.contracts.featurevector.FeatureVectorOne;
import com.wf.recommendationprovider.entity.CustomerDetails;
import com.wf.recommendationprovider.repository.CustomerDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DatabaseHandler {
    private DetailsGenerator detailsGenerator;
    private CustomerDetailsRepository customerDetailsRepository;

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

        if (optionalCustomerDetails.isPresent()) {
            customerDetails.setCardConfidenceMap(optionalCustomerDetails.get().getCardConfidenceMap());
        }

        customerDetailsRepository.save(customerDetails);
    }

    @Transactional
    public void handle(CompiledRecommendation compiledRecommendation) {
        CustomerDetails customerDetails = detailsGenerator.generate(compiledRecommendation);

        Optional<CustomerDetails> optionalCustomerDetails =
                customerDetailsRepository.findByCustomerID(customerDetails.getCustomerID());

        if (optionalCustomerDetails.isPresent()) {
            CustomerDetails temp =
                    optionalCustomerDetails.get();
            temp.setCardConfidenceMap(customerDetails.getCardConfidenceMap());
            customerDetails = temp;
        }

        customerDetailsRepository.save(customerDetails);
    }
}
