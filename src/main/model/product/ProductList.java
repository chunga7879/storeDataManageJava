package model.product;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.WritableProduct;

import java.util.LinkedList;
import java.util.List;

public class ProductList implements WritableProduct {
    String name;
    List<Product> productList;

    public ProductList(String name) {
        this.name = name;
        productList = new LinkedList<>();
    }

    public String getProductListName() {
        return name;
    }

    public List<Product> getProductList() {
        return this.productList;
    }

    // EFFECTS: returns number of products in this productList
    public int numProduct() {
        return productList.size();
    }

    public Product findProductWithName(String productName) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductName().equals(productName)) {
                return productList.get(i);
            }
        }
        return null;
    }


    // MODIFIES: this
    // EFFECTS: add product to product list
    public void addProductToList(Product product) {
        productList.add(product);
    }

    // REQUIRES: product list does not empty
    // MODIFIES: this
    // EFFECTS: remove product from product list and return true if the product is in the list, otherwise
    // return false
    public boolean removeProductFromList(Product product) {
        if (productList.contains(product)) {
            productList.remove(product);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public JSONObject toProductJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("products", productsToJson());
        return json;
    }

    // EFFECTS: returns products in this productList as a JSON array
    private JSONArray productsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Product p : productList) {
            jsonArray.put(p.toProductJson());
        }

        return jsonArray;
    }


}
