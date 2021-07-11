package com.wf.datacollector.controller;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private InboundFilter inboundFilterChain;
    @MockBean
    private TransactionConverter transactionConverter;
    @MockBean
    private CollectedTransactionProducer collectedTransactionProducer;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void consumeTransactionTest() throws Exception {
        InboundTransaction inboundTransaction = new InboundTransaction();
        when(collectedTransactionProducer.produce(inboundTransaction)).thenReturn(false);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/transaction").content("{\"trans_date_trans_time\":\"2019-01-01 " +
                        "00:00:18\",\"card_num\":2703186189652095,\"merchant\":\"fraud_Rippin, Kub and Mann\"," +
                        "\"purchase_category\":\"Misc_net\",\"trans_amt\":4.97,\"first\":\"Jennifer\"," +
                        "\"last\":\"Banks\",\"gender\":\"F\",\"job\":\"doctor\",\"card_type\":\"Visa Signature\"," +
                        "\"credit_score\":690,\"street\":\"561 Perry Cove\",\"city\":\"Moravian Falls\"," +
                        "\"state\":\"NC\",\"zip\":28654,\"lat\":36.0788,\"long\":-81.1781,\"city_pop\":3495," +
                        "\"dob\":\"1988-03-09\",\"trans_num\":\"0b242abb623afc578575680df30655b9\"," +
                        "\"unix_time\":1325376018,\"merch_lat\":36.011293,\"merch_long\":-82.048315,\"is_fraud\":0," +
                        "\"age\":32}").contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void badRequestTest() throws Exception {
        InboundTransaction inboundTransaction = new InboundTransaction();
        when(collectedTransactionProducer.produce(inboundTransaction)).thenReturn(false);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/transaction").content("").contentType("application/json"))
                .andExpect(status().isBadRequest());
    }
}
