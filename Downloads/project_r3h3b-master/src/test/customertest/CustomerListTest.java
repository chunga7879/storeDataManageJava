package customertest;

import model.customer.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerListTest {
    CustomerList customerList;

    @BeforeEach
    public void runBefore() {
        customerList = new CustomerList("Customer List");
    }

    @Test
    public void testAddCustomerToProperListWhenSilver() {
        Customer c = new SilverCustomer("Suzin", 19283764);
        customerList.addCustomerToProperList(c);
        assertEquals(1, customerList.getCustomerListWithGivenLevel("Silver").size());
        assertEquals(0, customerList.getCustomerListWithGivenLevel("Gold").size());
        assertEquals(0, customerList.getCustomerListWithGivenLevel("VIP").size());
        assertEquals(c, customerList.getCustomerListWithGivenLevel("Silver").get(0));

        Customer c2 = new SilverCustomer("Mary", 2947483);
        customerList.addCustomerToProperList(c2);
        assertEquals(2, customerList.getCustomerListWithGivenLevel("Silver").size());
        assertEquals(0, customerList.getCustomerListWithGivenLevel("Gold").size());
        assertEquals(0, customerList.getCustomerListWithGivenLevel("VIP").size());
        assertEquals(c2, customerList.getCustomerListWithGivenLevel("Silver").get(1));
    }

    @Test
    public void testAddCustomerToProperListWhenGold() {
        Customer c = new GoldCustomer("Suzin", 19283764);
        customerList.addCustomerToProperList(c);
        assertEquals(0, customerList.getCustomerListWithGivenLevel("Silver").size());
        assertEquals(1, customerList.getCustomerListWithGivenLevel("Gold").size());
        assertEquals(0, customerList.getCustomerListWithGivenLevel("VIP").size());
        assertEquals(c, customerList.getCustomerListWithGivenLevel("Gold").get(0));

        Customer c2 = new GoldCustomer("Mary", 2947483);
        customerList.addCustomerToProperList(c2);
        assertEquals(0, customerList.getCustomerListWithGivenLevel("Silver").size());
        assertEquals(2, customerList.getCustomerListWithGivenLevel("Gold").size());
        assertEquals(0, customerList.getCustomerListWithGivenLevel("VIP").size());
        assertEquals(c2, customerList.getCustomerListWithGivenLevel("Gold").get(1));
    }

    @Test
    public void testAddCustomerToProperListWhenVip() {
        Customer c = new VipCustomer("Suzin", 19283764);
        customerList.addCustomerToProperList(c);
        assertEquals(0, customerList.getCustomerListWithGivenLevel("Silver").size());
        assertEquals(0, customerList.getCustomerListWithGivenLevel("Gold").size());
        assertEquals(1, customerList.getCustomerListWithGivenLevel("VIP").size());
        assertEquals(c, customerList.getCustomerListWithGivenLevel("VIP").get(0));

        Customer c2 = new VipCustomer("Mary", 2947483);
        customerList.addCustomerToProperList(c2);
        assertEquals(0, customerList.getCustomerListWithGivenLevel("Silver").size());
        assertEquals(0, customerList.getCustomerListWithGivenLevel("Gold").size());
        assertEquals(2, customerList.getCustomerListWithGivenLevel("VIP").size());
        assertEquals(c2, customerList.getCustomerListWithGivenLevel("VIP").get(1));
    }

    @Test
    public void testAddCustomerToProperListWhenVariousLevel() {
        Customer c = new GoldCustomer("Suzin", 19283764);
        Customer c2 = new VipCustomer("Mary", 2947483);
        Customer c3 = new SilverCustomer("Daniel", 34937183);

        customerList.addCustomerToProperList(c);
        customerList.addCustomerToProperList(c2);
        customerList.addCustomerToProperList(c3);
        assertEquals(1, customerList.getCustomerListWithGivenLevel("Silver").size());
        assertEquals(1, customerList.getCustomerListWithGivenLevel("Gold").size());
        assertEquals(1, customerList.getCustomerListWithGivenLevel("VIP").size());
        assertEquals(c3, customerList.getCustomerListWithGivenLevel("Silver").get(0));
        assertEquals(c, customerList.getCustomerListWithGivenLevel("Gold").get(0));
        assertEquals(c2, customerList.getCustomerListWithGivenLevel("VIP").get(0));

    }

    @Test
    public void testRemoveCustomerFromListWhenSilver() {
        Customer c = new SilverCustomer("Suzin", 19283764);
        Customer c2 = new SilverCustomer("Mary", 2947483);
        customerList.addCustomerToProperList(c);
        customerList.addCustomerToProperList(c2);
        assertEquals(2, customerList.getCustomerListWithGivenLevel("Silver").size());

        customerList.removeCustomerFromList(c);
        assertEquals(1, customerList.getCustomerListWithGivenLevel("Silver").size());
        assertEquals(c2, customerList.getCustomerListWithGivenLevel("Silver").get(0));


        customerList.removeCustomerFromList(c2);
        assertEquals(0, customerList.getCustomerListWithGivenLevel("Silver").size());

    }

    @Test
    public void testRemoveCustomerFromListWhenGold() {
        Customer c = new GoldCustomer("Suzin", 19283764);
        Customer c2 = new GoldCustomer("Mary", 2947483);
        customerList.addCustomerToProperList(c);
        customerList.addCustomerToProperList(c2);
        assertEquals(2, customerList.getCustomerListWithGivenLevel("Gold").size());

        customerList.removeCustomerFromList(c2);
        assertEquals(1, customerList.getCustomerListWithGivenLevel("Gold").size());
        assertEquals(c, customerList.getCustomerListWithGivenLevel("Gold").get(0));


        customerList.removeCustomerFromList(c);
        assertEquals(0, customerList.getCustomerListWithGivenLevel("Gold").size());

    }

    @Test
    public void testRemoveCustomerFromListWhenVip() {
        Customer c = new VipCustomer("Suzin", 19283764);
        Customer c2 = new VipCustomer("Mary", 2947483);
        customerList.addCustomerToProperList(c);
        customerList.addCustomerToProperList(c2);
        assertEquals(2, customerList.getCustomerListWithGivenLevel("Vip").size());

        customerList.removeCustomerFromList(c2);
        assertEquals(1, customerList.getCustomerListWithGivenLevel("Vip").size());
        assertEquals(c, customerList.getCustomerListWithGivenLevel("Vip").get(0));


        customerList.removeCustomerFromList(c);
        assertEquals(0, customerList.getCustomerListWithGivenLevel("Vip").size());
    }

    @Test
    public void testRemoveCustomerFromListInVarious() {
        Customer c1 = new VipCustomer("Suzin", 19283764);
        Customer c2 = new GoldCustomer("Daniel", 23498723);
        Customer c3 = new SilverCustomer("Mike", 293847572);
        Customer c4 = new VipCustomer("Chunga", 59375937);
        Customer c5 = new SilverCustomer("Mary", 294745638);
        customerList.addCustomerToProperList(c1);
        customerList.addCustomerToProperList(c2);
        customerList.addCustomerToProperList(c3);
        customerList.addCustomerToProperList(c4);
        customerList.addCustomerToProperList(c5);
        assertEquals(2, customerList.getCustomerListWithGivenLevel("Silver").size());
        assertEquals(1, customerList.getCustomerListWithGivenLevel("Gold").size());
        assertEquals(2, customerList.getCustomerListWithGivenLevel("VIP").size());

        customerList.removeCustomerFromList(c1);
        assertEquals(2, customerList.getCustomerListWithGivenLevel("Silver").size());
        assertEquals(1, customerList.getCustomerListWithGivenLevel("Gold").size());
        assertEquals(1, customerList.getCustomerListWithGivenLevel("VIP").size());

        customerList.removeCustomerFromList(c3);
        assertEquals(1, customerList.getCustomerListWithGivenLevel("Silver").size());
        assertEquals(1, customerList.getCustomerListWithGivenLevel("Gold").size());
        assertEquals(1, customerList.getCustomerListWithGivenLevel("VIP").size());

        customerList.removeCustomerFromList(c5);
        assertEquals(0, customerList.getCustomerListWithGivenLevel("Silver").size());
        assertEquals(1, customerList.getCustomerListWithGivenLevel("Gold").size());
        assertEquals(1, customerList.getCustomerListWithGivenLevel("VIP").size());
    }

    @Test
    public void testGetCustomerList() {
        Customer c1 = new VipCustomer("Suzin", 19283764);
        Customer c2 = new GoldCustomer("Daniel", 23498723);
        Customer c3 = new SilverCustomer("Mike", 293847572);
        Customer c4 = new VipCustomer("Chunga", 59375937);
        Customer c5 = new SilverCustomer("Mary", 294745638);
        customerList.addCustomerToProperList(c1);
        customerList.addCustomerToProperList(c2);
        customerList.addCustomerToProperList(c3);
        customerList.addCustomerToProperList(c4);
        customerList.addCustomerToProperList(c5);

        assertEquals(customerList.getSilverCustomerList(),
                customerList.getCustomerListWithGivenLevel("Silver"));
        assertEquals(customerList.getGoldCustomerList(),
                customerList.getCustomerListWithGivenLevel("Gold"));
        assertEquals(customerList.getVipCustomerList(),
                customerList.getCustomerListWithGivenLevel("Vip"));

    }

    @Test
    public void testGetCustomerWithGivenInfo() {
        Customer c1 = new VipCustomer("Suzin", 19283764);
        Customer c2 = new GoldCustomer("Daniel", 23498723);
        Customer c3 = new SilverCustomer("Mike", 293847572);
        Customer c4 = new VipCustomer("Chunga", 59375937);
        Customer c5 = new SilverCustomer("Mary", 294745638);
        customerList.addCustomerToProperList(c1);
        customerList.addCustomerToProperList(c2);
        customerList.addCustomerToProperList(c3);
        customerList.addCustomerToProperList(c4);
        customerList.addCustomerToProperList(c5);

        // When in Silver level
        assertEquals(c3, customerList.getCustomerWithGivenInfo
                (293847572));

        // When in Gold level
        assertEquals(c2, customerList.getCustomerWithGivenInfo
                (23498723));

        // When in VIP level
        assertEquals(c4, customerList.getCustomerWithGivenInfo(59375937));

        // when null
        assertEquals(null, customerList.getCustomerWithGivenInfo(1234567689));
    }


    @Test
    public void testGetAllTelephoneNumberInListWhenNotNull() {
        Customer c1 = new VipCustomer("Suzin", 19283764);
        Customer c2 = new GoldCustomer("Suzin", 23498723);
        Customer c3 = new SilverCustomer("Suzin", 293847572);
        Customer c4 = new VipCustomer("Chunga", 59375937);
        Customer c5 = new SilverCustomer("Mary", 294745638);
        Customer c6 = new SilverCustomer("Mary", 12345567);
        customerList.addCustomerToProperList(c1);
        customerList.addCustomerToProperList(c2);
        customerList.addCustomerToProperList(c3);
        customerList.addCustomerToProperList(c4);
        customerList.addCustomerToProperList(c5);
        customerList.addCustomerToProperList(c6);

        // When only one person with same name
        List<Long> l = customerList.getAllTelephoneNumberInList("Chunga");
        assertEquals(1, l.size());
        assertEquals(59375937,l.get(0));

        // when two people with same name
        List<Long> l2 = customerList.getAllTelephoneNumberInList("Mary");
        assertEquals(2, l2.size());
        assertEquals(294745638,l2.get(0));
        assertEquals(12345567,l2.get(1));

        // when three people with same name
        List<Long> l3 = customerList.getAllTelephoneNumberInList("Suzin");
        assertEquals(3, l3.size());
        assertEquals(293847572,l3.get(0));
        assertEquals(23498723,l3.get(1));
        assertEquals(19283764,l3.get(2));

    }




}
