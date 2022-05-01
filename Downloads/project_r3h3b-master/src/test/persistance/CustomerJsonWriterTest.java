package persistance;

import model.customer.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CustomerJsonWriterTest extends CustomerJsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            CustomerList customerList = new CustomerList("Customer List");
            CustomerJsonWriter writer = new CustomerJsonWriter("./data/my\0illegal:fileName.customerjson");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCustomerList() {
        try {
            CustomerList customerList = new CustomerList("Customer List");
            CustomerJsonWriter writer =
                    new CustomerJsonWriter("./data/testCustomerWriterEmptyCustomerList.json");
            writer.open();
            writer.write(customerList);
            writer.close();

            CustomerJsonReader reader = new CustomerJsonReader("./data/testCustomerWriterEmptyCustomerList.json");
            customerList = reader.read();
            assertEquals("Customer List", customerList.getCustomerListName());
            assertEquals(0, customerList.numCustomer());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralCustomerList() {
        try {
            CustomerList customerList = new CustomerList("Customer List");
            customerList.addCustomerToProperList(new SilverCustomer("Mike", 12345678));
            customerList.addCustomerToProperList(new GoldCustomer("Julie", 67891));
            customerList.addCustomerToProperList(new VipCustomer("Suzin", 13579));
            CustomerJsonWriter writer = new CustomerJsonWriter("./data/testWriterGeneralCustomerList.json");
            writer.open();
            writer.write(customerList);
            writer.close();

            CustomerJsonReader reader = new CustomerJsonReader("./data/testWriterGeneralCustomerList.json");
            customerList = reader.read();
            assertEquals("Customer List", customerList.getCustomerListName());
            List<Customer> customerList1 = customerList.getAllCustomerList();
            assertEquals(3, customerList1.size());
            checkCustomer("Mike", 12345678, customerList1.get(0));
            checkCustomer("Julie", 67891, customerList1.get(1));
            checkCustomer("Suzin", 13579, customerList1.get(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }





}
