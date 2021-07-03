package com.wf.contractlib.contracts.featurevector;

import com.wf.contractlib.entities.CardType;
import com.wf.contractlib.entities.JobType;
import com.wf.contractlib.entities.PurchaseCategory;
import lombok.*;

import java.util.Map;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FeatureVectorOne {
    private Integer customerID;
    private JobType job;
    private Integer creditScore;
    private Boolean newUser;
    private CardType cardType;
    private Long cardIssueUnixTime;
    private Map<PurchaseCategory, Float> purchaseExpenditureMap;
}
