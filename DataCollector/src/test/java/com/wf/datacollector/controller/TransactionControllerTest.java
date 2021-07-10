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

    @Test
    public void consumeTransactionTest() throws Exception {
        InboundTransaction transaction = new InboundTransaction() ;
        transaction.setCard_num("9876543219876543");
        transaction.setMerchant("Sporer-Keebler");
        transaction.setPurchase_category("Personal");
        transaction.setTrans_amt(30.34F);
        transaction.setFirst("Ashley");
        transaction.setLast("Lopez");
        transaction.setGender("F");
        transaction.setCard_type("Shopping");
        transaction.setCredit_score(210);
        transaction.setStreet("9333 Valentine Point");
        transaction.setCity("Bellmore");
        transaction.setState("NY");
        transaction.setZip("11710");
        transaction.setLongitude(40.6729F);
        transaction.setLatitude(-73.5365F);
        transaction.setCity_pop(34496);
        transaction.setJob("manager");
        transaction.setDob("10-12-98");
        transaction.setTrans_num("c81755dbbbea9d5c77f094348a7579be");
        transaction.setUnix_time(1371816893L);
        transaction.setMerch_lat(40.49581F);
        transaction.setMerch_long(-74.196111F);

        when(collectedTransactionProducer.produce(transaction)).thenReturn(false) ;

        mockMvc.perform(
                MockMvcRequestBuilders.post("/transaction").content())

                .andExpect(status().isOk())
                .andR

        verify(collectedTransactionProducer,times(1)).produce(transaction) ;

    }

}
