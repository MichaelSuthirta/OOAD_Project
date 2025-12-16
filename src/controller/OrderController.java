package controller;

import java.util.List;

import models.data_handling.CartModel;
import models.data_handling.OrderDetailsModel;
import models.data_handling.OrderHeaderModel;
import models.data_handling.ProductModel;
import models.data_handling.UserModel;
import models.entity_models.CartItem;
import models.entity_models.Product;
import models.entity_models.Promo;
import models.entity_models.OrderHeader;

public class OrderController {

	public static OrderHeader checkout(
	        String idCustomer,
	        List<CartItem> cartItems,
	        String promoCode
	) {

	    double totalAmount = 0;

	    // Hitung total
	    for (CartItem item : cartItems) {
	        Product product = ProductModel.findProductByID(
	            Integer.parseInt(item.getIdProduct())
	        );

	        if (product == null || item.getCount() > product.getStock()) {
	            return null;
	        }

	        totalAmount += product.getPrice() * item.getCount();
	    }

	    // Promo (optional)
	    String idPromo = null;
	    if (promoCode != null && !promoCode.isBlank()) {
	        Promo promo = PromoController.getPromo(promoCode);
	        if (promo == null) return null;

	        idPromo = promo.getIdPromo();
	        totalAmount -= totalAmount * promo.getDiscountPercentage();
	    }

	    // Buat order header SEKALI
	    OrderHeader order = OrderHeaderModel.createOrderHeader(
	        idCustomer,
	        idPromo,
	        totalAmount
	    );

	    if (order == null) return null;

	    // Simpan detail
	    for (CartItem item : cartItems) {
	        OrderDetailsModel.createOrderDetail(
	            order.getIdOrder(),
	            item.getIdProduct(),
	            item.getCount()
	        );

	        // Update stok
	        ProductModel.updateStock(
	            Integer.parseInt(item.getIdProduct()),
	            ProductModel.findProductByID(
	                Integer.parseInt(item.getIdProduct())
	            ).getStock() - item.getCount()
	        );
	    }

	    return order;
	}
}
