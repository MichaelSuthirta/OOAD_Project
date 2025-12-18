package controller;

import java.util.List;

import models.data_handling.CourierModel;
import models.entity_models.Courier;

public class CourierController {

    // getCourier(idCourier)
   

    public static Courier getCourier(String idCourier) {
        return CourierModel.getCourierByID(Integer.parseInt(idCourier));
    }

    // getAllCouriers()
    

    public static List<Courier> getAllCouriers() {
        return CourierModel.getAllCouriers();
    }
}