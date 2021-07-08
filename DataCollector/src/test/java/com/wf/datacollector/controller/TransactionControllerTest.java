package com.wf.datacollector.controller;

import com.wf.contractlib.contracts.CollectedTransaction;
import com.wf.datacollector.entity.InboundTransaction;
import com.wf.datacollector.filter.inbound.InboundFilter;
import com.wf.datacollector.service.CollectedTransactionProducer;
import com.wf.datacollector.service.TransactionConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private InboundFilter inboundFilterChain;

    @MockBean
    private TransactionConverter transactionConverter;

    @MockBean
    private CollectedTransactionProducer collectedTransactionProducer;

    @Autowired
    protected MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

//    @Test
//    public void consumeTransactionTest() throws Exception {
//        InboundTransaction inboundTransaction = new InboundTransaction();
//        when(collectedTransactionProducer.produce(inboundTransaction)).thenReturn(false) ;
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/transaction"))
//                .andExpect(status().isOk())
//                .andExpect() ;
//        )
//    }
    @Test
    public void shouldFilterOutDebitCardTransactions() throws Exception {
        InboundTransaction inboundTransaction = new InboundTransaction();
        when(inboundFilterChain.isOk(inboundTransaction)).thenReturn(false);

        CollectedTransaction collectedTransaction = new CollectedTransaction();
        when(transactionConverter.convert(inboundTransaction)).thenReturn(collectedTransaction);
        verify(transactionConverter, atMostOnce()).convert(inboundTransaction);
    }
}
