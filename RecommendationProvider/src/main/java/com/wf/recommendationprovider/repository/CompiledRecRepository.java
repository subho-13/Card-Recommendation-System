package com.wf.recommendationprovider.repository;

import com.wf.recommendationprovider.entity.CompiledRec;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompiledRecRepository extends CrudRepository<CompiledRec, Integer> {
    Optional<CompiledRec> findByCustomerID(Integer customerID);
}
