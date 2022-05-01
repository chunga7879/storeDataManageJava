package ui;

import model.customer.CustomerList;
import model.product.ProductList;
import persistance.CustomerJsonReader;
import persistance.CustomerJsonWriter;
import persistance.ProductJsonReader;
import persistance.ProductJsonWriter;
import ui.graphic.LoadScreen;

public class GraphicApp {
    public CustomerList customerList =  new CustomerList("Customer List");
    public ProductList productList = new ProductList("Product List");
    public static final String JSON_STORE_CUSTOMER = "./data/customerList2.txt";
    public static final String JSON_STORE_PRODUCT = "./data/productList2.txt";
    public CustomerJsonWriter customerJsonWriter = new CustomerJsonWriter(JSON_STORE_CUSTOMER);
    public CustomerJsonReader customerJsonReader = new CustomerJsonReader(JSON_STORE_CUSTOMER);
    public ProductJsonWriter productJsonWriter = new ProductJsonWriter(JSON_STORE_PRODUCT);
    public ProductJsonReader productJsonReader = new ProductJsonReader(JSON_STORE_PRODUCT);

    public static void main(String[] args) {
        GraphicApp graphicApp = new GraphicApp();
        new LoadScreen(graphicApp);
    }

}
