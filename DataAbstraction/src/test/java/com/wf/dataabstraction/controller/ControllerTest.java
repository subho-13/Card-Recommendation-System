package com.wf.dataabstraction.controller;

import com.wf.contractlib.entities.CardType;
import com.wf.contractlib.entities.Gender;
import com.wf.contractlib.entities.JobType;
import com.wf.dataabstraction.entity.CardDetails;
import com.wf.dataabstraction.entity.CustomerDetails;
import com.wf.dataabstraction.repository.CardDetailsRepository;
import com.wf.dataabstraction.repository.CustomerDetailsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static com.wf.contractlib.entities.JobType.MANAGER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(Controller.class)
class ControllerTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private CardDetailsRepository cardDetailsRepository;
    @MockBean
    private CustomerDetailsRepository customerDetailsRepository ;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    @DisplayName("Get mapping tests when user not found in DB ")
    public void newUserTest() throws Exception {

        when(cardDetailsRepository.findByCardID(5)).thenReturn(Optional.empty());
        mockMvc.perform(
                MockMvcRequestBuilders.get("/get/cardIssueUnixTime/{cardID}",5).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string("")) ;

        when(customerDetailsRepository.findByCustomerID(1)).thenReturn(Optional.empty());
        mockMvc.perform(
                MockMvcRequestBuilders.get("/get/job/{customerID}",1).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string("")) ;

        when(customerDetailsRepository.findByCustomerID(1)).thenReturn(Optional.empty());
        mockMvc.perform(
                MockMvcRequestBuilders.get("/get/creditScore/{customerID}",1).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string("")) ;

//        when(customerDetailsRepository.findByFirstNameAndLastNameAndGenderAndDob(anyString(),anyString(),any(),any())).thenReturn(Optional.empty()) ;
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/get/customerID").content("{\\\"customerID\\\":1,\\\"firstName\\\":\\\"Ashley\\\",\\\"lastName\\\":\\\"Lopez\\\",\\\"gender\\\":\\\"FEMALE\\\",\\\"dob\\\":\\\"10-10-98\\\",\\\"addressDetails\\\":null,\\\"job\\\":\\\"MANAGER\\\",\\\"creditScore\\\":210,\\\"cardDetails\\\":[]}\"" )
//                        .contentType("application/json"))
//                .andExpect(status().isBadRequest()) ;
// {\"customerID\":1,\"firstName\":\"Ashley\",\"lastName\":\"Lopez\",\"gender\":\"FEMALE\",\"dob\":\"10-10-98\",\"addressDetails\":null,\"job\":\"MANAGER\",\"creditScore\":210,\"cardDetails\":[]}"

    }

    @Test
    @DisplayName("happy path testing")
    public void controllerTest() throws Exception {

        CustomerDetails customerDetails = new CustomerDetails() ;
        customerDetails.setCustomerID(1);
        customerDetails.setFirstName("Ashley");
        customerDetails.setLastName("Lopez");
        customerDetails.setGender(Gender.FEMALE);
        customerDetails.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("10-12-98"));
        customerDetails.setJob(MANAGER);
        customerDetails.setCreditScore(210);

        CardDetails cardDetails = new CardDetails();
        cardDetails.setCardNum("9876543219876543");
        cardDetails.setCustomerDetails(customerDetails);
        cardDetails.setCardType(CardType.SHOPPING);
        cardDetails.setCardIssueUnixTime(1371816893L);
        cardDetails.setCardID(5);

        when(cardDetailsRepository.findByCardID(5)).thenReturn(Optional.of(cardDetails));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/get/cardIssueUnixTime/{cardID}",5).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(1371816893L))) ;

        when(customerDetailsRepository.findByCustomerID(1)).thenReturn(Optional.of(customerDetails));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/get/job/{customerID}",1).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string("\"MANAGER\"") ) ;

        mockMvc.perform(
                MockMvcRequestBuilders.get("/get/creditScore/{customerID}",1).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(210))) ;

        when(customerDetailsRepository.findByFirstNameAndLastNameAndGenderAndDob(anyString(),anyString(),any(),any())).thenReturn(Optional.empty()) ;
        mockMvc.perform(
                MockMvcRequestBuilders.post("/get/customerID").content("{\\\"customerID\\\":1,\\\"firstName\\\":\\\"Ashley\\\",\\\"lastName\\\":\\\"Lopez\\\",\\\"gender\\\":\\\"FEMALE\\\",\\\"dob\\\":\\\"10-10-98\\\",\\\"addressDetails\\\":null,\\\"job\\\":\\\"MANAGER\\\",\\\"creditScore\\\":210,\\\"cardDetails\\\":[]}\"" )
                        .contentType("application/json"))
                .andExpect(status().isOk()) ;

    }

}