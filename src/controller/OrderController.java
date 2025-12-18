package controller;

import java.util.List;

import models.data_handling.CartModel;
import models.data_handling.DeliveryModel;
import models.data_handling.OrderDetailsModel;
import models.data_handling.OrderHeaderModel;
import models.data_handling.ProductModel;
import models.data_handling.UserModel;
import models.entity_models.CartItem;
import models.entity_models.Product;
import models.entity_models.Promo;
import models.entity_models.OrderHeader;
import models.entity_models.AllOrdersRow;

public class OrderController {

	 //Performs the checkout operation.
	 

	public static OrderHeader checkout(
	        String idCustomer,
	        List<CartItem> cartItems,
	        String promoCode
	) {

	    double totalAmount = 0;

	    // Calculate the total amount.
	    for (CartItem item : cartItems) {
	        Product product = ProductModel.findProductByID(
	            Integer.parseInt(item.getIdProduct())
	        );

	        if (product == null || item.getCount() > product.getStock()) {
	            return null;
	        }

	        totalAmount += product.getPrice() * item.getCount();
	    }

	    // Apply promo code (optional).
	    String idPromo = null;
	    if (promoCode != null && !promoCode.isBlank()) {
	        Promo promo = PromoController.getPromo(promoCode);
	        if (promo == null) return null;

	        idPromo = promo.getIdPromo();
	        totalAmount -= totalAmount * promo.getDiscountPercentage()/100;
	    }

	    // Create the order header only once.
	    int customerIntId = Integer.parseInt(idCustomer);
	    double balance = UserModel.getCustomerBalance(customerIntId);
	    if (balance < totalAmount) return null;

	    OrderHeader order = OrderHeaderModel.createOrderHeader(
	        idCustomer,
	        idPromo,
	        totalAmount
	    );

	    if (order == null) return null;

	    // Save the order details.
	    for (CartItem item : cartItems) {
	        OrderDetailsModel.createOrderDetail(
	            order.getIdOrder(),
	            item.getIdProduct(),
	            item.getCount()
	        );

	        // Update product stock.
	        ProductModel.updateStock(
	            Integer.parseInt(item.getIdProduct()),
	            ProductModel.findProductByID(
	                Integer.parseInt(item.getIdProduct())
	            ).getStock() - item.getCount()
	        );
	    }

	    // Deduct the customer balance and clear the cart.
	    UserModel.updateBalance(customerIntId, balance - totalAmount);
	    CartModel.clearCart(customerIntId);

	    return order;
	}


    public static List<AllOrdersRow> getAllOrdersForAdmin() {
        return OrderHeaderModel.getAllOrdersForAdmin();
    }
    
    public static boolean courierUpdateStatus(String idOrder, String idCourier, String deliveryStatus) {
        idOrder = idOrder.trim();
        idCourier = idCourier.trim();

        boolean okDelivery = DeliveryModel.editDeliveryStatus(idOrder, idCourier, deliveryStatus);
        System.out.println("okDelivery=" + okDelivery);

        if (!okDelivery) {
            
            return false;
        }

        // Map delivery status to the order header status (you can keep them the same if you prefer).
        String orderStatus = deliveryStatus;
        if ("DELIVERED".equals(deliveryStatus)) orderStatus = "COMPLETED";

        boolean okOrder = OrderHeaderModel.editOrderHeaderStatus(idOrder, orderStatus);
        System.out.println("okOrder=" + okOrder);

        return okOrder;
    }




}