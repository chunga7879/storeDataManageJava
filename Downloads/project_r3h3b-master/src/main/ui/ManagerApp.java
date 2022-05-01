package ui;

import model.customer.*;
import model.product.Product;
import model.product.ProductList;
import persistance.CustomerJsonReader;
import persistance.CustomerJsonWriter;
import persistance.ProductJsonReader;
import persistance.ProductJsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ManagerApp {
    static final int MIN_SPENT_FOR_GOLD = 5000;
    static final int MIN_SPENT_FOR_VIP = 10000;

    private CustomerList customerList =  new CustomerList("Customer List");
    private ProductList productList = new ProductList("Product List");
    private final Scanner input = new Scanner(System.in);
    private static final String JSON_STORE_CUSTOMER = "./data/customerList.txt";
    private static final String JSON_STORE_PRODUCT = "./data/productList.txt";
    private CustomerJsonWriter customerJsonWriter;
    private CustomerJsonReader customerJsonReader;
    private ProductJsonWriter productJsonWriter;
    private ProductJsonReader productJsonReader;



    public ManagerApp() {
        customerJsonWriter = new CustomerJsonWriter(JSON_STORE_CUSTOMER);
        customerJsonReader = new CustomerJsonReader(JSON_STORE_CUSTOMER);
        productJsonWriter = new ProductJsonWriter(JSON_STORE_PRODUCT);
        productJsonReader = new ProductJsonReader(JSON_STORE_PRODUCT);
        runManagerApp();
    }

    // EFFECTS: process user input
    public void runManagerApp() {

        System.out.println("Choose what information do you want to manage?");

        String selection = input.nextLine();

        if (selection.equals("c")) {
            System.out.println("Do you want to load customer/product list? (y or n)");
            String yn = input.next();
            if (yn.equals("y")) {
                loadCustomerList();
                loadProductList();
            }
            runCustomer();
        } else if (selection.equals("p")) {
            System.out.println("Do you want to load customer/product list? (y or n)");
            String yn = input.next();
            if (yn.equals("y")) {
                loadCustomerList();
                loadProductList();
            }
            runProduct();
        }

        System.out.println("\nGoodbye!");

    }

    // EFFECTS: process user input about customer information
    public void runCustomer() {
        boolean keepGoing = true;
        while (keepGoing) {
            disPlayMenuCustomer();
            String command = input.next();
            if (command.equals("q")) {
                System.out.println("Do you want to save customer/product list to file? (y or n)");
                String yn2 = input.next();
                if (yn2.equals("y")) {
                    saveCustomerList();
                    saveProductList();
                }
                keepGoing = false;
            } else if (command.equals("product")) {
                runProduct();
                keepGoing = false;
            } else {
                processCustomer(command);
            }
        }
    }

    // EFFECTS: process user input about product information
    public void runProduct() {
        boolean keepGoing = true;
        while (keepGoing) {
            disPlayMenuProduct();
            String command = input.next();
            if (command.equals("q")) {
                System.out.println("Do you want to save customer/product list to file? (y or n)");
                String yn2 = input.next();
                if (yn2.equals("y")) {
                    saveProductList();
                    saveCustomerList();
                }
                keepGoing = false;
            } else if (command.equals("customer")) {
                runCustomer();
                keepGoing = false;
            } else {
                processProduct(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command about customer information
    public void processCustomer(String command) {
        switch (command) {
            case "a" : doAddNewCustomer();
            break;
            case "vp" : doViewProfitsInEachLevel();
            break;
            case "v" : disPlayMenuViewCustomerInformation();
                        processViewCustomer();
                        break;
            case "c" : doCustomerPurchase();
            break;
            case "p" : doViewCustomerPurchaseHistory();
            break;
            case "l" : doLevelUpForAllCustomerSatisfiedCondition();
            break;
            case "save" : saveCustomerList();
            break;
            case "load" : loadCustomerList();
            break;
            default : System.out.println("Selection not valid...");
            break;
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command about product information
    public void processProduct(String command) {
        if (command.equals("a")) {
            doAddProductToProductList();
        } else if (command.equals("v")) {
            doViewListOfProduct();
        } else if (command.equals("r")) {
            doViewListOfProductByRanking();
        } else if (command.equals("save")) {
            saveProductList();
        } else if (command.equals("load")) {
            loadProductList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: processes user command to view customer information
    public void processViewCustomer() {
        System.out.println("select one you want to view: ");
        String newCommand = input.next();
        if (newCommand.equals("a")) {
            doViewCustomerInformationAllLevel();
        } else if (newCommand.equals("s")) {
            doViewCustomerInformationInSilverLevel();
        } else if (newCommand.equals("g")) {
            doViewCustomerInformationInGoldLevel();
        } else if (newCommand.equals("v")) {
            doViewCustomerInformationInVipLevel();
        }
    }

    // EFFECTS: displays menu of options to user to manage customer information
    public void disPlayMenuCustomer() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add");
        System.out.println("\tvp -> viewProfitsInEachLevel");
        System.out.println("\tv -> viewCustomerInformation");
        System.out.println("\tc -> conductCustomerPurchase");
        System.out.println("\tp -> ViewPurchaseHistory");
        System.out.println("\tl -> levelUpCustomer");
        System.out.println("\tproduct -> GoToProductManagement");
        System.out.println("\tsave -> save customer list to file");
        System.out.println("\tload -> load customer list from file");
        System.out.println("\tq -> quit");

    }

    // EFFECTS: displays menu of options to user to view customer information
    public void disPlayMenuViewCustomerInformation() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> all");
        System.out.println("\ts -> Silver");
        System.out.println("\tg -> Gold");
        System.out.println("\tv -> VIP");
    }

    // EFFECTS: displays menu of options to user to manage product information
    public void disPlayMenuProduct() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add");
        System.out.println("\tv -> viewListOfProduct");
        System.out.println("\tr -> viewListOfProductByRanking");
        System.out.println("\tcustomer -> GoToCustomerManagement");
        System.out.println("\tsave -> save product list to file");
        System.out.println("\tload -> load product list from file");
        System.out.println("\tq -> quit");

    }

    // MODIFIES: this
    // EFFECTS: add customer to customer list
    public void doAddNewCustomer() {
        System.out.println("Write customer name: ");
        String name = input.next();
        System.out.println("Write customer telephone number: ");
        long telNum = input.nextLong();

        Customer c = new SilverCustomer(name, telNum);
        customerList.addCustomerToProperList(c);

    }

    // EFFECTS: view information of profits in each level
    public void doViewProfitsInEachLevel() {
        System.out.println("Write the level you want to know the profits(Silver, Gold or VIP): ");
        String s = input.next();

        if (s.equals("Silver")) {
            System.out.println("bonusPointRate: 0.05");
        } else if (s.equals("Gold")) {
            System.out.println("Bonus Point Rate = 0.10");
            System.out.println("Price Discount = 0.05");
            System.out.println("Free movie tickets per month = 2");
        } else if (s.equals("VIP")) {
            System.out.println("Bonus Point Rate = 0.15");
            System.out.println("Price Discount = 0.10");
            System.out.println("Free movie tickets per month = 4");
            System.out.println("Using VIP Lounge");
            System.out.println("Ticket For VIP Party");
        }
    }

    // EFFECTS: view information of all customer
    public void doViewCustomerInformationAllLevel() {
        System.out.println("Silver level Membership Customer List: ");
        for (Customer c : customerList.getSilverCustomerList()) {
            System.out.println(c.toString());
        }
        System.out.println("-------------------------------------------------------------------------");
        System.out.println();

        System.out.println("Gold level Membership Customer List: ");
        for (Customer c : customerList.getGoldCustomerList()) {
            System.out.println(c.toString());
        }
        System.out.println("-------------------------------------------------------------------------");
        System.out.println();
        System.out.println("VIP level Membership Customer List: ");
        for (Customer c : customerList.getVipCustomerList()) {
            System.out.println(c.toString());
        }
        System.out.println("-------------------------------------------------------------------------");
        System.out.println();
    }

    // EFFECTS: view information of silver customer
    public void doViewCustomerInformationInSilverLevel() {
        System.out.println("Silver level Membership Customer List: ");
        for (Customer c : customerList.getSilverCustomerList()) {
            System.out.println(c.toString());
        }
        System.out.println("-------------------------------------------------------------------------");
        System.out.println();
    }

    // EFFECTS: view information of gold customer
    public void doViewCustomerInformationInGoldLevel() {
        System.out.println("Gold level Membership Customer List: ");
        for (Customer c : customerList.getGoldCustomerList()) {
            System.out.println(c.toString());
        }
        System.out.println("-------------------------------------------------------------------------");
        System.out.println();
    }

    // EFFECTS: view information of vip customer
    public void doViewCustomerInformationInVipLevel() {
        System.out.println("VIP level Membership Customer List: ");
        for (Customer c : customerList.getVipCustomerList()) {
            System.out.println(c.toString());
        }
        System.out.println("-------------------------------------------------------------------------");
        System.out.println();
    }

    // REQUIRES: customer need to be silver level
    // MODIFIES: this
    // EFFECTS: level up silver customer to gold customer if total spent is more than  MIN_SPENT_FOR_GOLD
    // Remove the customer from silver customer list, make new gold customer which have same information and add gold
    // customer to gold customer list
    public void doLevelUpCustomerLevelSilverToGold(Customer customer) {
        if (customer.getCustomerMemLevel().equals("Silver") && customer.getTotalSpent() >= MIN_SPENT_FOR_GOLD
                && customer.getTotalSpent() < MIN_SPENT_FOR_VIP) {
            System.out.println(customer.getCustomerName() + " : Change Silver level customer to Gold level customer");
            Customer goldCustomer = new GoldCustomer(customer.getCustomerName(), customer.getCustomerTelNum());
            goldCustomer.setPoint(customer.getPoint());
            goldCustomer.setPurchaseHistory(customer.getPurchaseHistory());
            goldCustomer.setTotalSpent(customer.getTotalSpent());
            customerList.addCustomerToProperList(goldCustomer);
            customerList.removeCustomerFromList(customer);
        }
    }

    // REQUIRES: customer need to be gold level
    // MODIFIES: this
    // EFFECTS: level up gold customer to VIP customer if total spent is more than  MIN_SPENT_FOR_VIP
    // Remove the customer from gold customer list, make new vip customer which have same information and add vip
    // customer to vip customer list
    public void doLevelUpCustomerLevelGoldToVip(Customer customer) {
        if (customer.getCustomerMemLevel().equals("Gold") && customer.getTotalSpent() >= MIN_SPENT_FOR_VIP) {
            System.out.println(customer.getCustomerName() + " : Change Gold level customer to VIP level customer");
            customerList.removeCustomerFromList(customer);
            Customer vipCustomer = new VipCustomer(customer.getCustomerName(), customer.getCustomerTelNum());
            vipCustomer.setPoint(customer.getPoint());
            vipCustomer.setPurchaseHistory(customer.getPurchaseHistory());
            vipCustomer.setTotalSpent(customer.getTotalSpent());
            customerList.addCustomerToProperList(vipCustomer);
            customerList.removeCustomerFromList(customer);
        }
    }

    // REQUIRES: customer need to be gold level
    // MODIFIES: this
    // EFFECTS: level up silver customer to VIP customer if total spent is more than  MIN_SPENT_FOR_VIP
    // Remove the customer from silver customer list, make new vip customer which have same information and add vip
    // customer to vip customer list
    public void doLevelUpCustomerLevelSilverToVip(Customer customer) {
        if (customer.getCustomerMemLevel().equals("Silver") && customer.getTotalSpent() >= MIN_SPENT_FOR_VIP) {
            System.out.println(customer.getCustomerName() + " : Change Silver level customer to VIP level customer");
            customerList.removeCustomerFromList(customer);
            Customer vipCustomer = new VipCustomer(customer.getCustomerName(), customer.getCustomerTelNum());
            vipCustomer.setPoint(customer.getPoint());
            vipCustomer.setPurchaseHistory(customer.getPurchaseHistory());
            vipCustomer.setTotalSpent(customer.getTotalSpent());
            customerList.addCustomerToProperList(vipCustomer);
            customerList.removeCustomerFromList(customer);
        }
    }

    // MODIFIES: this
    // EFFECTS: call doLevelUpCustomerLevelSilverToGold() for all Silver level customer and
    // doLevelUpCustomerLevelGoldToVip() for all Gold level customer
    public void doLevelUpForAllCustomerSatisfiedCondition() {
        for (Customer c : customerList.getSilverCustomerList()) {
            doLevelUpCustomerLevelSilverToVip(c);
            doLevelUpCustomerLevelSilverToGold(c);
        }
        for (Customer c : customerList.getGoldCustomerList()) {
            doLevelUpCustomerLevelGoldToVip(c);
        }
    }

    // MODIFIES: this
    // EFFECTS: add product to product list
    public void doAddProductToProductList() {
        System.out.println("Write product name: ");
        String productName = input.next();
        System.out.println("Write product price: ");
        double productPrice = input.nextDouble();
        System.out.println("Write the number of product: ");
        int numberOfProduct = input.nextInt();
        Product product = new Product(productName, productPrice, numberOfProduct);
        System.out.println("Please set the manager of product: ");
        String manager = input.next();
        product.setManager(manager);
        System.out.println("Please set the floor of product: ");
        int floor = input.nextInt();
        product.setFloor(floor);
        productList.addProductToList(product);

    }

    // EFFECTS: view product information in the list
    public void doViewListOfProduct() {
        for (Product p : productList.getProductList()) {
            System.out.println(p.toString());
        }
    }

    // EFFECTS: view product information by ranking of amount of product sold
    public void doViewListOfProductByRanking() {
        int[] rank = new int[productList.getProductList().size()];
        for (int i = 0; i < productList.getProductList().size(); i++) {
            rank[i] = 1;
        }

        for (int i = 0; i < rank.length; i++) {
            for (int j = 0; j < productList.getProductList().size(); j++) {
                if (productList.getProductList().get(i).getNumberOfSold()
                        < productList.getProductList().get(j).getNumberOfSold()) {
                    rank[i]++;
                }
            }
        }

        int[] index = new int[rank.length];
        for (int i = 0; i < rank.length; i++) {
            index[rank[i] - 1] = i;
        }

        for (int i = 0; i < rank.length; i++) {
            int t = index[i];
            System.out.println(rank[t] + " : " + productList.getProductList().get(t).toString());
        }

    }

    // EFFECTS: Conduct customer purchase
    public void doCustomerPurchase() {
        System.out.println("Who buy a product - enter telephone number?");
        long telNum = input.nextLong();
        System.out.println("What Product the customer buy? ");
        String purchasedProduct = input.next();
        System.out.println("How many customer purchase the product?");
        int numberOfPurchase = input.nextInt();
        System.out.println("When did customer purchase the product - year,month,day,hour,minute?"
                + "(please enter after writing one)");
        int year = input.nextInt();
        int month = input.nextInt();
        int day = input.nextInt();
        int hour = input.nextInt();
        int minute = input.nextInt();
        for (Product p : productList.getProductList()) {
            if (p.getProductName().equals(purchasedProduct)) {
                customerList.getCustomerWithGivenInfo(telNum).buyProduct(p, numberOfPurchase, year, month,
                        day, hour, minute);
            }
        }
    }

    // EFFECTS: view purchase history of that customer
    public void doViewCustomerPurchaseHistory() {
        System.out.println("Who buy a product - enter name?");
        String name = input.next();
        for (long l : customerList.getAllTelephoneNumberInList(name)) {
            System.out.println(l);
        }
        // this is for the case which some customer have same name
        System.out.println("Who buy a product among the telephone numbers- enter telephone number?");
        long telNum = input.nextLong();
        System.out.println("Purchase history of " + name);
        for (String p : customerList.getCustomerWithGivenInfo(telNum).getPurchaseHistory()) {
            System.out.println(p);
        }
        System.out.println("Total Spent: " + customerList.getCustomerWithGivenInfo(telNum).getTotalSpent()
                + "\nTotal Point: " + customerList.getCustomerWithGivenInfo(telNum).getPoint());
    }


    // EFFECTS: saves the workroom to file
    private void saveCustomerList() {
        try {
            customerJsonWriter.open();
            customerJsonWriter.write(customerList);
            customerJsonWriter.close();
            System.out.println("Saved " + customerList.getCustomerListName() + " to " + JSON_STORE_CUSTOMER);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_CUSTOMER);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public void loadCustomerList() {
        try {
            customerList = customerJsonReader.read();
            System.out.println("Loaded " + customerList.getCustomerListName() + " from " + JSON_STORE_CUSTOMER);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_CUSTOMER);
        }
    }

    // EFFECTS: saves the workroom to file
    private void saveProductList() {
        try {
            productJsonWriter.open();
            productJsonWriter.write(productList);
            productJsonWriter.close();
            System.out.println("Saved " + productList.getProductListName() + " to " + JSON_STORE_PRODUCT);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_PRODUCT);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public void loadProductList() {
        try {
            productList = productJsonReader.read();
            System.out.println("Loaded " + productList.getProductListName() + " from " + JSON_STORE_PRODUCT);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_PRODUCT);
        }
    }


}
