package com.wf.recommendationcompiler.entity;

import com.wf.contractlib.entities.CardType;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Map;
import java.util.Objects;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "recommendation_details", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"customer_id", "model_name"})
})
public class RecommendationDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommendation_id")
    private Integer recommendationID;

    @Column(name = "customer_id", nullable = false)
    private Integer customerID;

    @Column(name = "model_name", nullable = false)
    private String modelName;

    @ElementCollection
    @MapKeyColumn(name = "card_type")
    @MapKeyEnumerated(EnumType.STRING)
    @CollectionTable(name = "confidence_score")
    private Map<CardType, Float> cardConfidenceMap;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }

        RecommendationDetails that = (RecommendationDetails) o;

        return Objects.equals(recommendationID, that.recommendationID);
    }

    @Override
    public int hashCode() {
        return 193502105;
    }
}

