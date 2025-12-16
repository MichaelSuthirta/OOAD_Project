package controller;

import models.data_handling.DeliveryModel;
import models.data_handling.UserModel;
import models.entity_models.Delivery;
import models.entity_models.User;

public class DeliveryController {

    // assignCourierToOrder
    public static Delivery assignCourierToOrder(String idOrder, String idCourier) {

        User courier = UserModel.findUserByID(Integer.parseInt(idCourier));
        if (courier == null || !courier.getRole().equalsIgnoreCase("courier")) {
            System.out.println("Courier not found");
            return null;
        }

        return DeliveryModel.createDelivery(idOrder, idCourier);
    }

    // editDeliveryStatus
    public static boolean editDeliveryStatus(
            String idOrder,
            String idCourier,
            String status
    ) {

        if (
            !status.equals("Pending") &&
            !status.equals("In Progress") &&
            !status.equals("Delivered")
        ) {
            System.out.println("Invalid status");
            return false;
        }

        return DeliveryModel.editDeliveryStatus(idOrder, idCourier, status);
    }
}
