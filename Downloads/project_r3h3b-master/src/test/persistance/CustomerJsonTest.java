package persistance;

import model.customer.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerJsonTest {

    protected void checkCustomer(String name, long telNum, Customer customer) {
        assertEquals(name, customer.getCustomerName());
        assertEquals(telNum, customer.getCustomerTelNum());
    }
}
