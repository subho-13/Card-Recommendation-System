package com.wf.nearlinescheduler.service;

import com.wf.nearlinescheduler.entity.CustomerDetails;
import com.wf.nearlinescheduler.repository.CustomerDetailsRepository;
import com.wf.nearlinescheduler.util.Time;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class LastScheduledTriggerTest {

    @InjectMocks
    private LastScheduledTrigger lastScheduledTrigger ;

    @Mock
    ApplicationContext applicationContext ;
    @Mock
    Thread thread  ;
    @Mock
    TriggerProducer triggerProducer;
    @Mock
    CustomerDetailsRepository mockRepository;
    @Mock
    AutoCloseable closeable ;
    @BeforeEach
    void setUp() {
        lastScheduledTrigger= new LastScheduledTrigger(applicationContext) ;
        closeable= MockitoAnnotations.openMocks(this) ;
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("")
    public void runTest()
    {
        List<CustomerDetails> customerDetailsList = new ArrayList<>();

        CustomerDetails customer1 = new CustomerDetails() ;
        customer1.setCustomerID(001);
        customer1.setNumTransactions(1);
        customer1.setLastScheduledUnixTime(Time.getCurrentTimeInSecs());

        CustomerDetails customer2 = new CustomerDetails() ;
        customer2.setCustomerID(002);
        customer2.setNumTransactions(1);
        customer2.setLastScheduledUnixTime(Time.getCurrentTimeInSecs());

        customerDetailsList.add(customer1);
        customerDetailsList.add(customer2) ;

        when(mockRepository.findByLastScheduledUnixTimeLessThan(anyLong())).thenReturn(customerDetailsList) ;
        lastScheduledTrigger.run();
        verify(triggerProducer,times(2)).produce(any());
        verify(mockRepository,times(2)).save(any());

    }

    }
