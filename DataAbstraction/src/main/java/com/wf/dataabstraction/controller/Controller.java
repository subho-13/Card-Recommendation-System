package com.wf.dataabstraction.controller;

import com.wf.contractlib.entities.JobType;
import com.wf.dataabstraction.entity.CardDetails;
import com.wf.dataabstraction.entity.CustomerDetails;
import com.wf.dataabstraction.repository.CardDetailsRepository;
import com.wf.dataabstraction.repository.CustomerDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class Controller {
    private CardDetailsRepository cardDetailsRepository;

    @Autowired
    public void setCustomerDetailsRepository(CustomerDetailsRepository customerDetailsRepository) {
        this.customerDetailsRepository = customerDetailsRepository;
    }

    private CustomerDetailsRepository customerDetailsRepository;

    @Autowired
    public void setCardDetailsRepository(CardDetailsRepository cardDetailsRepository) {
        this.cardDetailsRepository = cardDetailsRepository;
    }

    @GetMapping(value = "/get/cardIssueUnixTime/{cardID}")
    public Long getCardIssueUnixTime(@PathVariable Integer cardID) {
        Optional<CardDetails> optionalCardDetails = cardDetailsRepository.findByCardID(cardID);

        if (optionalCardDetails.isEmpty()) {
            return null;
        }

        return optionalCardDetails.get().getCardIssueUnixTime();
    }

    @GetMapping(value = "/get/job/{customerID}")
    public JobType getJob(@PathVariable Integer customerID) {
        Optional<CustomerDetails> optionalCustomerDetails =
                customerDetailsRepository.findByCustomerID(customerID);

        if (optionalCustomerDetails.isEmpty()) {
            return null;
        }
        return optionalCustomerDetails.get().getJob();
    }

    @GetMapping(value = "/get/creditScore/{customerID}")
    public Integer getCreditScore(@PathVariable Integer customerID) {
        Optional<CustomerDetails> optionalCustomerDetails =
                customerDetailsRepository.findByCustomerID(customerID);

        if (optionalCustomerDetails.isEmpty()) {
            return null;
        }
        return optionalCustomerDetails.get().getCreditScore();
    }

    @PostMapping(value = "/get/customerID")
    public Integer getCustomerID(com.wf.contractlib.contracts.CustomerDetails customerDetails){
        try {
            Date convertedDob = new SimpleDateFormat("yyyy-MM-dd").parse(customerDetails.getDob());

            Optional<CustomerDetails> optionalCustomerDetails =
                    customerDetailsRepository.findByFirstNameAndLastNameAndGenderAndDob(
                    customerDetails.getFirstName(), customerDetails.getLastName(), customerDetails.getGender(), convertedDob
            );

            if (optionalCustomerDetails.isPresent()) {
                return optionalCustomerDetails.get().getCustomerID();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
