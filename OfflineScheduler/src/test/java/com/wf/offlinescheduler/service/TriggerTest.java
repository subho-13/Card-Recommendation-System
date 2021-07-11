package com.wf.offlinescheduler.service;

import com.wf.contractlib.contracts.AbstractedTransaction;
import com.wf.contractlib.entities.CardType;
import com.wf.contractlib.entities.GeoCoordinate;
import com.wf.contractlib.entities.PurchaseCategory;
import com.wf.offlinescheduler.entity.CustomerDetails;
import com.wf.offlinescheduler.repository.CustomerDetailsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TriggerTest {
    @Mock
    TriggerProducer triggerProducer;
    @Mock
    CustomerDetailsRepository mockRepository;
    @Mock
    AutoCloseable closeable;
    @InjectMocks
    private Trigger trigger;
    private int totalUsers = 0;
    private int numUsersAboveThreshold = 0;
    private int currentThresholdTransactionCount = 8;

    @BeforeEach
    public void setUp() {
        trigger = new Trigger();
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    public AbstractedTransaction createAbstractedTransaction() {
        AbstractedTransaction abstractedTransaction = new AbstractedTransaction();
        abstractedTransaction.setCustomerID(001);
        abstractedTransaction.setCardID(109287);
        abstractedTransaction.setPurchaseCategory(PurchaseCategory.PERSONAL);
        abstractedTransaction.setTransactionAmount(30.34F);
        abstractedTransaction.setCardType(CardType.SHOPPING);
        abstractedTransaction.setTransactionNum("c81755dbbbea9d5c77f094348a7579be");
        abstractedTransaction.setUnixTime(1371816893L);
        abstractedTransaction.setMerchantID(0023);
        abstractedTransaction.setMerchantCoordinate(new GeoCoordinate(40.49581F, -74.196111F));
        return abstractedTransaction;
    }

    @Test
    @DisplayName("send trigger when above threshold")
    public void sendTriggerTest() {

        AbstractedTransaction abstractedTransaction = createAbstractedTransaction();

        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setCustomerID(001);
        customerDetails.setNumTransactions(7);

        when(mockRepository.findById(anyInt())).thenReturn(Optional.of(customerDetails));
        when(mockRepository.countByNumTransactionsGreaterThanEqual(currentThresholdTransactionCount)).thenReturn(100);
        trigger.handle(abstractedTransaction);

//        System.out.println(trigger.getTotalUsers()+" "+trigger.getNumUsersAboveThreshold()+" " +trigger
//        .getCurrentThresholdTransactionCount());
        assertEquals(100, trigger.getNumUsersAboveThreshold());

    }

    @Test
    @DisplayName("trigger is not sent when customer exists but below threshold")
    public void belowThresholdTest() {

        AbstractedTransaction abstractedTransaction = createAbstractedTransaction();

        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setCustomerID(001);
        customerDetails.setNumTransactions(2);

        when(mockRepository.findById(anyInt())).thenReturn(Optional.of(customerDetails));

        trigger.handle(abstractedTransaction);
        assertEquals(0, trigger.getNumUsersAboveThreshold());
        assertEquals(0, trigger.getTotalUsers());

    }

    @Test
    @DisplayName("new customer is added")
    public void newCustomerTest() {
        AbstractedTransaction abstractedTransaction = createAbstractedTransaction();
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setCustomerID(001);
        customerDetails.setNumTransactions(2);

        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());
        trigger.handle(abstractedTransaction);
        assertEquals(1, trigger.getTotalUsers());
    }

}