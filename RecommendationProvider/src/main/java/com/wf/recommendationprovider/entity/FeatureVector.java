package com.wf.recommendationprovider.entity;

import com.wf.contractlib.entities.CardType;
import com.wf.contractlib.entities.JobType;
import com.wf.contractlib.entities.PurchaseCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "feature_vector")
@ToString
public class FeatureVector {
    @Id
    @Column(name = "customer_id")
    private Integer customerID;

    @Column(name = "job")
    JobType job;

    @Column(name = "credit_score")
    private Integer creditScore;

    @Column(name = "new_user")
    private Boolean newUser;

    @Column(name = "card_type")
    private CardType cardType;

    @ElementCollection
    @MapKeyColumn(name = "purchase_category")
    @MapKeyEnumerated(EnumType.STRING)
    @CollectionTable(name = "purchase_expenditure")
    private Map<PurchaseCategory, Float> purchaseExpenditureMap;
}
