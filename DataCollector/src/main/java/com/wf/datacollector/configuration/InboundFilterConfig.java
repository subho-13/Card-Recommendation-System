package com.wf.datacollector.configuration;

import com.wf.datacollector.filter.inbound.InboundFilter;
import com.wf.datacollector.filter.inbound.InboundFilterNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InboundFilterConfig {

    @Bean
    public InboundFilter getInboundFilterChain() {
        InboundFilter filterone = new InboundFilterNull();
        return filterone;
    }
}
