import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CollectedTransaction {
    String cardNum;
    String merchant;
    PurchaseCategory purchaseCategory;
    Float transactionAmount;
    String firstName;
    String lastName;
    Gender gender;
    CardType cardType;
    Integer creditScore;
    String street;
    String city;
    String state;
    String zip;
    GeoCoordinate addressCoordinate;
    Integer population;
    JobType job;
    Date dob;
    String transactionNum;
    Long unixTime;
    GeoCoordinate merchantCoordinate;
}

