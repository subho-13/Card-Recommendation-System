package com.wf.dataabstraction.entity;

import com.wf.contractlib.entities.CardType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name="card_details", uniqueConstraints={
        @UniqueConstraint(columnNames = {"card_num"})
})
@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class CardDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id", nullable = false)
    private Integer cardID;

    @Column(name = "card_num", nullable = false)
    private String cardNum;

    @Column(name = "card_type", nullable = false)
    private CardType cardType;

    @Column(name = "card_issue_unix_time", nullable = false)
    private Long cardIssueUnixTime;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerDetails customerDetails;
}
