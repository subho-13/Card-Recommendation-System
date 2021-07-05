package com.wf.dataabstraction.entity;

import com.wf.contractlib.entities.Gender;
import com.wf.contractlib.entities.JobType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="customer_details", uniqueConstraints={
        @UniqueConstraint(columnNames = {"first_name", "last_name", "gender", "dob"})
})
@Data
@Getter
@Setter
@NoArgsConstructor
public class CustomerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Integer customerID;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "dob", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dob;

    @OneToOne(mappedBy = "customerDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AddressDetails addressDetails;

    @Column(name = "job", nullable = false)
    private JobType job;

    @Column(name = "credit_score", nullable = false)
    private Integer creditScore;

    @OneToMany(mappedBy = "customerDetails")
    private Set<CardDetails> cardDetails = new HashSet<>();
}
