package com.wf.nearlinescheduler.service;

import com.wf.contractlib.contracts.AbstractedTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionConsumer {
    private TransactionCountTrigger transactionCountTrigger;

    @Autowired
    public void setTransactionCountTrigger(TransactionCountTrigger transactionCountTrigger) {
        this.transactionCountTrigger = transactionCountTrigger;
    }

    @KafkaListener(groupId = "NearlineScheduler", topics = "AbstractedTransaction",
            containerFactory = "abstractedTransactionListenerFactory")
    public void consumeAbstractedTransaction(AbstractedTransaction abstractedTransaction) {
        transactionCountTrigger.handle(abstractedTransaction);
    }
}
