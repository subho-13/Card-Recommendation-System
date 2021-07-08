package com.wf.offlinescheduler.service;

import com.wf.contractlib.contracts.AbstractedTransaction;
import com.wf.contractlib.entities.*;
import com.wf.offlinescheduler.entity.CustomerDetails;
import com.wf.offlinescheduler.repository.CustomerDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TriggerTest {
   @InjectMocks
    private Trigger trigger ;

    @MockBean
    TriggerProducer triggerProducer;
    @MockBean
    CustomerDetailsRepository mockRepository;

    @BeforeEach
    public void setUp(){
        trigger = new Trigger() ;
        MockitoAnnotations.initMocks(this);
    }

    private double percentThreshold = 10;

    private int transactionStepCount=2;

    private int totalUsers = 10;
    private int numUsersAboveThreshold = 99;
    private int currentThresholdTransactionCount = 8;

    @Test
    @DisplayName("send trigger when above threshold")
    public void handleTransactionTest(){

        AbstractedTransaction abstractedTransaction= new AbstractedTransaction() ;
        abstractedTransaction.setCustomerID(001);
        abstractedTransaction.setCardID(109287);
        abstractedTransaction.setPurchaseCategory(PurchaseCategory.PERSONAL);
        abstractedTransaction.setTransactionAmount(30.34F);
        abstractedTransaction.setCardType(CardType.SHOPPING);
        abstractedTransaction.setTransactionNum("c81755dbbbea9d5c77f094348a7579be");
        abstractedTransaction.setUnixTime(1371816893L);
        abstractedTransaction.setMerchantID(0023);
        abstractedTransaction.setMerchantCoordinate(new GeoCoordinate(40.49581F,-74.196111F));

        CustomerDetails customerDetails = new CustomerDetails() ;
        customerDetails.setCustomerID(001);
        customerDetails.setNumTransactions(7);

        doReturn(customerDetails).when(mockRepository).save(customerDetails) ;
//        when(mockRepository.save(customerDetails)).thenReturn(customerDetails) ;
        when(mockRepository.findById(anyInt())).thenReturn(Optional.of(customerDetails)) ;
        when(mockRepository.countByNumTransactionsGreaterThanEqual(currentThresholdTransactionCount)).thenReturn(100) ;
        trigger.handle(abstractedTransaction);
        verify(triggerProducer,atLeastOnce()) ;

    }
}