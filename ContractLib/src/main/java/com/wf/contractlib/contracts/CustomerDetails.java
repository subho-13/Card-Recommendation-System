package com.wf.contractlib.contracts;

import com.wf.contractlib.entities.* ;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@ToString
public class CustomerDetails {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String dob;
}
