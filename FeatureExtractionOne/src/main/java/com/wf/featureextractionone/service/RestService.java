package com.wf.featureextractionone.service;

import com.wf.contractlib.entities.JobType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
public class RestService {
    private static final String dataAbstractionUrl = "http://localhost:9502";
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public JobType getCustomerJob(Integer customerID) {
        String uri = dataAbstractionUrl + "/get/job/" + customerID;
        return fetchDataFromUri(uri, JobType.class);
    }

    public Integer getCustomerCreditScore(Integer customerID) {
        String uri = dataAbstractionUrl + "/get/creditScore/" + customerID;
        return fetchDataFromUri(uri, Integer.class);
    }

    public Long getCardIssueUnixTime(Integer cardID) {
        String uri = dataAbstractionUrl + "/get/cardIssueUnixTime/" + cardID;
        return fetchDataFromUri(uri, Long.class);
    }



    private <T> T fetchDataFromUri(String url, Class<T> clazz) {
        T tmp = this.restTemplate.getForObject(url, clazz);
        int sleepTimeMilliseconds = 100;

        while (tmp == null) {
            try {
                TimeUnit.MILLISECONDS.sleep(sleepTimeMilliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tmp = this.restTemplate.getForObject(url, clazz);
            sleepTimeMilliseconds = (sleepTimeMilliseconds * 2 + 1) % 6000;
        }

        return tmp;
    }
}
