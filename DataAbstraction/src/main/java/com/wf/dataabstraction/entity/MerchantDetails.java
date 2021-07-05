package com.wf.dataabstraction.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="merchant_details", uniqueConstraints={
        @UniqueConstraint(columnNames = {"merchantName"})
})
@Data
@Getter
@Setter
@NoArgsConstructor
public class MerchantDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer merchantID;

    @Column(name="merchantName", nullable=false)
    private String merchantName;
}

