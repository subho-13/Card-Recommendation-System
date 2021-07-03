package com.wf.contractlib.contracts;

import com.wf.contractlib.entities.CardType;
import lombok.*;

import java.util.Map;

@Data
@Getter
@Setter
@NoArgsConstructor
@ToString
public class GeneratedRecommendation {
    private Integer customerID;
    private String modelName;
    private Map<CardType, Float> cardConfidenceMap;
}