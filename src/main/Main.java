package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

import controller.CartController;
import controller.CourierController;
import controller.DeliveryController;
import controller.OrderController;
import controller.ProductController;
import controller.UserController;
import models.data_handling.DeliveryModel;
import models.data_handling.OrderHeaderModel;
import models.data_handling.UserModel;
import models.entity_models.Product;
import models.entity_models.User;
import view.AddToCartView;
import view.AllCouriersView;
import view.AllOrdersView;
import view.AssignCourierView;
import view.CartView;
import view.EditDeliveryStatusView;
import view.EditProductStockView;
import view.EditProfileView;
import view.LoginView;
import view.OrderHistoryView;
import view.ProductListView;
import view.RegisterView;
import view.TopUpView;
import view.UpdateCartItemView;
import controller.OrderHistoryController;

public class Main extends Application{

	private Stage stage;
	private User currentUser;
	private int currentUserId = -1;

	//Performs the start operation.
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.stage = primaryStage;
		stage.setResizable(false);
		showLogin();
	}

	// =====================
	// Navigation / Helpers
	// =====================
	// Displays the info.
	 

	private void showInfo(String title, String message) {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle(title);
		a.setHeaderText(null);
		a.setContentText(message);
		a.showAndWait();
	}

	// Displays the error.
	 

	private void showError(String title, String message) {
		Alert a = new Alert(AlertType.ERROR);
		a.setTitle(title);
		a.setHeaderText(null);
		a.setContentText(message);
		a.showAndWait();
	}

	// Displays the login.


	private void showLogin() {
		LoginView lv = new LoginView(stage);
		lv.show();

		// Override the "Sign up" hyperlink action to navigate into the register flow that is already wired to the controller.
		// (This field has no getter, so we use reflection. This does not modify the View code.)
		try {
			java.lang.reflect.Field f = lv.getClass().getDeclaredField("signupLink");
			f.setAccessible(true);
			Object linkObj = f.get(lv);
			if (linkObj instanceof javafx.scene.control.Hyperlink) {
				((javafx.scene.control.Hyperlink) linkObj).setOnAction(e -> showRegister());
			}
		} catch (Exception ignore) {
			// If this fails, the default link behavior from the view will still work (UI navigation only).
		}

		lv.getLoginButton().setOnAction(e -> {
			String email = lv.getEmail();
			String pass = lv.getPassword();

			if (email == null || email.isBlank() || pass == null || pass.isBlank()) {
				showError("Login Failed", "Email and Password are required.");
				return;
			}

			User user = null;
			try {
				user = UserController.loginUser(email, pass);
			} catch (Exception ex) {
				showError("Login Error", "An error occurred while logging in (check database connection / query).\n" + ex.getMessage());
				return;
			}

			if (user == null) {
				showError("Login Failed", "Email / Password wrong.");
				return;
			}

			this.currentUser = user;
			try {
				this.currentUserId = UserModel.findIDByEmail(email);
			} catch (Exception ex) {
				this.currentUserId = -1;
			}

			switch (user.getRole().toLowerCase()) {
			case "admin":
				showAdminHome();
				break;
			case "courier":
				showCourierHome();
				break;
			default:
				showCustomerHome();
				break;
			}
		});
	}

	// Displays the register.
	 

	private void showRegister() {
		RegisterView rv = new RegisterView(stage);
		rv.show();

		rv.getRegisterButton().setOnAction(e -> {
			try {
				var customer = UserController.registerCustomer(
					rv.getName(),
					rv.getEmail(),
					rv.getPassword(),
					rv.getConfirmPassword(),
					rv.getPhone(),
					rv.getAddress(),
					rv.getGender() == null ? "" : rv.getGender()
				);

				if (customer == null) {
					showError("Register Failed", "Validation failed. Check your input against the rules.");
					return;
				}

				showInfo("Register Success", "Account created successfully. Please log in.");
				showLogin();
			} catch (Exception ex) {
				showError("Register Error", "An error occurred during registration (check database connection / query).\n" + ex.getMessage());
			}
		});
	}

	// Performs the build home scene operation.
	 

	private Scene buildHomeScene(String titleText, Button... buttons) {
		Label title = new Label(titleText);
		title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

		VBox root = new VBox(10);
		root.setPadding(new Insets(20));
		root.getChildren().add(title);
		root.getChildren().addAll(buttons);
		return new Scene(root, 420, 320);
	}

	//Performs the logout operation.
	

	private void logout() {
		this.currentUser = null;
		this.currentUserId = -1;
		showLogin();
	}

	// =====================
	// Customer Flow
	// =====================
	// Displays the customer home.
	 

	private void showCustomerHome() {
		Button productsBtn = new Button("Browse Products");
		Button cartBtn = new Button("Cart");
		Button topUpBtn = new Button("Top Up Balance");
		Button historyBtn = new Button("Order History");
		Button logoutBtn = new Button("Logout");
		Button editProfile = new Button("Edit Profile");

		productsBtn.setMaxWidth(Double.MAX_VALUE);
		cartBtn.setMaxWidth(Double.MAX_VALUE);
		topUpBtn.setMaxWidth(Double.MAX_VALUE);
		historyBtn.setMaxWidth(Double.MAX_VALUE);
		logoutBtn.setMaxWidth(Double.MAX_VALUE);
		editProfile.setMaxWidth(Double.MAX_VALUE);

		productsBtn.setOnAction(e -> showProductList());
		cartBtn.setOnAction(e -> showCart());
		topUpBtn.setOnAction(e -> showTopUp());
		historyBtn.setOnAction(e -> showOrderHistory());
		logoutBtn.setOnAction(e -> logout());
		editProfile.setOnAction(e -> showEditProfile());

		stage.setTitle("JoymarKet - Customer");
		stage.setScene(buildHomeScene("Customer Menu", productsBtn, cartBtn, topUpBtn, historyBtn, editProfile, logoutBtn));
		stage.show();
	}

	
	// Displays the edit profile.
	 

	private void showEditProfile() {
	    String idUser = currentUser.getIdUser(); 

	    User user = UserController.getUserByID(idUser);

	    EditProfileView v = new EditProfileView(stage);
	    v.show(user.getFullName(), user.getEmail(), user.getPhone(), user.getAddress());

	    v.getSaveButton().setOnAction(e -> {
	        String err = UserController.editProfile(
	            Integer.parseInt(idUser.trim()),   
	            v.getFullName(),
	            v.getEmail(),
	            v.getPassword(),
	            v.getPhone(),
	            v.getAddress()
	        );

	        if (err == null) {
	            new Alert(Alert.AlertType.INFORMATION, "Profile updated!").showAndWait();
	        } else {
	            new Alert(Alert.AlertType.ERROR, err).showAndWait();
	        }
	    });

	    v.getBackButton().setOnAction(e -> showCustomerHome());
	}


	
	// Displays the product list.
	 

	private void showProductList() {
		ProductListView pv = new ProductListView(stage);
		pv.show();

		// NOTE: This project does not set the cell value factory and does not load data yet.
		// Main only handles navigation so the application can run end-to-end.

		pv.getBackButton().setOnAction(e -> showCustomerHome());
		pv.getAddToCartButton().setOnAction(e -> {
			Product selected = pv.getProductTable().getSelectionModel().getSelectedItem();
			if (selected == null) {
				showError("Add to Cart", "Choose a product first.");
				return;
			}
			showAddToCart(selected.getIdProduct(), selected.getName());
		});
	}

	// Displays the add to cart.
	 

	private void showAddToCart(String productId, String productName) {
		AddToCartView av = new AddToCartView(stage);
		av.show(productName);

		av.getBackButton().setOnAction(e -> showProductList());
		av.getAddButton().setOnAction(e -> {
			String qty = av.getQuantity();
			try {
				int q = Integer.parseInt(qty);
				if (q < 1) {
					showError("Add to Cart", "Quantity minimal 1.");
					return;
				}

				CartController.addItem(Integer.toString(currentUserId), productId, q);
				showInfo("Add to Cart", "Item added to cart.");
				showProductList();
			} catch (Exception ex) {
				showError("Add to Cart", "Quantity must be numeric.");
			}
		});
	}

	//Displays the cart.
	 

	private void showCart() {
		CartView cv = new CartView(stage);
		cv.show(currentUserId);

		cv.getBackButton().setOnAction(e -> showCustomerHome());
		cv.getCheckoutButton().setOnAction(e -> {
			if (cv.getCartTable().getItems() == null || cv.getCartTable().getItems().isEmpty()) {
				showError("Checkout Failed", "Cart empty.");
				return;
			}

			TextInputDialog dialog = new TextInputDialog("");
			dialog.setTitle("Promo Code");
			dialog.setHeaderText(null);
			dialog.setContentText("Enter promo code (optional):");

			String promoCode = dialog.showAndWait().orElse("");

			var order = OrderController.checkout(
				Integer.toString(currentUserId),
				new ArrayList<>(cv.getCartTable().getItems()),
				promoCode
			);

			if (order == null) {
				showError("Checkout Failed", "Checkout failed (invalid promo / insufficient stock / insufficient balance).");
				return;
			}

			showInfo("Checkout Success", "Order successfully created. ID Order: " + order.getIdOrder());
			showCart();
		});
	}

	// Displays the update cart item.
	 

	private void showUpdateCartItem(String productName, int currentQty) {
		UpdateCartItemView uv = new UpdateCartItemView(stage);
		uv.show(productName, currentQty);

		uv.getCancelButton().setOnAction(e -> showCart());
		uv.getUpdateButton().setOnAction(e -> {
			try {
				int q = Integer.parseInt(uv.getNewQty());
				if (q < 1) {
					showError("Update Item", "Quantity minimal 1.");
					return;
				}
				showInfo("Update Item", "(Placeholder) Quantity cart updated.");
				showCart();
			} catch (Exception ex) {
				showError("Update Item", "Quantity must be numeric.");
			}
		});
	}

	// Displays the top up.
	 

	private void showTopUp() {
		TopUpView tv = new TopUpView(stage);
		tv.show();

		tv.getBackButton().setOnAction(e -> showCustomerHome());
		tv.getTopUpButton().setOnAction(e -> {
			try {
				String amt = tv.getAmount();
				var customer = UserController.topUpBalance(Integer.toString(currentUserId), amt);
				if (customer == null) {
					showError("Top Up Failed", "Validation failed / user is not a customer.");
					return;
				}
				showInfo("Top Up Success", "Balance added successfully.");
				showCustomerHome();
			} catch (Exception ex) {
				showError("Top Up Error", "An error occurred while topping up (check database connection/query).\n" + ex.getMessage());
			}
		});
	}

	//Displays the order history.
	 

	private void showOrderHistory() {
		OrderHistoryView ohv = new OrderHistoryView(stage);
		ohv.show();
		ohv.getOrderHistoryTable().setItems(OrderHistoryController.getOrderHistory(currentUserId));
		ohv.getBackButton().setOnAction(e -> showCustomerHome());
	}

	// =====================
	// Admin Flow
	// =====================
	// Displays the admin home.
	 

	private void showAdminHome() {
		Button editStockBtn = new Button("Edit Product Stock");
		Button assignCourierBtn = new Button("Assign Order to Courier");
		Button allOrdersBtn = new Button("View All Orders");
		Button logoutBtn = new Button("Logout");
		Button viewCourierBtn = new Button("View All Couriers");

		editStockBtn.setMaxWidth(Double.MAX_VALUE);
		assignCourierBtn.setMaxWidth(Double.MAX_VALUE);
		allOrdersBtn.setMaxWidth(Double.MAX_VALUE);
		logoutBtn.setMaxWidth(Double.MAX_VALUE);
		viewCourierBtn.setMaxWidth(Double.MAX_VALUE);

		editStockBtn.setOnAction(e -> showEditStock());
		assignCourierBtn.setOnAction(e -> showAssignCourier());
		allOrdersBtn.setOnAction(e -> showAllOrders());
		logoutBtn.setOnAction(e -> logout());
		viewCourierBtn.setOnAction(e -> showAllCouriers());

		stage.setTitle("JoymarKet - Admin");
		stage.setScene(buildHomeScene("Admin Menu", editStockBtn, assignCourierBtn, allOrdersBtn, logoutBtn, viewCourierBtn));
		stage.show();
	}

	// Displays the edit stock.
	 

	private void showEditStock() {
		EditProductStockView ev = new EditProductStockView(stage);
		ev.show();

		
		ev.getProductBox().getItems().addAll("1", "2", "3");

		ev.getBackButton().setOnAction(e -> showAdminHome());
		ev.getSaveButton().setOnAction(e -> {
			String productId = ev.getProductBox().getValue();
			if (productId == null || productId.isBlank()) {
				showError("Edit Stock", "Pilih product dulu.");
				return;
			}
			var updated = ProductController.editStock(productId, ev.getStock());
			if (updated == null) {
				showError("Edit Stock", "Validation failed (stock numeric & cannot be negative).\nOr DB error.");
				return;
			}
			showInfo("Edit Stock", "Stock updated");
		});
	}

	// Displays the assign courier.
	 

	private void showAssignCourier() {
	    AssignCourierView av = new AssignCourierView(stage);
	    av.show();

	    
	    av.getOrderIDBox().getItems().clear();
	    for (var row : OrderHeaderModel.getAllOrdersForAdmin()) {
	        av.getOrderIDBox().getItems().add(row.getOrderId()); 
	    }

	   // ✅ Populate couriers from the DB (users with role=courier) and store as "id - name".
	    av.getCourierBox().getItems().clear();
	    for (var c : CourierController.getAllCouriers()) {
	        av.getCourierBox().getItems().add(c.getIdUser() + " - " + c.getFullName());
	    }

	    av.getBackButton().setOnAction(e -> showAdminHome());

	    av.getAssignButton().setOnAction(e -> {
	        String idOrder = av.getOrderIDBox().getValue();
	        String courierDisplay = av.getCourierBox().getValue();

	        if (idOrder == null || courierDisplay == null) {
	            showError("Assign Courier", "Order ID and Courier must be selected.");
	            return;
	        }

	        // Extract idCourier from the "id - name" value.
	        String idCourier = courierDisplay.split(" - ")[0].trim();

	        boolean ok = DeliveryModel.assignCourierToOrder(idOrder, idCourier);

	        if (ok) {
	            
	            OrderHeaderModel.editOrderHeaderStatus(idOrder, "In Progress");
	            showInfo("Assign Courier", "Courier successfully assigned to order, order status also updated.");
	        } else {
	            showError("Assign Courier", "Assign failed (check delivery table/courier ID).");
	        }
	    });
	}


	//Displays the all orders.
	 

	private void showAllOrders() {
		AllOrdersView aov = new AllOrdersView(stage);
		aov.show();
		aov.getOrderTable().getItems().setAll(OrderController.getAllOrdersForAdmin());
		aov.getBackButton().setOnAction(e -> showAdminHome());
	}

	// =====================
	// Courier Flow
	// =====================
	//Displays the courier home.
	 
	private void showCourierHome() {
		Button editStatusBtn = new Button("Edit Delivery Status");
		Button logoutBtn = new Button("Logout");

		editStatusBtn.setMaxWidth(Double.MAX_VALUE);
		logoutBtn.setMaxWidth(Double.MAX_VALUE);

		editStatusBtn.setOnAction(e -> showEditDeliveryStatus());
		logoutBtn.setOnAction(e -> logout());

		stage.setTitle("JoymarKet - Courier");
		stage.setScene(buildHomeScene("Courier Menu", editStatusBtn, logoutBtn));
		stage.show();
	}

	// Displays the edit delivery status.
	 

	private void showEditDeliveryStatus() {
	    EditDeliveryStatusView v = new EditDeliveryStatusView(stage);
	    v.show();

	    // Populate the list of orders assigned to the courier (temporary: read from the delivery table).
	    // Easiest approach: add a model method to fetch the order list by courier.
	    v.getOrderBox().getItems().setAll(DeliveryModel.getAssignedOrderIds(String.valueOf(currentUser.getIdUser())));

	    v.getUpdateButton().setOnAction(e -> {
	        String idOrder = v.getOrderBox().getValue();
	        String status = v.getStatus();
	        String idCourier = String.valueOf(currentUser.getIdUser());

	        if (idOrder == null || status == null) return;

	        boolean ok = DeliveryController.courierUpdateStatus(idOrder, idCourier, status);

	        Alert a = new Alert(ok ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
	        a.setHeaderText(null);
	        a.setContentText(ok ? "Status updated!" : "Update failed!");
	        a.showAndWait();
	    });

	    v.getBackButton().setOnAction(e -> showCourierHome());
	}

	
	//Displays the all couriers.
	 

	private void showAllCouriers() {
	    AllCouriersView acv = new AllCouriersView(stage); 
	    acv.show();

	    // Populate the table using the controller data.
	    acv.getCourierTable().getItems().setAll(CourierController.getAllCouriers());

	    // tombol back balik ke menu admin
	    acv.getBackButton().setOnAction(e -> showAdminHome());
	}


}