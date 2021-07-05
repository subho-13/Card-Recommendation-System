package com.wf.dataabstraction.repository;

import com.wf.dataabstraction.entity.MerchantDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantDetailsRepository extends CrudRepository<MerchantDetails, Integer> {
    Optional<MerchantDetails> findByMerchantName(String name);
}

