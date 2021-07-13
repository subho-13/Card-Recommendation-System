package com.wf.recommendationcompiler.service;

import com.wf.contractlib.contracts.CompiledRecommendation;
import com.wf.contractlib.entities.CardType;
import com.wf.recommendationcompiler.entity.RecommendationDetails;
import com.wf.recommendationcompiler.repository.RecommendationDetailsRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompiledRecommendationProducer implements DisposableBean, Runnable {
    private FinalRecommendationCompiler finalRecommendationCompiler;
    private RecommendationDetailsRepository recommendationDetailsRepository;
    private DatabaseHandler databaseHandler;
    private KafkaTemplate<String, CompiledRecommendation> kafkaTemplate;
    private Thread thread;

    @Value("${sleeptime-ms}")
    private int sleepTime;

    @Value("${initial-sleeptime-ms}")
    private int initialSleepTime;

    @Value("${producer-topic}")
    private String topic;

    @Autowired
    public CompiledRecommendationProducer(FinalRecommendationCompiler finalRecommendationCompiler,
                                          RecommendationDetailsRepository recommendationDetailsRepository,
                                          DatabaseHandler databaseHandler,
                                          KafkaTemplate<String,
            CompiledRecommendation> kafkaTemplate) {
        this.finalRecommendationCompiler = finalRecommendationCompiler;
        this.recommendationDetailsRepository = recommendationDetailsRepository;
        this.databaseHandler = databaseHandler;
        this.kafkaTemplate = kafkaTemplate;

        this.thread = new Thread(this);
        this.thread.start();
    }


    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(initialSleepTime);
        while (true) {
            Thread.sleep(sleepTime);

            if (!databaseHandler.getIsNewDataAvailable()) {
                continue;
            }

             List<RecommendationDetails> recommendationDetailsList = databaseHandler.getAllRecommendationDetails();
            Map<Integer, Map<String, Map<CardType, Float>>> customerModelCardConfidenceMap =
                    getCustomerModelCardConfidenceMap(recommendationDetailsList);

            for(Map.Entry<Integer, Map<String, Map<CardType, Float>>> entry :
            customerModelCardConfidenceMap.entrySet()) {
                CompiledRecommendation compiledRecommendation = new CompiledRecommendation();
                compiledRecommendation.setCustomerID(entry.getKey());
                Map<String, Map<CardType, Float>> modelCardConfidenceMap = entry.getValue();
                Map<CardType, Float> finalCardConfidenceMap =
                        finalRecommendationCompiler.getCardConfidenceMap(modelCardConfidenceMap);

                compiledRecommendation.setCardConfidenceMap(finalCardConfidenceMap);
                this.kafkaTemplate.send(topic, compiledRecommendation);
            }
        }
    }

    private Map<Integer, Map<String, Map<CardType, Float>>> getCustomerModelCardConfidenceMap(List<RecommendationDetails> recommendationDetailsList) {
        Map<Integer, Map<String, Map<CardType, Float>>> customerModelCardConfidenceMap = new HashMap<>();

        for (RecommendationDetails recommendationDetails: recommendationDetailsList) {
            Integer customerID = recommendationDetails.getCustomerID();
            String modelName = recommendationDetails.getModelName();
            Map<CardType, Float> cardConfidenceMap = recommendationDetails.getCardConfidenceMap();

            if (!customerModelCardConfidenceMap.containsKey(customerID)) {
                customerModelCardConfidenceMap.put(customerID, new HashMap<>());
            }

            customerModelCardConfidenceMap.get(customerID).put(modelName, cardConfidenceMap);
        }

        return customerModelCardConfidenceMap;
    }

    @Override
    public void destroy() throws Exception {

    }
}
