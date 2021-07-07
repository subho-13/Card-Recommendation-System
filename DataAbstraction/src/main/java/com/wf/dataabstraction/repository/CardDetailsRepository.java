package com.wf.dataabstraction.repository;

import com.wf.dataabstraction.entity.CardDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardDetailsRepository extends CrudRepository<CardDetails, Integer> {
    Optional<CardDetails> findByCardNum(String cardNum);
    Optional<CardDetails> findByCardID(Integer cardID);
}