package customertest;

import model.customer.SilverCustomer;
import model.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SilverCustomerTest {
    private SilverCustomer silverCustomer;

    @BeforeEach
    public void runBefore() {
        silverCustomer = new SilverCustomer("Daniel Yu", 19283733);
    }

    @Test
    public void testConstructor() {
        assertEquals("Daniel Yu" , silverCustomer.getCustomerName());
        assertEquals(19283733, silverCustomer.getCustomerTelNum());
        assertEquals("Silver", silverCustomer.getCustomerMemLevel());
        assertEquals(0, silverCustomer.getPoint());
        assertEquals(0.05, silverCustomer.getBonusPointRate());

    }

    @Test
    public void testBuyProductWhenBuyOneProduct() {
        Product product = new Product("Chocolate", 1.5, 3000);
        silverCustomer.buyProduct(product, 1, 2020, 10, 1, 15, 30);
        assertEquals(1.5, silverCustomer.getTotalSpent());
        assertEquals(0.075,silverCustomer.getPoint());
        assertEquals(1,silverCustomer.getPurchaseHistory().size());
        assertEquals("Product: Chocolate, Num: 1, Spent: 1.5 dollars, Date: 2020/10/1, Time: 15:30",
                silverCustomer.getPurchaseHistory().get(0));

        assertEquals(2999,product.getNumberOfLeft());
        assertEquals(1,product.getNumberOfSold());

    }

    @Test
    public void testBuyProductWhenBuySomeProduct() {
        Product product1 = new Product("Chocolate", 1.5, 3000);
        Product product2 = new Product("Candy", 1.0, 1000);
        Product product3 = new Product("Dish", 40, 500);

        silverCustomer.buyProduct(product1, 2, 2020, 10,1, 15, 30);
        assertEquals(3.0, silverCustomer.getTotalSpent());
        assertEquals(0.15, silverCustomer.getPoint());
        assertEquals(1,silverCustomer.getPurchaseHistory().size());
        assertEquals("Product: Chocolate, Num: 2, Spent: 3.0 dollars, Date: 2020/10/1, Time: 15:30",
                silverCustomer.getPurchaseHistory().get(0));
        assertEquals(2998,product1.getNumberOfLeft());
        assertEquals(2,product1.getNumberOfSold());

        silverCustomer.buyProduct(product2, 5, 2020, 10,3, 18, 30);
        assertEquals(8.0, silverCustomer.getTotalSpent());
        assertEquals(0.4, silverCustomer.getPoint());
        assertEquals(2,silverCustomer.getPurchaseHistory().size());
        assertEquals("Product: Candy, Num: 5, Spent: 5.0 dollars, Date: 2020/10/3, Time: 18:30",
                silverCustomer.getPurchaseHistory().get(1));
        assertEquals(995,product2.getNumberOfLeft());
        assertEquals(5,product2.getNumberOfSold());

        silverCustomer.buyProduct(product3, 3, 2020, 10,5, 10, 10);
        assertEquals(128.0, silverCustomer.getTotalSpent());
        assertEquals(6.4, silverCustomer.getPoint());
        assertEquals(3,silverCustomer.getPurchaseHistory().size());
        assertEquals("Product: Dish, Num: 3, Spent: 120.0 dollars, Date: 2020/10/5, Time: 10:10",
                silverCustomer.getPurchaseHistory().get(2));
        assertEquals(497,product3.getNumberOfLeft());
        assertEquals(3,product3.getNumberOfSold());

    }

    @Test
    public void testToString() {
        assertEquals( silverCustomer.toString(), "Daniel Yu(Membership: Silver) : Tel.19283733");
    }



}
