package com.wf.featureextractionone.service;

import com.wf.contractlib.contracts.AbstractedTransaction;
import com.wf.contractlib.contracts.featurevector.FeatureVectorOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AbstractedTransactionConsumer {
    private DatabaseHandler databaseHandler;
    private FeatureVectorOneProducer featureVectorOneProducer;

    @Autowired
    public void setDatabaseHandler(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    @Autowired
    public void setFeatureVectorOneProducer(FeatureVectorOneProducer featureVectorOneProducer) {
        this.featureVectorOneProducer = featureVectorOneProducer;
    }

    @KafkaListener(groupId = "FeatureExtractionOne", topics = "AbstractedTransaction",
            containerFactory = "abstractedTransactionListenerFactory")
    public void consume(AbstractedTransaction abstractedTransaction) {
        FeatureVectorOne featureVectorOne = databaseHandler.handle(abstractedTransaction);
        featureVectorOneProducer.produce(featureVectorOne);
    }
}
