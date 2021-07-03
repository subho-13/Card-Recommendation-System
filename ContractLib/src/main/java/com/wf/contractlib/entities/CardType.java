package com.wf.contractlib.entities;

public enum CardType {
    CASH_WISE,
    PLATINUM,
    HOTEL,
    COLLEGE,
    VISA_SIGNATURE,
    HOLIDAY,
    SHOPPING,
    ENTERTAINMENT,
    CREDIT_BUILDER,
    UNKNOWN;

    public static CardType convert(String type) {
        switch (type) {
            case "Cash Wise": return CASH_WISE;
            case "Platinum": return PLATINUM;
            case "Hotel": return HOTEL;
            case "College": return COLLEGE;
            case "Visa Signature": return VISA_SIGNATURE;
            case "Holiday": return HOLIDAY;
            case "Shopping": return SHOPPING;
            case "Entertainment": return ENTERTAINMENT;
            case "Credit Builder": return CREDIT_BUILDER;
        }
        return UNKNOWN;
    }
}
