package com.wf.dataabstraction.repository;

import com.wf.contractlib.entities.Gender;
import com.wf.dataabstraction.entity.CustomerDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface CustomerDetailsRepository extends CrudRepository<CustomerDetails, Integer> {

    Optional<CustomerDetails> findByFirstNameAndLastNameAndGenderAndDob(
            String firstName, String lastName, Gender gender, Date dob
    );

    Optional<CustomerDetails> findByCustomerID(Integer customerID);
}
