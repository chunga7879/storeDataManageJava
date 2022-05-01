package model.customer;

import model.product.Product;
import org.json.JSONArray;
import org.json.JSONObject;
import persistance.WritableCustomer;

import java.util.LinkedList;
import java.util.List;

public abstract class Customer implements WritableCustomer {


    // point, totalSpent rounded to decimal point three
    String customerName;
    long customerTelNum;
    double point;
    String customerMemLevel;
    List<String> purchaseHistory;
    double totalSpent;
    double bonusPointRate;



    // EFFECTS: create new customer with customer name, customerTelNum, customerPoint by starting with
    // Silver and empty purchaseHistory
    public Customer(String customerName, long customerTelNum) {
        this.customerName = customerName;
        this.customerTelNum = customerTelNum;
        this.point = 0;
        customerMemLevel = "Silver";
        purchaseHistory = new LinkedList<>();
    }

    // EFFECTS: get customer name
    public String getCustomerName() {
        return customerName;
    }

    // EFFECTS: get customer telephone number
    public long getCustomerTelNum() {
        return customerTelNum;
    }

    // EFFECTS: get customer point
    public double getPoint() {
        return point;
    }

    // EFFECTS: get customer total spent
    public double getTotalSpent() {
        return totalSpent;
    }

    // EFFECTS: get customer purchase history
    public List<String> getPurchaseHistory() {
        return purchaseHistory;
    }

    // EFFECTS: get customer membership level
    public String getCustomerMemLevel() {
        return this.customerMemLevel;
    }

    // EFFECTS: get customer bonus point rate
    public double getBonusPointRate() {
        return this.bonusPointRate;
    }

    // EFFECTS: set customer point as given point
    public void setPoint(double point) {
        this.point = point;
    }

    // EFFECTS: set customer total spent as given spent
    public void setTotalSpent(double totalSpent) {
        this.totalSpent = totalSpent;
    }

    // EFFECTS: set customer purchaseHistory as given purchaseHistory
    public void setPurchaseHistory(List<String> purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }

    // EFFECTS: calculate spent and make purchaseInfo like "numberOfProduct - product name - spent - year/month/day
    // hour:minute" and add purchaseInfo to purchaseHistory and calculate point and finally add spent to totalSpent
    // to get new total spend and call sellProduct method
    public void buyProduct(Product product, int numberOfProduct, int year, int month, int day, int hour, int minute) { }

    // EFFECTS: returns a string representation of customer
    @Override
    public String toString() {
        return customerName + "(Membership: " + customerMemLevel + ") : Tel." + customerTelNum;
    }

    @Override
    public JSONObject toCustomerJson() {
        JSONObject json = new JSONObject();
        json.put("name", customerName);
        json.put("Membership", customerMemLevel);
        json.put("Telephone Number", customerTelNum);
        json.put("Purchase History", putPurchaseHistoryToJsonArray());
        json.put("Point", point);
        json.put("Total Spent", totalSpent);
        return json;
    }

    // EFFECTS: put purchase history to json array list
    public JSONArray putPurchaseHistoryToJsonArray() {
        JSONArray jsonArray = new JSONArray();
        JSONObject purchaseHistoryJson = new JSONObject();
        for (int i = 0; i < getPurchaseHistory().size(); i++) {
            purchaseHistoryJson.put(i + " : " + "purchase", getPurchaseHistory().get(i));
            jsonArray.put(i, purchaseHistoryJson);
        }
        return jsonArray;
    }

}
