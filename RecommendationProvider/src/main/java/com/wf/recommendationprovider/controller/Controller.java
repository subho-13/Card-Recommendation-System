package com.wf.recommendationprovider.controller;

import com.wf.contractlib.contracts.ProvidedRecommendation;
import com.wf.contractlib.entities.CardType;
import com.wf.recommendationprovider.entity.CompiledRec;
import com.wf.recommendationprovider.entity.FeatureVector;
import com.wf.recommendationprovider.repository.CompiledRecRepository;
import com.wf.recommendationprovider.repository.FeatureVectorRepository;
import com.wf.recommendationprovider.service.ComplimentaryCardProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class Controller {
    private CompiledRecRepository compiledRecRepository;
    private FeatureVectorRepository featureVectorRepository;
    private ComplimentaryCardProvider complimentaryCardProvider;

    @Autowired
    public void setComplimentaryCardProvider(ComplimentaryCardProvider complimentaryCardProvider) {
        this.complimentaryCardProvider = complimentaryCardProvider;
    }

    @Autowired
    public void setCompiledRecRepository(CompiledRecRepository compiledRecRepository) {
        this.compiledRecRepository = compiledRecRepository;
    }

    @Autowired
    public void setFeatureVectorRepository(FeatureVectorRepository featureVectorRepository) {
        this.featureVectorRepository = featureVectorRepository;
    }

    @GetMapping("/get/{customerID}")
    @CrossOrigin(origins = "*")
    public ProvidedRecommendation getRecommendation(@PathVariable Integer customerID) {
        Optional<CompiledRec> optionalCompiledRec = compiledRecRepository.findByCustomerID(customerID);
        Optional<FeatureVector> optionalFeatureVector = featureVectorRepository.findByCustomerID(customerID);

        if (optionalFeatureVector.isEmpty() || optionalCompiledRec.isEmpty()) {
            return null;
        }

        FeatureVector featureVector = optionalFeatureVector.get();
        CompiledRec compiledRec = optionalCompiledRec.get();

        ProvidedRecommendation providedRecommendation = new ProvidedRecommendation();
        providedRecommendation.setExistingCard(featureVector.getCardType());
        providedRecommendation.setCreditScore(featureVector.getCreditScore());
        providedRecommendation.setJob(featureVector.getJob());
        providedRecommendation.setPurchaseExpenditureMap(featureVector.getPurchaseExpenditureMap());
        providedRecommendation.setCardConfidenceMap(compiledRec.getCardConfidenceMap());

        List<CardType> cards = getFinalRecommendation(providedRecommendation.getCardConfidenceMap());

        if (cards.size() == 1) {
            CardType card = cards.get(0);

            if (card == featureVector.getCardType()) {
                providedRecommendation.setComplimentaryCard(true);
                card = complimentaryCardProvider.getComplimentaryCard(featureVector);
                cards.remove(0);
                cards.add(card);
                System.out.println("Customer ID :: " + featureVector.getCustomerID());
            }
        }

        providedRecommendation.setCards(cards);
        return providedRecommendation;
    }

    private List<CardType> getFinalRecommendation(Map<CardType, Float> cardConfidenceMap) {
        float confidenceScore = 0;

        for (Map.Entry<CardType, Float> cardConfidenceEntry : cardConfidenceMap.entrySet()) {
            if (cardConfidenceEntry.getValue() > confidenceScore) {
                confidenceScore = cardConfidenceEntry.getValue();
            }
        }

        List<CardType> cards = new ArrayList<>();

        for (Map.Entry<CardType, Float> cardConfidenceEntry : cardConfidenceMap.entrySet()) {
            if (cardConfidenceEntry.getValue() == confidenceScore) {
                cards.add(cardConfidenceEntry.getKey());
            }
        }

        return cards;
    }
}
