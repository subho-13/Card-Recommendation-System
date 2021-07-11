package com.wf.featureextractionone.repository;

import com.wf.featureextractionone.entity.FeatureVector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeatureVectorRepository extends CrudRepository<FeatureVector, Integer> {
    Optional<FeatureVector> findByCustomerID(Integer customerID);
}
