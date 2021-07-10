package com.wf.recommendationprovider.entity;

import com.wf.contractlib.entities.PurchaseCategory;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Data
@Getter
@Setter
@ToString
public class CardBenefits {
    private Integer creditScore;
    private Map<PurchaseCategory, Float> purchaseBenefitsMap;
}
