package com.wf.contractlib.contracts;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class OfflineTrigger {
    private boolean trigger;
}
