package com.wf.contractlib.contracts;

import com.wf.contractlib.entities.CardType;
import com.wf.contractlib.entities.PurchaseCategory;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
@ToString
public class ProvidedRecommendation {
    boolean isComplimentaryCard;
    Map<PurchaseCategory, Float> purchaseExpenditureMap;
    Map<CardType, Float> cardConfidenceMap;
    List<CardType> cards;
}
