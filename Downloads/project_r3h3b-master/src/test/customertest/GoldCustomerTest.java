package customertest;

import model.customer.GoldCustomer;
import model.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoldCustomerTest {

    private GoldCustomer goldCustomer;

    @BeforeEach
    public void runBefore() {
        goldCustomer = new GoldCustomer("Mary Kim", 13928593);
    }

    @Test
    public void testConstructor() {
        assertEquals("Mary Kim" , goldCustomer.getCustomerName());
        assertEquals(13928593, goldCustomer.getCustomerTelNum());
        assertEquals("Gold", goldCustomer.getCustomerMemLevel());
        assertEquals(0, goldCustomer.getPoint());
        assertEquals(0.10, goldCustomer.getBonusPointRate());
        assertEquals(0.05,goldCustomer.getPriceDiscount());
        assertEquals(2,goldCustomer.getFreeMovieTicketperMonth());
    }

    @Test
    public void testBuyProductWhenBuyOneProduct() {
        Product product = new Product("Chocolate", 1.5, 3000);
        goldCustomer.buyProduct(product, 1, 2020, 10, 1, 15, 30);
        assertEquals(1.425, goldCustomer.getTotalSpent());
        assertEquals(0.143,goldCustomer.getPoint());
        assertEquals(1,goldCustomer.getPurchaseHistory().size());
        assertEquals("Product: Chocolate, Num: 1, Spent: 1.425 dollars, Date: 2020/10/1, Time: 15:30",
                goldCustomer.getPurchaseHistory().get(0));

        assertEquals(2999,product.getNumberOfLeft());
        assertEquals(1,product.getNumberOfSold());

    }

    @Test
    public void testBuyProductWhenBuySomeProduct() {
        Product product1 = new Product("Chocolate", 1.5, 3000);
        Product product2 = new Product("Candy", 1.0, 1000);
        Product product3 = new Product("Dish", 40, 500);

        goldCustomer.buyProduct(product1, 2, 2020, 10,1, 15, 30);
        assertEquals(2.85, goldCustomer.getTotalSpent());
        assertEquals(0.285, goldCustomer.getPoint());
        assertEquals(1,goldCustomer.getPurchaseHistory().size());
        assertEquals("Product: Chocolate, Num: 2, Spent: 2.85 dollars, Date: 2020/10/1, Time: 15:30",
                goldCustomer.getPurchaseHistory().get(0));
        assertEquals(2998,product1.getNumberOfLeft());
        assertEquals(2,product1.getNumberOfSold());

        goldCustomer.buyProduct(product2, 5, 2020, 10,3, 18, 30);
        assertEquals(7.6, goldCustomer.getTotalSpent());
        assertEquals(0.76, goldCustomer.getPoint());
        assertEquals(2,goldCustomer.getPurchaseHistory().size());
        assertEquals("Product: Candy, Num: 5, Spent: 4.75 dollars, Date: 2020/10/3, Time: 18:30",
                goldCustomer.getPurchaseHistory().get(1));
        assertEquals(995,product2.getNumberOfLeft());
        assertEquals(5,product2.getNumberOfSold());

        goldCustomer.buyProduct(product3, 3, 2020, 10,5, 10, 10);
        assertEquals(121.6, goldCustomer.getTotalSpent());
        assertEquals(12.16, goldCustomer.getPoint());
        assertEquals(3,goldCustomer.getPurchaseHistory().size());
        assertEquals("Product: Dish, Num: 3, Spent: 114.0 dollars, Date: 2020/10/5, Time: 10:10",
                goldCustomer.getPurchaseHistory().get(2));
        assertEquals(497,product3.getNumberOfLeft());
        assertEquals(3,product3.getNumberOfSold());

    }

    @Test
    public void testToString() {
        assertEquals( goldCustomer.toString(), "Mary Kim(Membership: Gold) : Tel.13928593");
    }


}
