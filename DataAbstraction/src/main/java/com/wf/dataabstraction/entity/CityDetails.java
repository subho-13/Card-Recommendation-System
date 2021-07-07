package com.wf.dataabstraction.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="city_details")
@Data
@Getter
@Setter
@NoArgsConstructor
public class CityDetails {
    @Id
    @Column(name = "city_name", nullable = false, length = 50)
    private String cityName;

    @OneToMany(mappedBy = "cityDetails")
    private Set<AddressDetails> addressDetails = new HashSet<>();

    @Column(name = "city_pop", nullable = false)
    private Integer cityPop;
}
