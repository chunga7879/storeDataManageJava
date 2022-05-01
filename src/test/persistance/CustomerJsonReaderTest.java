package persistance;

import model.customer.Customer;
import model.customer.CustomerList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CustomerJsonReaderTest extends CustomerJsonTest{

    @Test
    void testCustomerReaderNonExistentFile() {
        CustomerJsonReader reader = new CustomerJsonReader("./data/noSuchFileCustomer.json");
        try {
            CustomerList customerList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCustomerList() {
        CustomerJsonReader reader = new CustomerJsonReader("./data/testCustomerWriterEmptyCustomerList.json");
        try {
            CustomerList customerList = reader.read();
            assertEquals("Customer List", customerList.getCustomerListName());
            assertEquals(0, customerList.numCustomer());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCustomerList() {
        CustomerJsonReader reader = new CustomerJsonReader("./data/testWriterGeneralCustomerList.json");
        try {
            CustomerList customerList = reader.read();
            assertEquals("Customer List", customerList.getCustomerListName());
            List<Customer> customers = customerList.getAllCustomerList();
            assertEquals(3, customers.size());
            checkCustomer("Mike", 12345678, customers.get(0));
            checkCustomer("Julie", 67891, customers.get(1));
            checkCustomer("Suzin", 13579, customers.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
