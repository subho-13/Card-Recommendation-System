package com.wf.dataabstraction.entity;

import com.wf.contractlib.entities.GeoCoordinate;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "address_details")
@Data
@Getter
@Setter
@NoArgsConstructor
public class AddressDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressID;

    @Embedded
    @Column(name="address_coordinate", nullable = false)
    private GeoCoordinate addressCoordinate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerDetails customerDetails;

    @ManyToOne
    @JoinColumn(name = "city_name", nullable = false)
    private CityDetails cityDetails;

    @Column(name = "state", nullable = false, length = 2)
    private String state;

    @Column(name = "zip", nullable = false ,length = 5)
    private String zip;
}

