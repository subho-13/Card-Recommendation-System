package com.wf.recommendationcompiler.repository;

import com.wf.recommendationcompiler.entity.RecommendationDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecommendationDetailsRepository extends
        CrudRepository<RecommendationDetails, Integer> {
    List<RecommendationDetails> findByCustomerID(Integer customerID);
    Optional<RecommendationDetails> findByCustomerIDAndModelName(Integer customerID,
                                                                 String modelName);
}
