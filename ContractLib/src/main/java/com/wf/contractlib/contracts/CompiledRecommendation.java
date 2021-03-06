package com.wf.contractlib.contracts;

import com.wf.contractlib.entities.CardType;
import lombok.*;

import java.util.Map;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompiledRecommendation {
    private Integer customerID;
    private Map<CardType, Float> cardConfidenceMap;
}