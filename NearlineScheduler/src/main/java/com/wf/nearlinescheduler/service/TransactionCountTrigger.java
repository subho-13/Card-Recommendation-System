package com.wf.nearlinescheduler.service;

import com.wf.contractlib.contracts.AbstractedTransaction;
import com.wf.contractlib.contracts.NearlineTrigger;
import com.wf.nearlinescheduler.entity.CustomerDetails;
import com.wf.nearlinescheduler.repository.CustomerDetailsRepository;
import com.wf.nearlinescheduler.util.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Optional;

public class TransactionCountTrigger {
    private CustomerDetailsRepository customerDetailsRepository;
    private TriggerProducer triggerProducer;

    @Value("${transaction-step-count}")
    private int transactionStepCount;

    @Autowired
    public void setCustomerDetailsRepository(CustomerDetailsRepository customerDetailsRepository) {
        this.customerDetailsRepository = customerDetailsRepository;
    }

    @Autowired
    public void setTriggerProducer(TriggerProducer triggerProducer) {
        this.triggerProducer = triggerProducer;
    }

    private CustomerDetails getNewCustomer(AbstractedTransaction abstractedTransaction) {
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setCustomerID(abstractedTransaction.getCustomerID());
        customerDetails.setNumTransactions(1);
        customerDetails.setLastScheduledUnixTime(Time.getCurrentTimeInSecs());
        return customerDetails;
    }

    public void updateDatabase(AbstractedTransaction abstractedTransaction) {
        CustomerDetails customerDetails = getNewCustomer(abstractedTransaction);

        Optional<CustomerDetails> customerDetailsOptional =
                customerDetailsRepository.findByCustomerID(customerDetails.getCustomerID());

        if (customerDetailsOptional.isPresent()) {
            customerDetails = customerDetailsOptional.get();
            int numTransactions = customerDetails.getNumTransactions() + 1;

            customerDetails.setNumTransactions(numTransactions);

            if (numTransactions%transactionStepCount == 0) {
                NearlineTrigger nearlineTrigger = new NearlineTrigger();
                nearlineTrigger.setCustomerID(customerDetails.getCustomerID());

                triggerProducer.produceScheduler(nearlineTrigger);

                customerDetails.setLastScheduledUnixTime(Time.getCurrentTimeInSecs());
            }
        }

        customerDetailsRepository.save(customerDetails);
    }
}
