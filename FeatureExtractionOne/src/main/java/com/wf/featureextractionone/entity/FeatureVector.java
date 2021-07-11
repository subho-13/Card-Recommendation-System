package com.wf.featureextractionone.entity;

import com.wf.contractlib.entities.CardType;
import com.wf.contractlib.entities.JobType;
import com.wf.contractlib.entities.PurchaseCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feature_vector_one")
public class FeatureVector {
    @Id
    @Column(name = "customer_id")
    private Integer customerID;

    @Column(name = "job", nullable = false)
    private JobType job;

    @Column(name = "creditScore", nullable = false)
    private Integer creditScore;

    @Column(name = "new_user", nullable = false)
    private Boolean newUser;

    @Column(name = "card_type", nullable = false)
    private CardType cardType;

    @Column(name = "card_issue_unix_time", nullable = false)
    private Long cardIssueUnixTime;

    @ElementCollection
    @MapKeyColumn(name = "purchase_category")
    @MapKeyEnumerated(EnumType.STRING)
    @CollectionTable(name = "expenditure")
    private Map<PurchaseCategory, Float> purchaseExpenditureMap;
}
