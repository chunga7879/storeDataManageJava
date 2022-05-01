package persistance;

import model.customer.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class CustomerJsonReader {
    private String source;

    // EFFECTS: constructs customer reader to read from source file
    public CustomerJsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads customerList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CustomerList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCustomerList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses customerList from JSON object and returns it
    private CustomerList parseCustomerList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        CustomerList customerList = new CustomerList(name);
        addCustomers(customerList, jsonObject);
        return customerList;
    }

    // MODIFIES: customerList
    // EFFECTS: parses customers from JSON object and adds them to customerList
    private void addCustomers(CustomerList customerList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("customers");
        for (Object json : jsonArray) {
            JSONObject nextCustomer = (JSONObject) json;
            addCustomer(customerList, nextCustomer);
        }
    }

    // MODIFIES: customerList
    // EFFECTS: parses customer from JSON object and adds it to customerList
    private void addCustomer(CustomerList customerList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String customerName = jsonObject.getString("name");
        long customerTelNum = jsonObject.getLong("Telephone Number");
        String customerMemLevel = jsonObject.getString("Membership");
        double customerTotalSpent = jsonObject.getDouble("Total Spent");
        double point = jsonObject.getDouble("Point");
        JSONArray jsonArray = jsonObject.getJSONArray("Purchase History");
        List<String> customerPurchaseHistory = jsonArrayToList(jsonArray);;
        if (customerMemLevel.equals("Silver")) {
            Customer customer = new SilverCustomer(customerName, customerTelNum);
            toSetOriginalValue(customer, customerTotalSpent, point, customerPurchaseHistory);
            customerList.addCustomerToProperList(customer);
        } else if (customerMemLevel.equals("Gold")) {
            Customer customer = new GoldCustomer(customerName, customerTelNum);
            toSetOriginalValue(customer, customerTotalSpent, point, customerPurchaseHistory);
            customerList.addCustomerToProperList(customer);
        } else if (customerMemLevel.equals("VIP")) {
            Customer customer = new VipCustomer(customerName, customerTelNum);
            toSetOriginalValue(customer, customerTotalSpent, point, customerPurchaseHistory);
            customerList.addCustomerToProperList(customer);
        }
    }

    // EFFECTS: set customer with given information
    public void toSetOriginalValue(Customer customer, double customerTotalSpent, double point,
                                   List<String> customerPurchaseHistory) {
        customer.setTotalSpent(customerTotalSpent);
        customer.setPoint(point);
        customer.setPurchaseHistory(customerPurchaseHistory);
    }

    // EFFECTS: put things in jsonArray to LinkedList
    public List<String> jsonArrayToList(JSONArray jsonArray) {
        List<String> list = new LinkedList<>();
        if (jsonArray != null) {
            int len = jsonArray.length();
            for (int i = 0; i < len; i++) {
                list.add(jsonArray.get(i).toString());
            }
        }
        return list;
    }



}
