package com.wf.dataabstraction.service;

import com.wf.contractlib.contracts.CollectedTransaction;
import com.wf.dataabstraction.entity.*;
import com.wf.dataabstraction.repository.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class DatabaseHandler {
    private CustomerDetailsRepository customerDetailsRepository;
    private AddressDetailsRepository addressDetailsRepository;
    private CityDetailsRepository cityDetailsRepository;
    private CardDetailsRepository cardDetailsRepository;
    private MerchantDetailsRepository merchantDetailsRepository;
    private DetailsGenerator detailsGenerator;

    @Autowired
    public void setCustomerDetailsRepository(CustomerDetailsRepository customerDetailsRepository) {
        this.customerDetailsRepository = customerDetailsRepository;
    }

    @Autowired
    public void setAddressDetailsRepository(AddressDetailsRepository addressDetailsRepository) {
        this.addressDetailsRepository = addressDetailsRepository;
    }

    @Autowired
    public void setCardDetailsRepository(CardDetailsRepository cardDetailsRepository) {
        this.cardDetailsRepository = cardDetailsRepository;
    }

    @Autowired
    public void setCityDetailsRepository(CityDetailsRepository cityDetailsRepository) {
        this.cityDetailsRepository = cityDetailsRepository;
    }

    @Autowired
    public void setMerchantDetailsRepository(MerchantDetailsRepository merchantDetailsRepository) {
        this.merchantDetailsRepository = merchantDetailsRepository;
    }

    @Autowired
    public void setDetailsGenerator(DetailsGenerator detailsGenerator) {
        this.detailsGenerator = detailsGenerator;
    }

    @Transactional
    public Map<String, Integer> saveTransactionDetails(CollectedTransaction transaction) {
        CityDetails cityDetails = saveCityDetails(transaction);
        CustomerDetails customerDetails = saveCustomerDetails(transaction, cityDetails);
        CardDetails cardDetails = saveCardDetails(transaction, customerDetails);
        MerchantDetails merchantDetails = saveMerchantDetails(transaction);

        Map<String, Integer> mapDetailID = new HashMap<>();
        mapDetailID.put("customerID", customerDetails.getCustomerID());
        mapDetailID.put("cardID", cardDetails.getCardID());
        mapDetailID.put("merchantID", merchantDetails.getMerchantID());

        return mapDetailID;
    }

    @NotNull
    private CardDetails saveCardDetails(CollectedTransaction transaction, CustomerDetails customerDetails) {
        CardDetails cardDetails = detailsGenerator.generateCard(transaction, customerDetails);
        Optional<CardDetails> cardDetailsTemp =
                cardDetailsRepository.findByCardNum(cardDetails.getCardNum());
        if (cardDetailsTemp.isEmpty()) {
            cardDetails = cardDetailsRepository.save(cardDetails);
        } else {
            cardDetails.setCardID(cardDetailsTemp.get().getCardID());
        }
        return cardDetails;
    }

    @NotNull
    private CityDetails saveCityDetails(CollectedTransaction transaction) {
        CityDetails cityDetails = detailsGenerator.generateCity(transaction);
        cityDetailsRepository.save(cityDetails);
        return cityDetails;
    }

    @NotNull
    private CustomerDetails saveCustomerDetails(CollectedTransaction transaction, CityDetails cityDetails) {
        CustomerDetails customerDetails = detailsGenerator.generateCustomer(transaction);
        Optional<CustomerDetails> customerDetailsTemp =
                customerDetailsRepository.findByFirstNameAndLastNameAndGenderAndDob(customerDetails.getFirstName(),
                        customerDetails.getLastName(), customerDetails.getGender(),
                        customerDetails.getDob());
        if (customerDetailsTemp.isEmpty()) {
            customerDetails = customerDetailsRepository.save(customerDetails);

            AddressDetails addressDetails =
                    detailsGenerator.generateAddress(transaction, customerDetails, cityDetails);

            addressDetailsRepository.save(addressDetails);
        } else {
            customerDetails.setCustomerID(customerDetailsTemp.get().getCustomerID());
        }
        return customerDetails;
    }

    @NotNull
    private MerchantDetails saveMerchantDetails(CollectedTransaction transaction) {
        MerchantDetails merchantDetails = detailsGenerator.getMerchant(transaction);
        Optional<MerchantDetails> merchantDetailsTemp =
                merchantDetailsRepository.findByMerchantName(merchantDetails.getMerchantName());
        if (merchantDetailsTemp.isEmpty()) {
            merchantDetails = merchantDetailsRepository.save(merchantDetails);
        } else {
            merchantDetails.setMerchantID(merchantDetailsTemp.get().getMerchantID());
        }
        return merchantDetails;
    }
}
