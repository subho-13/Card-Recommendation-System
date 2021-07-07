package com.wf.nearlinescheduler.repository;

import com.wf.nearlinescheduler.entity.CustomerDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerDetailsRepository extends CrudRepository<CustomerDetails, Integer> {
    Optional<CustomerDetails> findByCustomerID(Integer customerID);
    List<CustomerDetails> findByLastScheduledUnixTimeLessThan(Long timeBefore);
}
