package com.wf.recommendationprovider.repository;

import com.wf.recommendationprovider.entity.CustomerDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerDetailsRepository extends CrudRepository<CustomerDetails, Integer> {
    Optional<CustomerDetails> findByCustomerID(Integer customerID);
}
