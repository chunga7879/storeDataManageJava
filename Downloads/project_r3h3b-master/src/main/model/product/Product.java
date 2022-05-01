package model.product;

import org.json.JSONObject;
import persistance.WritableProduct;

public class Product implements WritableProduct {
    String productName;
    double productPrice;
    int numberOfSold;
    int numberOfProduct;
    String manager;
    int floor;

    //EFFECTS: construct new product with given productName, productPrice, numberOfProduct
    // print "Not yet set a manager" for manager and 0 for floor at first
    public Product(String productName, double productPrice, int numberOfProduct) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.numberOfProduct = numberOfProduct;
        this.manager = "Not yet set a manager";
        this.floor = 0;
    }

    // EFFECTS: get product name
    public String getProductName() {
        return this.productName;
    }

    // EFFECTS: get product price
    public double getProductPrice() {
        return this.productPrice;
    }

    // EFFECTS: get the number of product sold
    public int getNumberOfSold() {
        return this.numberOfSold;
    }

    // EFFECTS: set product's manager
    public void setManager(String manager) {
        this.manager = manager;
    }

    // EFFECTS: get number of product left
    public int getNumberOfLeft() {
        return this.numberOfProduct;
    }

    // EFFECTS: set product's floor
    public void setFloor(int floor) {
        this.floor = floor;
    }

    // EFFECTS: set product's number of sold
    public void setNumberOfSold(int numberOfSold) {
        this.numberOfSold = numberOfSold;
    }

    // REQUIRES: theNumberOfProduct > 0
    // MODIFIES : this
    // EFFECTS: subtract theNumberOfProduct from numberOfProduct and add theNumberOfProduct to numberOfSold
    public void sellProduct(int theNumberOfProduct) {
        numberOfProduct = numberOfProduct - theNumberOfProduct;
        numberOfSold = numberOfSold + theNumberOfProduct;
    }

    // EFFECTS: returns a string representation of customer
    @Override
    public String toString() {
        return productName + "(" + productPrice + " dollars) : " + "Left = " + numberOfProduct + ", Sold = "
                + numberOfSold + ", Floor = " + floor + ", Manager = " + manager;
    }

    @Override
    public JSONObject toProductJson() {
        JSONObject json = new JSONObject();
        json.put("name", productName);
        json.put("Price", productPrice);
        json.put("Manager", manager);
        json.put("Number Of Sold", numberOfSold);
        json.put("Number Of Product", numberOfProduct);
        json.put("Floor", floor);
        return json;
    }


}
