package models.entity_models;

public class CartItem {
    private String idCustomer, idProduct;
    private int count;

    private String name;
    private double price;

    //Creates a new CartItem instance.

    public CartItem(String idCustomer, String idProduct, int count) {
        this.idCustomer = idCustomer;
        this.idProduct = idProduct;
        this.count = count;
    }


    //Creates a new CartItem instance.


    public CartItem(String name, int quantity, double price) {
        this.name = name;
        this.count = quantity;
        this.price = price;
    }

    // Creates a new CartItem instance.


    public CartItem(String idProduct, String name, int quantity, double price) {
        this.idProduct = idProduct;
        this.name = name;
        this.count = quantity;
        this.price = price;
    }

    //Returns id customer.

    public String getIdCustomer() { return idCustomer; }
    public String getIdProduct() { return idProduct; }
    // Returns count.
  

    public int getCount() { return count; }

    //Returns name.
 

    public String getName() { return name; }
    public int getQuantity() { return count; }
    //Returns price.


    public double getPrice() { return price; }
    public double getTotal() { return price * count; }
}