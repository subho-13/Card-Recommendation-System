package com.wf.dataabstraction.repository;

import com.wf.dataabstraction.entity.AddressDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDetailsRepository extends CrudRepository<AddressDetails, Integer> {
}
