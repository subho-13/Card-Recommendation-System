package com.wf.datacollector.service;


import com.wf.datacollector.filter.inbound.InboundFilter;
import com.wf.datacollector.filter.outbound.OutboundFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionConverterTest {
    private OutboundFilter outboundFilterChain;
    private InboundFilter inboundFilter;

    @Autowired
    public void setOutboundFilterChain(OutboundFilter outboundFilterChain) {
        this.outboundFilterChain = outboundFilterChain;
    }

    @Autowired
    public void setInboundFilter(InboundFilter inboundFilter) {
        this.inboundFilter = inboundFilter;
    }

    @Test
    @DisplayName("Should return true since the Inbound Transaction was Okay")
    public void testProduceIsTrue() {

    }
}
