package com.wf.dataabstraction.service;

import com.wf.contractlib.contracts.CollectedTransaction;
import com.wf.dataabstraction.entity.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class DetailsGenerator {
    public CustomerDetails generateCustomer(@NotNull CollectedTransaction transaction) {
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setFirstName(transaction.getFirstName());
        customerDetails.setLastName(transaction.getLastName());
        customerDetails.setGender(transaction.getGender());
        customerDetails.setJob(transaction.getJob());
        customerDetails.setDob(transaction.getDob());
        customerDetails.setCreditScore(transaction.getCreditScore());
        return customerDetails;
    }

    public CardDetails generateCard(@NotNull CollectedTransaction transaction , @NotNull CustomerDetails customerDetails) {
        CardDetails cardDetails = new CardDetails();
        cardDetails.setCardNum(transaction.getCardNum());
        cardDetails.setCustomerDetails(customerDetails);
        cardDetails.setCardType(transaction.getCardType());
        cardDetails.setCardIssueUnixTime(transaction.getUnixTime());
        return cardDetails;
    }

    public CityDetails generateCity(@NotNull CollectedTransaction transaction) {
        CityDetails cityDetails = new CityDetails();
        cityDetails.setCityName(transaction.getCity());
        cityDetails.setCityPop(transaction.getPopulation());

        return cityDetails;
    }

    public AddressDetails generateAddress(@NotNull CollectedTransaction transaction, @NotNull CustomerDetails customerDetails, @NotNull CityDetails cityDetails) {
        AddressDetails addressDetails = new AddressDetails();
        addressDetails.setCustomerDetails(customerDetails);
        addressDetails.setCityDetails(cityDetails);
        addressDetails.setState(transaction.getState());
        addressDetails.setZip(transaction.getZip());
        addressDetails.setAddressCoordinate(transaction.getAddressCoordinate());

        return addressDetails;
    }

    public MerchantDetails getMerchant(@NotNull CollectedTransaction transaction) {
        MerchantDetails merchantDetails = new MerchantDetails();
        merchantDetails.setMerchantName(transaction.getMerchant());

        return merchantDetails;
    }
}
