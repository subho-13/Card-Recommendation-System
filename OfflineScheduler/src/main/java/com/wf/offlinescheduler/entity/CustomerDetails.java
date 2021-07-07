package com.wf.offlinescheduler.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_details")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDetails {
    @Id
    @Column(name = "customer_id", nullable = false)
    private Integer customerID;

    @Column(name = "num_transactions", nullable = false)
    private int numTransactions;
}