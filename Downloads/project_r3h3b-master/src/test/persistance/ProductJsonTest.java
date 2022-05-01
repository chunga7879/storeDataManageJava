package persistance;

import model.product.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductJsonTest {

    protected void checkProduct(String name, double price, int numberOfProduct, Product product) {
        assertEquals(name, product.getProductName());
        assertEquals(price, product.getProductPrice());
        assertEquals(numberOfProduct, product.getNumberOfLeft());
    }
}
