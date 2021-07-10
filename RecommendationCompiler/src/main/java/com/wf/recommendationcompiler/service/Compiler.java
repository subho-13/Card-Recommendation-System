package com.wf.recommendationcompiler.service;

import com.wf.contractlib.entities.CardType;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class Compiler {
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock writeLock = readWriteLock.writeLock();
    private Lock readLock = readWriteLock.readLock();

    @Getter
    HashMap<String, Float> modelWeightMap;

    public Compiler() {
        modelWeightMap = new HashMap<>();
        modelWeightMap.put("Rule Learning", 0.15F);
        modelWeightMap.put("Association Rule Learning", 0.35F);
        modelWeightMap.put("Kpca Kmeans", 0.05F);
        modelWeightMap.put("Kpca Similarity", 0.06F);
        modelWeightMap.put("Pca Kmeans", 0.04F);
        modelWeightMap.put("Som Based", 0.1F);
        modelWeightMap.put("Neural Based", 0.1F);
        modelWeightMap.put("Autoencoder Based", 0.1F);
        modelWeightMap.put("New User", 0.05F);
    }

    public void updateMap(@NotNull final Map<String, Float> newModelWeightMap) {
        try {
            writeLock.lock();
            modelWeightMap = new HashMap<>();

            for(Map.Entry<String, Float> modelWeightEntry : newModelWeightMap.entrySet()) {
                modelWeightMap.put(modelWeightEntry.getKey(), modelWeightEntry.getValue());
            }
        }
        finally {
            writeLock.unlock();
        }
    }

    public Map<CardType, Float> getCardConfidenceMap(@NotNull Map<String, Map<CardType, Float>> modelCardConfidenceMap) {
        Map<CardType, Float> finalCardConfidenceMap = new HashMap<>();
        try {
            readLock.lock();

            for (Map.Entry<String, Map<CardType, Float>> entry: modelCardConfidenceMap.entrySet()) {
                String modelName = entry.getKey();
                Float modelWeight = modelWeightMap.get(modelName);
                Map<CardType, Float> cardConfidenceMap = entry.getValue();

                for(Map.Entry<CardType, Float> cardConfidence: cardConfidenceMap.entrySet()) {
                    CardType cardType = cardConfidence.getKey();

                    Float confidence = cardConfidence.getValue();
                    Float globalConfidence = confidence * modelWeight;

                    if(finalCardConfidenceMap.containsKey(cardType)) {
                        Float prevConfidence = finalCardConfidenceMap.get(cardType);
                        globalConfidence += prevConfidence;
                    }

                    finalCardConfidenceMap.put(cardType, globalConfidence);
                }
            }

        } finally {
            readLock.unlock();
        }

        return finalCardConfidenceMap;
    }
}
