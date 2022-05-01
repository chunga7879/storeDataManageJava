package producttest;

import model.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {
    Product product;

    @BeforeEach
    public void runBefore() {
        product = new Product("Chocolate", 1.5, 3000);
        product.setFloor(5);
        product.setManager("Ms.Kim");
    }

    @Test
    public void testSellProduct() {
        product.sellProduct(1500);
        assertEquals(1500, product.getNumberOfLeft());
        assertEquals(1500, product.getNumberOfSold());

        product.sellProduct(700);
        assertEquals(800, product.getNumberOfLeft());
        assertEquals(2200, product.getNumberOfSold());

    }

    @Test
    public void testToString() {
        // when nothing is sold

        assertEquals("Chocolate(1.5 dollars) : Left = 3000, Sold = 0, Floor = 5, Manager = Ms.Kim",
                product.toString());

        // when some is sold
        product.sellProduct(1000);
        assertEquals("Chocolate(1.5 dollars) : Left = 2000, Sold = 1000, Floor = 5, Manager = Ms.Kim",
                product.toString());

    }

}
