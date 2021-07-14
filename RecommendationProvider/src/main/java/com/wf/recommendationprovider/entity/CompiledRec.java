package com.wf.recommendationprovider.entity;

import com.wf.contractlib.entities.CardType;
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
@Table(name = "compiled_rec")
@ToString
public class CompiledRec {
    @Id
    @Column(name = "customer_id")
    private Integer customerID;


    @ElementCollection
    @MapKeyColumn(name = "card_type")
    @MapKeyEnumerated(EnumType.STRING)
    @CollectionTable(name = "confidence_score")
    private Map<CardType, Float> cardConfidenceMap;
}
