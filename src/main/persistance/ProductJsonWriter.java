package persistance;

import model.product.ProductList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ProductJsonWriter {
    private static final int TAB = 4;
    private PrintWriter productWriter;
    private String destination;

    // EFFECTS: constructs product writer to write to destination file
    public ProductJsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        productWriter = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of productList to file
    public void write(ProductList productList) {
        JSONObject json = productList.toProductJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        productWriter.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        productWriter.print(json);
    }

}
