package model.customer;

import model.product.Product;

import java.util.List;

public class GoldCustomer extends Customer {

    double priceDiscount;
    int freeMovieTicketperMonth;

    public GoldCustomer(String customerName, long customerTelNum) {
        super(customerName, customerTelNum);
        customerMemLevel = "Gold";
        bonusPointRate = 0.10;
        priceDiscount = 0.05;
        freeMovieTicketperMonth = 2;
    }

    // EFFECTS: get price discount
    public double getPriceDiscount() {
        return this.priceDiscount;
    }

    // EFFECTS: get the number of movie ticket provided per month
    public int getFreeMovieTicketperMonth() {
        return this.freeMovieTicketperMonth;
    }

    // REQUIRES: numberOfProduct > 0, 1 <= month <= 12, 1 <= day <= 29,30,31 depending on which month
    // MODIFIES: this and product
    // EFFECTS: call buyProduct method of super and calculate actual price by multiplying product price with
    // (1-priceDiscount) and calculate spent by multiplying numberOfProduct * product.getProductPrice()
    // and make purchaseInfo like "numberOfProduct - product name - spent - year/month/day hour:minute" and add
    // purchaseInfo to purchaseHistory and calculate point by adding the value of multiplying bonusPointRate and
    // spent to point before the purchase and finally add spent to totalSpent to get new total spent
    // and call sellProduct method
    @Override
    public void buyProduct(Product product, int numberOfProduct, int year, int month, int day, int hour, int minute) {
        super.buyProduct(product, numberOfProduct, year, month, day, hour, minute);
        double actualPrice = product.getProductPrice() * (1 - priceDiscount);
        double spent = numberOfProduct * actualPrice;
        double roundSpent = Math.round(spent * 1000) / 1000.0;
        String purchaseInfo = "Product: " + product.getProductName() + ", Num: " + numberOfProduct + ", Spent: "
                + roundSpent + " dollars, Date: " + year + "/"
                + month + "/" + day + ", Time: " + hour + ":" + minute;
        purchaseHistory.add(purchaseInfo);
        point = point + Math.round((bonusPointRate * spent) * 1000) / 1000.0;
        totalSpent = totalSpent + roundSpent;
        product.sellProduct(numberOfProduct);

    }


}
