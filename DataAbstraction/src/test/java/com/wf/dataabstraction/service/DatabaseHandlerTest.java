package com.wf.dataabstraction.service;

import com.wf.contractlib.contracts.CollectedTransaction;
import com.wf.contractlib.entities.*;
import com.wf.dataabstraction.entity.*;
import com.wf.dataabstraction.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DatabaseHandlerTest {

    @InjectMocks
    private DatabaseHandler databaseHandler ;

    @Mock
    private CustomerDetailsRepository customerDetailsRepository;
    @Mock
    private AddressDetailsRepository addressDetailsRepository;
    @Mock
    private CityDetailsRepository cityDetailsRepository;
    @Mock
    private CardDetailsRepository cardDetailsRepository;
    @Mock
    private MerchantDetailsRepository merchantDetailsRepository;
    @Mock
    private DetailsGenerator detailsGenerator;
    @Mock
    AutoCloseable closeable ;

    @BeforeEach
    void setUp() {
        databaseHandler = new DatabaseHandler() ;
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    public CollectedTransaction getCollectedTransaction() throws ParseException {
        CollectedTransaction collectedTransaction = new CollectedTransaction() ;
        collectedTransaction.setCardNum("9876543219876543");
        collectedTransaction.setMerchant("Sporer-Keebler");
        collectedTransaction.setPurchaseCategory(PurchaseCategory.PERSONAL);
        collectedTransaction.setTransactionAmount(30.34F);
        collectedTransaction.setFirstName("Ashley");
        collectedTransaction.setLastName("Lopez");
        collectedTransaction.setGender(Gender.FEMALE);
        collectedTransaction.setCardType(CardType.SHOPPING);
        collectedTransaction.setCreditScore(210);
        collectedTransaction.setStreet("9333 Valentine Point");
        collectedTransaction.setCity("Bellmore");
        collectedTransaction.setState("NY");
        collectedTransaction.setZip("11710");
        collectedTransaction.setAddressCoordinate(new GeoCoordinate( -73.5365F,40.6729F));
        collectedTransaction.setPopulation(34496);
        collectedTransaction.setJob(JobType.MANAGER);
        collectedTransaction.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("10-12-98"));
        collectedTransaction.setTransactionNum("c81755dbbbea9d5c77f094348a7579be");
        collectedTransaction.setUnixTime(1371816893L);
        collectedTransaction.setMerchantCoordinate(new GeoCoordinate(40.49581F,-74.196111F));

        return  collectedTransaction ;
    }

    public CityDetails getCityDetails()
    {
        CityDetails cityDetails = new CityDetails();
        cityDetails.setCityName("Bellmore");
        cityDetails.setCityPop(34496);
        return cityDetails ;
    }

    public CustomerDetails getCustomerDetails() throws ParseException {
        CustomerDetails customerDetails = new CustomerDetails() ;
        customerDetails.setCustomerID(1);
        customerDetails.setFirstName("Ashley");
        customerDetails.setLastName("Lopez");
        customerDetails.setGender(Gender.FEMALE);
        customerDetails.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("10-12-98"));
        customerDetails.setJob(JobType.MANAGER);
        customerDetails.setCreditScore(210);
        return customerDetails ;
    }

    public CardDetails getCardDetails(CustomerDetails customerDetails){

        CardDetails cardDetails = new CardDetails();
        cardDetails.setCardNum("9876543219876543");
        cardDetails.setCustomerDetails(customerDetails);
        cardDetails.setCardType(CardType.SHOPPING);
        cardDetails.setCardIssueUnixTime(1371816893L);
        cardDetails.setCardID(5);
        return cardDetails ;
    }

    public  AddressDetails getAddressDetails(CustomerDetails customerDetails ,CityDetails cityDetails )
    {

        AddressDetails addressDetails = new AddressDetails() ;
        addressDetails.setCustomerDetails(customerDetails);
        addressDetails.setCityDetails(cityDetails);
        addressDetails.setState("NY");
        addressDetails.setZip("11710");
        addressDetails.setAddressCoordinate(new GeoCoordinate( -73.5365F,40.6729F));

         return addressDetails ;
    }

    @Test //customer exists
     void handlerTest() throws ParseException {

        CollectedTransaction collectedTransaction = getCollectedTransaction() ;
        CustomerDetails customerDetails = getCustomerDetails() ;
        CardDetails cardDetails = getCardDetails(customerDetails) ;
        CityDetails cityDetails = getCityDetails() ;

        MerchantDetails merchantDetails = new MerchantDetails();
        merchantDetails.setMerchantName("Sporer-Keebler");
        merchantDetails.setMerchantID(34);

        Map<String, Integer> mapDetailsID = new HashMap<>() ;
        mapDetailsID.put("customerID",1) ;
        mapDetailsID.put("cardID",5) ;
        mapDetailsID.put("merchantID",34) ;

        when(detailsGenerator.generateCity(collectedTransaction)).thenReturn(cityDetails) ;
        when(detailsGenerator.generateCustomer(collectedTransaction)).thenReturn(customerDetails) ;
        when(customerDetailsRepository.findByFirstNameAndLastNameAndGenderAndDob(customerDetails.getFirstName(),
                customerDetails.getLastName(), customerDetails.getGender(), customerDetails.getDob()))
             .thenReturn(Optional.of(customerDetails));
//        when(detailsGenerator.generateAddress(collectedTransaction, customerDetails, cityDetails)).thenReturn(addressDetails) ;
        when(detailsGenerator.generateCard(collectedTransaction, customerDetails)).thenReturn(cardDetails) ;
        when(cardDetailsRepository.findByCardNum(cardDetails.getCardNum())).thenReturn(Optional.of(cardDetails)) ;
        when(detailsGenerator.getMerchant(collectedTransaction)).thenReturn(merchantDetails) ;
        when(merchantDetailsRepository.findByMerchantName(merchantDetails.getMerchantName())).thenReturn(Optional.of(merchantDetails)) ;
        assertEquals(mapDetailsID,databaseHandler.saveTransactionDetails(collectedTransaction)) ;


    }

    @Test
     void newUserTest() throws ParseException {

        CollectedTransaction collectedTransaction = getCollectedTransaction() ;
        CustomerDetails customerDetails = getCustomerDetails() ;
        CardDetails cardDetails = getCardDetails(customerDetails) ;
        CityDetails cityDetails = getCityDetails() ;
        AddressDetails addressDetails = getAddressDetails(customerDetails,cityDetails) ;

        MerchantDetails merchantDetails = new MerchantDetails();
        merchantDetails.setMerchantName("Sporer-Keebler");
        merchantDetails.setMerchantID(34);

        Map<String, Integer> mapDetailsID = new HashMap<>() ;
        mapDetailsID.put("customerID",1) ;
        mapDetailsID.put("cardID",5) ;
        mapDetailsID.put("merchantID",34) ;

        when(detailsGenerator.generateCity(collectedTransaction)).thenReturn(cityDetails) ;
        when(detailsGenerator.generateCustomer(collectedTransaction)).thenReturn(customerDetails) ;
        when(customerDetailsRepository.findByFirstNameAndLastNameAndGenderAndDob(customerDetails.getFirstName(),
                customerDetails.getLastName(), customerDetails.getGender(), customerDetails.getDob()))
                .thenReturn(Optional.empty());
        when(customerDetailsRepository.save(customerDetails)).thenReturn(customerDetails) ;
        when(detailsGenerator.generateAddress(collectedTransaction, customerDetails, cityDetails)).thenReturn(addressDetails) ;
        when(detailsGenerator.generateCard(collectedTransaction, customerDetails)).thenReturn(cardDetails) ;
        when(cardDetailsRepository.findByCardNum(cardDetails.getCardNum())).thenReturn(Optional.empty()) ;
        when(cardDetailsRepository.save(any())).thenReturn(cardDetails) ;
        when(detailsGenerator.getMerchant(collectedTransaction)).thenReturn(merchantDetails) ;
        when(merchantDetailsRepository.findByMerchantName(merchantDetails.getMerchantName())).thenReturn(Optional.empty()) ;
        when(merchantDetailsRepository.save(any())).thenReturn(merchantDetails) ;

        assertEquals(mapDetailsID,databaseHandler.saveTransactionDetails(collectedTransaction)) ;
        verify(addressDetailsRepository,times(1)).save(any()) ;
    }

}