package com.wf.offlinescheduler.service;

import com.wf.contractlib.contracts.AbstractedTransaction;
import com.wf.contractlib.contracts.OfflineTrigger;
import com.wf.offlinescheduler.entity.CustomerDetails;
import com.wf.offlinescheduler.repository.CustomerDetailsRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class Trigger {
    private TriggerProducer triggerProducer;
    private CustomerDetailsRepository customerDetailsRepository;

    @Value("${percent-threshold}")
    private double percentThreshold;

    @Value("${transaction-step-count}")
    private int transactionStepCount;

    private int totalUsers = 0;

    private int numUsersAboveThreshold = 0;

    private int currentThresholdTransactionCount = 10;

    @Transactional
    public void handle(AbstractedTransaction abstractedTransaction) {
        CustomerDetails customerDetails =
                new CustomerDetails(abstractedTransaction.getCustomerID(), 1);

        Optional<CustomerDetails> optionalCustomerDetails =
                customerDetailsRepository.findById(customerDetails.getCustomerID());

        if (optionalCustomerDetails.isPresent()) {
            customerDetails = optionalCustomerDetails.get();
            customerDetails.setNumTransactions(customerDetails.getNumTransactions() + 1);

            if (customerDetails.getNumTransactions() == currentThresholdTransactionCount) {
                numUsersAboveThreshold++;
                sendTrigger();
            }

        } else {
            totalUsers++;
        }

        customerDetailsRepository.save(customerDetails);
    }

    private void sendTrigger() {
        if (numUsersAboveThreshold >= percentThreshold * totalUsers) {
            OfflineTrigger offlineTrigger = new OfflineTrigger();
            triggerProducer.produce(offlineTrigger);

            currentThresholdTransactionCount += transactionStepCount;
            numUsersAboveThreshold =
                    customerDetailsRepository.countByNumTransactionsGreaterThanEqual(currentThresholdTransactionCount);
        }
    }

    @Autowired
    public void setCustomerDetailsRepository(CustomerDetailsRepository customerDetailsRepository) {
        this.customerDetailsRepository = customerDetailsRepository;
    }

    @Autowired
    public void setTriggerProducer(TriggerProducer triggerProducer) {
        this.triggerProducer = triggerProducer;
    }
}
