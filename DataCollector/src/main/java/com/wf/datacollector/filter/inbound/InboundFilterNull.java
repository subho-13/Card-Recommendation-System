package com.wf.datacollector.filter.inbound;

import com.wf.datacollector.entity.InboundTransaction;

public class InboundFilterNull implements InboundFilter {
    InboundFilter nextInboundFilter;

    @Override
    public boolean isOk(InboundTransaction transaction) {
        boolean isInputOkay = true;
        isInputOkay &= (transaction.getTrans_date_trans_time() != null);
        isInputOkay &= (transaction.getCard_num() != null);
        isInputOkay &= (transaction.getMerchant() != null);
        isInputOkay &= (transaction.getPurchase_category() != null);
        isInputOkay &= (transaction.getTrans_amt() != null);
        isInputOkay &= (transaction.getFirst() != null);
        isInputOkay &= (transaction.getLast() != null);
        isInputOkay &= (transaction.getGender() != null);
        isInputOkay &= (transaction.getCard_type() != null);
        isInputOkay &= (transaction.getStreet() != null);
        isInputOkay &= (transaction.getCity() != null);
        isInputOkay &= (transaction.getState() != null);
        isInputOkay &= (transaction.getZip() != null);
        isInputOkay &= (transaction.getLatitude() != null);
        isInputOkay &= (transaction.getLongitude() != null);
        isInputOkay &= (transaction.getCity_pop() != null);
        isInputOkay &= (transaction.getJob() != null);
        isInputOkay &= (transaction.getDob() != null);
        isInputOkay &= (transaction.getTrans_num() != null);
        isInputOkay &= (transaction.getUnix_time() != null);
        isInputOkay &= (transaction.getMerch_lat() != null);
        isInputOkay &= (transaction.getMerch_long() != null);
        isInputOkay &= (transaction.getIs_fraud() != null);
        isInputOkay &= (transaction.getAge() != null);
        isInputOkay &= (transaction.getCredit_score()!= null);

        if(nextInboundFilter == null || isInputOkay == false) {
            return isInputOkay;
        }

        return nextInboundFilter.isOk(transaction);
    }

    @Override
    public void setNextFilter(InboundFilter inboundFilter) {
        this.nextInboundFilter = inboundFilter;
    }
}

