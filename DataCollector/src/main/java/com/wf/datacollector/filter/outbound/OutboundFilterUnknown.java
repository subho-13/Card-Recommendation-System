package com.wf.datacollector.filter.outbound;

import com.wf.contractlib.contracts.CollectedTransaction;
import com.wf.contractlib.entities.CardType;
import com.wf.contractlib.entities.Gender;
import com.wf.contractlib.entities.JobType;
import com.wf.contractlib.entities.PurchaseCategory;

public class OutboundFilterUnknown implements OutboundFilter {
    OutboundFilter nextOutboundFilter;
    @Override
    public boolean isOk(final CollectedTransaction transaction) {
        boolean isInputOkay;
        isInputOkay = transaction.getDob() != null;
        isInputOkay &= transaction.getGender() != Gender.UNKNOWN;
        isInputOkay &= (transaction.getJob() != JobType.UNKNOWN);
        isInputOkay &= (transaction.getCardType() != CardType.UNKNOWN);
        isInputOkay &= (transaction.getPurchaseCategory() != PurchaseCategory.UNKNOWN);

        if (nextOutboundFilter == null || !isInputOkay) {
            return isInputOkay;
        }

        return nextOutboundFilter.isOk(transaction);
    }

    @Override
    public void setNextFilter(final OutboundFilter outboundFilter) {
        this.nextOutboundFilter = outboundFilter;
    }
}