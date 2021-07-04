package com.wf.datacollector.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@ToString
public class InboundTransaction {
    String trans_date_trans_time;
    String card_num;
    String merchant;
    String purchase_category;
    Float trans_amt;
    String first;
    String last;
    String gender;
    String card_type;
    Integer credit_score;
    String street;
    String city;
    String state;
    String zip;
    Float latitude;
    Float longitude;
    Integer city_pop;
    String job;
    String dob;
    String trans_num;
    Long unix_time;
    Float merch_lat;
    Float merch_long;
    Integer is_fraud;
    Integer age;

    @JsonGetter("lat")
    public Float getLatitude() {
        return latitude;
    }

    @JsonGetter("long")
    public Float getLongitude() {
        return longitude;
    }
}
