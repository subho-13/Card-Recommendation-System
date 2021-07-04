package com.wf.datacollector.filter.inbound;

import com.wf.datacollector.entity.InboundTransaction;

public interface InboundFilter {
    public boolean isOk(InboundTransaction transaction);
    public void setNextFilter(InboundFilter inboundFilter);
}
