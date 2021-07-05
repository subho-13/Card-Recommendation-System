package com.wf.contractlib.contracts;

import com.wf.contractlib.entities.CardType;
import com.wf.contractlib.entities.GeoCoordinate;
import com.wf.contractlib.entities.PurchaseCategory;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AbstractedTransaction {
    private Integer customerID;
    private Integer cardID;
    private Integer merchantID;
    private PurchaseCategory purchaseCategory;
    private GeoCoordinate merchantCoordinate;
    private Float transactionAmount;
    private String transactionNum;
    private Long unixTime;
    private CardType cardType;
}