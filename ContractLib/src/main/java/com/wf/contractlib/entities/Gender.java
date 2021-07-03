package com.wf.contractlib.entities;

public enum Gender {
    FEMALE,
    MALE,
    UNKNOWN;

    public static Gender convert(String gender) {
        switch (gender) {
            case "F": return FEMALE;
            case "M": return MALE;
        }

        return UNKNOWN;
    }
}

