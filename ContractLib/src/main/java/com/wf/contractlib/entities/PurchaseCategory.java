package com.wf.contractlib.entities;

public enum PurchaseCategory {
    EDUCATION,
    ENTERTAINMENT,
    FOOD,
    GAS_TRANS,
    GROCERY_NET,
    GROCERY_POS,
    HEALTH,
    HOME,
    HOTEL,
    KIDS_PETS,
    MISC_NET,
    MISC_POS,
    PERSONAL,
    SHOP_NET,
    SHOP_POS,
    TRAVEL,
    UNKNOWN;

    public static PurchaseCategory convert(String category) {
        switch (category) {
        case "Personal":
            return PERSONAL;
        case "Health":
            return HEALTH;
        case "Misc_pos":
            return MISC_POS;
        case "Travel":
            return TRAVEL;
        case "Kids_pets":
            return KIDS_PETS;
        case "Shop_pos":
            return SHOP_POS;
        case "Food":
            return FOOD;
        case "Home":
            return HOME;
        case "Entertainment":
            return ENTERTAINMENT;
        case "Shop_net":
            return SHOP_NET;
        case "Misc_net":
            return MISC_NET;
        case "Grocery_pos":
            return GROCERY_POS;
        case "Gas_trans":
            return GAS_TRANS;
        case "Grocery_net":
            return GROCERY_NET;
        case "Education":
            return EDUCATION;
        case "Hotel":
            return HOTEL;
        case "Any":
            return ANY;
        }
        return UNKNOWN;
    }
}
