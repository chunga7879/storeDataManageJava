package customertest;

import model.customer.Customer;
import model.customer.SilverCustomer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {
    private Customer customer;

    @BeforeEach
    public void runBefore() {
        customer = new SilverCustomer("Sara Kim", 123456789);
    }

    @Test
    public void testSetPoint() {
        customer.setPoint(3.5);
        assertEquals(3.5, customer.getPoint());
    }

    @Test
    public void testSetTotalSpent() {
        customer.setTotalSpent(4000);
        assertEquals(4000, customer.getTotalSpent());
    }

    @Test
    public void testSetPurchaseHistory() {
        List<String> list = new LinkedList<>();
        list.add("Product: Dish, Num: 3, Spent: 114.0 dollars, Date: 2020/10/5, Time: 10:10");
        list.add("Product: Candy, Num: 5, Spent: 4.75 dollars, Date: 2020/10/3, Time: 18:30");

        customer.setPurchaseHistory(list);
        assertEquals(2, customer.getPurchaseHistory().size());

    }
}
