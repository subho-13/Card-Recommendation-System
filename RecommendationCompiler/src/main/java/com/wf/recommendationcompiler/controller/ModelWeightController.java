package com.wf.recommendationcompiler.controller;

import com.wf.recommendationcompiler.service.FinalRecommendationCompiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ModelWeightController {
    private FinalRecommendationCompiler finalRecommendationCompiler;

    @Autowired
    public void setFinalRecommendationCompiler(FinalRecommendationCompiler finalRecommendationCompiler) {
        this.finalRecommendationCompiler = finalRecommendationCompiler;
    }

    @PostMapping("/modelWeight")
    public void setModelWeight(@RequestBody Map<String, Float> modelWeight) {
        finalRecommendationCompiler.updateModelWeightMap(modelWeight);
    }
}
