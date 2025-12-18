package controller;

import models.data_handling.PromoModel;
import models.entity_models.Promo;

public class PromoController {

    // Returns promo.
 
    public static Promo getPromo(String code) {
        if (code == null || code.isBlank()) {
            return null;
        }
        return PromoModel.getPromo(code);
    }
}