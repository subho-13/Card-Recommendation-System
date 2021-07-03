package com.wf.contractlib.contracts;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@ToString
public class NearlineScheduler {
    private List<Integer> customerID;
}
