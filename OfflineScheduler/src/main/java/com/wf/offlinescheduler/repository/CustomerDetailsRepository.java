package com.wf.offlinescheduler.repository;

import com.wf.offlinescheduler.entity.CustomerDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDetailsRepository extends CrudRepository<CustomerDetails, Integer> {
    Optional<CustomerDetails> findByCustomerID(Integer customerID);
    int countByNumTransactionsGreaterThanEqual(int thresholdNumTransactions);
}

