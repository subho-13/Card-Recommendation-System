package com.wf.datacollector.filter.outbound;

import com.wf.contractlib.contracts.CollectedTransaction;

public interface OutboundFilter {
    public boolean isOk(CollectedTransaction transaction);
    void setNextFilter(OutboundFilter outboundFilter);
}
