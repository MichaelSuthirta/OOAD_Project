package controller;

import models.data_handling.DeliveryModel;
import models.data_handling.OrderHeaderModel;
import models.data_handling.UserModel;
import models.entity_models.Delivery;
import models.entity_models.User;

public class DeliveryController {

    //  Performs the assign courier to order operation.
     

    public static boolean assignCourierToOrder(User currentUser, String idOrder, String idCourier) {

    	
    	if (currentUser == null || 
    	        !currentUser.getRole().equalsIgnoreCase("admin")) {
    	        System.out.println("Only admin can assign courier");
    	        return false;
    	    }
    	
        User courier = UserModel.findUserByID(Integer.parseInt(idCourier));
        if (courier == null || !courier.getRole().equalsIgnoreCase("courier")) {
            System.out.println("Courier not found");
            return false;
        }

        return DeliveryModel.assignCourierToOrder(idOrder, idCourier);
    }
    
    //Performs an admin action to assign courier.
     

    public static boolean adminAssignCourier(String idOrder, String idCourier) {
        boolean okAssign = DeliveryModel.assignCourierToOrder(idOrder, idCourier);
        if (!okAssign) return false;

        boolean okOrder = OrderHeaderModel.editOrderHeaderStatus(idOrder, "In Progress");
        return okOrder;
    }



    //Edits the delivery status.
     

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
    
    public static boolean courierUpdateStatus(String idOrder, String idCourier, String deliveryStatus) {

        // 1) update delivery
        boolean okDelivery = DeliveryModel.editDeliveryStatus(idOrder, idCourier, deliveryStatus);

        // 2) Also update the order header (this is what changes the status from Pending).
        String orderStatus = deliveryStatus;
        if ("In Progress".equalsIgnoreCase(deliveryStatus)) orderStatus = "PROCESSING";
        if ("Delivered".equalsIgnoreCase(deliveryStatus)) orderStatus = "COMPLETED";

        boolean okOrder = OrderHeaderModel.editOrderHeaderStatus(idOrder, orderStatus);

        return okDelivery && okOrder;
    }
}