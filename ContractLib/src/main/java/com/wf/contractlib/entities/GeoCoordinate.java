package com.wf.contractlib.entities;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeoCoordinate {
    private Float latitude;
    private Float longitude;
}