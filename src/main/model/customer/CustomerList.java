package model.customer;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.WritableCustomer;

import java.util.LinkedList;
import java.util.List;

public class CustomerList implements WritableCustomer {
    String name;
    List<Customer> silverCustomerList;
    List<Customer> goldCustomerList;
    List<Customer> vipCustomerList;
    List<Customer> allCustomerList;

    public CustomerList(String name) {
        this.name = name;
        silverCustomerList = new LinkedList<>();
        goldCustomerList = new LinkedList<>();
        vipCustomerList = new LinkedList<>();
        allCustomerList = new LinkedList<>();;
    }

    public List<Customer> getSilverCustomerList() {
        return silverCustomerList;
    }

    public List<Customer> getGoldCustomerList() {
        return goldCustomerList;
    }

    public List<Customer> getVipCustomerList() {
        return vipCustomerList;
    }

    public List<Customer> getAllCustomerList() {
        return allCustomerList;
    }

    public String getCustomerListName() {
        return name;
    }

    // EFFECTS: returns number of customers in this customerList
    public int numCustomer() {
        return allCustomerList.size();

    }

    // MODIFIES: this
    // EFFECTS: add customer to proper level list: Silver -> SilverCustomerList, Gold -> GoldCustomerList
    // Vip -> VipCustomerList
    public void addCustomerToProperList(Customer customer) {
        if (customer.customerMemLevel.equals("Silver")) {
            silverCustomerList.add(customer);
        } else if (customer.customerMemLevel.equals("Gold")) {
            goldCustomerList.add(customer);
        } else {
            vipCustomerList.add(customer);
        }
        allCustomerList.add(customer);
    }

    // MODIFIES: this
    // EFFECTS: remove customer from proper list
    public void removeCustomerFromList(Customer customer) {
        if (customer.customerMemLevel.equals("Silver")) {
            silverCustomerList.remove(customer);
        } else if (customer.customerMemLevel.equals("Gold")) {
            goldCustomerList.remove(customer);
        } else  {
            vipCustomerList.remove(customer);
        }
        allCustomerList.remove(customer);
    }

    // REQUIRES: customerLevel need to be "Silver", "Gold" or "VIP"
    // EFFECTS: return customer list of given customer level
    public List<Customer> getCustomerListWithGivenLevel(String customerLevel) {
        if (customerLevel.equals("Silver")) {
            return silverCustomerList;
        } else if (customerLevel.equals("Gold")) {
            return goldCustomerList;
        } else {
            return vipCustomerList;
        }
    }

    // REQUIRES: at least one customer who have same telNum with given telNum
    // EFFECTS: get customer from customer list with given information
    public Customer getCustomerWithGivenInfo(long telNum) {
        for (Customer customer : allCustomerList) {
            if (customer.getCustomerTelNum() == telNum) {
                return customer;
            }
        }
        return null;
    }

    // REQUIRES: at least one customer which have same name with given name in the list
    // EFFECTS: make list of telephone number of customer which have same name
    public List<Long> getAllTelephoneNumberInList(String name) {
        List<Long> all = new LinkedList<>();
        for (Customer customer : silverCustomerList) {
            if (customer.getCustomerName().equals(name)) {
                all.add(customer.getCustomerTelNum());
            }
        }
        for (Customer customer : goldCustomerList) {
            if (customer.getCustomerName().equals(name)) {
                all.add(customer.getCustomerTelNum());
            }
        }
        for (Customer customer : vipCustomerList) {
            if (customer.getCustomerName().equals(name)) {
                all.add(customer.getCustomerTelNum());
            }
        }
        return all;
    }

    @Override
    public JSONObject toCustomerJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("customers", customersToJson());
        return json;
    }

    // EFFECTS: returns customers in this customerList as a JSON array
    private JSONArray customersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Customer t : silverCustomerList) {
            jsonArray.put(t.toCustomerJson());
        }
        for (Customer t : goldCustomerList) {
            jsonArray.put(t.toCustomerJson());
        }
        for (Customer t : vipCustomerList) {
            jsonArray.put(t.toCustomerJson());
        }

        return jsonArray;
    }



}
