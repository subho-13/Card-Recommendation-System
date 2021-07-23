package com.wf.recommendationcompiler.controller;

import com.wf.recommendationcompiler.service.FinalRecommendationCompiler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(ModelWeightController.class)
class ModelWeightControllerTest {

        @Autowired
        protected MockMvc mockMvc;
        @Autowired
        private WebApplicationContext webApplicationContext;
        @MockBean
        private FinalRecommendationCompiler finalRecommendationCompiler ;

        @BeforeEach
        public void setup() {
            this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        }

        @Test
        void consumeTransactionTest() throws Exception {
            finalRecommendationCompiler = new FinalRecommendationCompiler() ;
            mockMvc.perform(
                    MockMvcRequestBuilders.post("/modelWeight").content("{\"Rule Learning\":0.05,\"Association Rule Learning\":0.15,\"Kpca Kmeans\":0.35,\"Kpca Similarity\":0.06,\"Pca Kmeans\":0.04,\"Som Based\":0.1,\"Neural Based\":0.1,\"Autoencoder Based\":0.1,\"New User\":0.05}").contentType("application/json"))
                    .andExpect(status().isOk());

        }

        @Test
        void badRequestTest() throws Exception {
            finalRecommendationCompiler = new FinalRecommendationCompiler() ;
            mockMvc.perform(
                    MockMvcRequestBuilders.post("/modelWeight").content("").contentType("application/json"))
                    .andExpect(status().isBadRequest());

        }
    }

