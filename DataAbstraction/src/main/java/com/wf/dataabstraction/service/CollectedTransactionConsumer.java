package com.wf.dataabstraction.service;

import com.wf.contractlib.contracts.AbstractedTransaction;
import com.wf.contractlib.contracts.CollectedTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CollectedTransactionConsumer {
    @Autowired
    DatabaseHandler databaseHandler;

    @Autowired
    private TransactionConverter transactionConverter;

    @Autowired
    private AbstractedTransactionProducer abstractedTransactionProducer;

    @KafkaListener(groupId = "DataAbstraction", topics = "CollectedTransaction",
            containerFactory = "collectedTransactionListenerFactory")
    public void consumeCollectedTransaction(CollectedTransaction collectedTransaction) {
        Map<String, Integer> mapDetailsID =
                databaseHandler.saveTransactionDetails(collectedTransaction);
        AbstractedTransaction abstractedTransaction =
                transactionConverter.convert(collectedTransaction, mapDetailsID);
        abstractedTransactionProducer.produce(abstractedTransaction);
    }
}
