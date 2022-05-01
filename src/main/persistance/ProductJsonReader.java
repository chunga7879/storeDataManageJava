package persistance;

import model.product.Product;
import model.product.ProductList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ProductJsonReader {
    private String source;

    // EFFECTS: constructs product reader to read from source file
    public ProductJsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads productList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ProductList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseProductList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses productList from JSON object and returns it
    private ProductList parseProductList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ProductList productList = new ProductList(name);
        addProducts(productList, jsonObject);
        return productList;
    }

    // MODIFIES: productList
    // EFFECTS: parses products from JSON object and adds them to productList
    private void addProducts(ProductList productList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("products");
        for (Object json : jsonArray) {
            JSONObject nextProduct = (JSONObject) json;
            addProduct(productList, nextProduct);
        }
    }

    // MODIFIES: productList
    // EFFECTS: parses productList from JSON object and adds it to productList
    private void addProduct(ProductList productList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String productName = jsonObject.getString("name");
        double productPrice = jsonObject.getDouble("Price");
        int numberOfSold = jsonObject.getInt("Number Of Sold");
        int numberOfProduct = jsonObject.getInt("Number Of Product");
        String manager = jsonObject.getString("Manager");
        int floor = jsonObject.getInt("Floor");

        Product product = new Product(productName, productPrice, numberOfProduct);
        product.setFloor(floor);
        product.setManager(manager);
        product.setNumberOfSold(numberOfSold);
        productList.addProductToList(product);

    }
}
