package com.wf.recommendationprovider.controller;

import com.wf.contractlib.contracts.ProvidedRecommendation;
import com.wf.contractlib.entities.CardType;
import com.wf.recommendationprovider.entity.CustomerDetails;
import com.wf.recommendationprovider.repository.CustomerDetailsRepository;
import com.wf.recommendationprovider.service.ComplimentaryCardProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class Controller {
    private CustomerDetailsRepository customerDetailsRepository;
    private ComplimentaryCardProvider complimentaryCardProvider;

    @Autowired
    public void setCustomerDetailsRepository(CustomerDetailsRepository customerDetailsRepository) {
        this.customerDetailsRepository = customerDetailsRepository;
    }

    @Autowired
    public void setComplimentaryCardProvider(ComplimentaryCardProvider complimentaryCardProvider) {
        this.complimentaryCardProvider = complimentaryCardProvider;
    }

    @GetMapping("/get/{customerID}")
    public ProvidedRecommendation getRecommendation(@PathVariable Integer customerID) {
        Optional<CustomerDetails> optionalCustomerDetails = customerDetailsRepository.findByCustomerID(customerID);

        if (optionalCustomerDetails.isEmpty()) {
            return null;
        }

        CustomerDetails customerDetails = optionalCustomerDetails.get();

        ProvidedRecommendation providedRecommendation = new ProvidedRecommendation();
        providedRecommendation.setPurchaseExpenditureMap(customerDetails.getPurchaseExpenditureMap());
        providedRecommendation.setCardConfidenceMap(customerDetails.getCardConfidenceMap());

        List<CardType> cards = getFinalRecommendation(customerDetails.getCardConfidenceMap());

        if(cards.size() == 1) {
            CardType card = cards.get(0);

            if (card == customerDetails.getCardType()) {
                providedRecommendation.setComplimentaryCard(true);
                card = complimentaryCardProvider.getComplimentaryCard(customerDetails);
            }

            cards.add(0, card);
        }

        providedRecommendation.setCards(cards);

        return providedRecommendation;
    }

    private List<CardType> getFinalRecommendation(Map<CardType, Float> cardConfidenceMap) {
        float confidenceScore = 0;

        for(Map.Entry<CardType, Float> cardConfidenceEntry : cardConfidenceMap.entrySet()) {
            if (cardConfidenceEntry.getValue() > confidenceScore) {
                confidenceScore = cardConfidenceEntry.getValue();
            }
        }

        List<CardType> cards = new ArrayList<>();

        for(Map.Entry<CardType, Float> cardConfidenceEntry : cardConfidenceMap.entrySet()) {
            if (cardConfidenceEntry.getValue() == confidenceScore) {
                cards.add(cardConfidenceEntry.getKey());
            }
        }

        return cards;
    }
}
