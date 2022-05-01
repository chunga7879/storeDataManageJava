package customertest;

import model.customer.VipCustomer;
import model.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VipCustomerTest {
    private VipCustomer c;

    @BeforeEach
    public void runBefore() {
        c = new VipCustomer("Chunga Lee", 12248333);
    }

    @Test
    public void constructorTest() {
        assertEquals("Chunga Lee" , c.getCustomerName());
        assertEquals(12248333, c.getCustomerTelNum());
        assertEquals("VIP", c.getCustomerMemLevel());
        assertEquals(0, c.getPoint());
        assertEquals(0.15, c.getBonusPointRate());
        assertEquals(0.10, c.getPriceDiscount());
        assertEquals(4, c.getFreeMovieTicketperMonth());
        assertTrue(c.getIsTicketForVipParty());
        assertTrue(c.getIsUsingVipLounge());

    }

    @Test
    public void testBuyProductWhenBuyOneProduct() {
        Product product = new Product("Chocolate", 1.5, 3000);
        c.buyProduct(product, 1, 2020, 10,1, 15, 30);
        assertEquals(1.35, c.getTotalSpent());
        assertEquals(0.203,c.getPoint());
        assertEquals(1,c.getPurchaseHistory().size());
        assertEquals("Product: Chocolate, Num: 1, Spent: 1.35 dollars, Date: 2020/10/1, Time: 15:30",
                c.getPurchaseHistory().get(0));

        assertEquals(2999,product.getNumberOfLeft());
        assertEquals(1,product.getNumberOfSold());
    }

    @Test
    public void testBuyProductWhenBuySomeProduct() {
        Product product1 = new Product("Chocolate", 1.5, 3000);
        Product product2 = new Product("Candy", 1.0, 1000);
        Product product3 = new Product("Dish", 40, 500);

        c.buyProduct(product1, 2, 2020, 10,1, 15, 30);
        assertEquals(2.7, c.getTotalSpent());
        assertEquals(0.405, c.getPoint());
        assertEquals(1,c.getPurchaseHistory().size());
        assertEquals("Product: Chocolate, Num: 2, Spent: 2.7 dollars, Date: 2020/10/1, Time: 15:30",
                c.getPurchaseHistory().get(0));
        assertEquals(2998,product1.getNumberOfLeft());
        assertEquals(2,product1.getNumberOfSold());

        c.buyProduct(product2, 5, 2020, 10,3, 18, 30);
        assertEquals(7.2, c.getTotalSpent());
        assertEquals(1.08, c.getPoint());
        assertEquals(2,c.getPurchaseHistory().size());
        assertEquals("Product: Candy, Num: 5, Spent: 4.5 dollars, Date: 2020/10/3, Time: 18:30",
                c.getPurchaseHistory().get(1));
        assertEquals(995,product2.getNumberOfLeft());
        assertEquals(5,product2.getNumberOfSold());

        c.buyProduct(product3, 3, 2020, 10,5, 10, 10);
        assertEquals(115.2, c.getTotalSpent());
        assertEquals(17.28, c.getPoint());
        assertEquals(3,c.getPurchaseHistory().size());
        assertEquals("Product: Dish, Num: 3, Spent: 108.0 dollars, Date: 2020/10/5, Time: 10:10",
                c.getPurchaseHistory().get(2));
        assertEquals(497,product3.getNumberOfLeft());
        assertEquals(3,product3.getNumberOfSold());

    }

    @Test
    public void testToString() {
        assertEquals( c.toString(), "Chunga Lee(Membership: VIP) : Tel.12248333");
    }
}
