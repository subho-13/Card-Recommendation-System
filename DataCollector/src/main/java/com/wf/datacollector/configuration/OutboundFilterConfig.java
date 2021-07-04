package com.wf.datacollector.configuration;

import com.wf.datacollector.filter.outbound.OutboundFilter;
import com.wf.datacollector.filter.outbound.OutboundFilterUnknown;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OutboundFilterConfig {
    @Bean
    public OutboundFilter getOutboundFilterChain() {
        OutboundFilter filterone = new OutboundFilterUnknown();
        return filterone;
    }
}