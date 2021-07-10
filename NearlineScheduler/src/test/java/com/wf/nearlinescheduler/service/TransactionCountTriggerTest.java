package com.wf.nearlinescheduler.service;

import com.wf.contractlib.contracts.AbstractedTransaction;
import com.wf.contractlib.entities.CardType;
import com.wf.contractlib.entities.GeoCoordinate;
import com.wf.contractlib.entities.PurchaseCategory;
import com.wf.nearlinescheduler.entity.CustomerDetails;
import com.wf.nearlinescheduler.repository.CustomerDetailsRepository;
import com.wf.nearlinescheduler.util.Time;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionCountTriggerTest {

    @InjectMocks
    private TransactionCountTrigger transactionCountTrigger ;
    @Mock
    TriggerProducer triggerProducer;
    @Mock
    CustomerDetailsRepository mockRepository;
    @Mock
    AutoCloseable closeable ;

    @BeforeEach
    void setUp() {
        transactionCountTrigger=new TransactionCountTrigger();
        transactionCountTrigger.setTransactionStepCount(2);
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    public AbstractedTransaction createAbstractedTransaction()
    {
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
        return abstractedTransaction ;
    }

    @Test
    public void handleTest()
    {
        AbstractedTransaction abstractedTransaction = createAbstractedTransaction() ;

        CustomerDetails customerDetails = new CustomerDetails() ;
        customerDetails.setCustomerID(001);
        customerDetails.setNumTransactions(1);
        customerDetails.setLastScheduledUnixTime(Time.getCurrentTimeInSecs());

        when(mockRepository.findByCustomerID(anyInt())).thenReturn(Optional.of(customerDetails)) ;
        transactionCountTrigger.handle(abstractedTransaction);
        verify(triggerProducer,times(1)).produce(any()) ;
        verify(mockRepository,times(1)).save(any()) ;
    }

}