package com.wf.nearlinescheduler.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class CustomerDetails {
    @Id
    private Integer customerID;

    @Column(name = "num_transactions")
    private Integer numTransactions;

    @Column(name = "last_scheduled_unix_time")
    private Long lastScheduledUnixTime;
}
