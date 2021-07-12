package com.wf.offlinescheduler.service;

import com.wf.contractlib.contracts.AbstractedTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionConsumer {
    private Trigger trigger;

    @Autowired
    public void setTransactionCountTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    @KafkaListener(groupId = "OfflineScheduler", topics = "AbstractedTransaction",
            containerFactory = "abstractedTransactionListenerFactory")
    public void consumeAbstractedTransaction(AbstractedTransaction abstractedTransaction) {
        trigger.handle(abstractedTransaction);
    }
}
