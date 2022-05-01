package persistance;

import model.customer.CustomerList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CustomerJsonWriter {
    private static final int TAB = 4;
    private PrintWriter customerWriter;
    private String destination;

    // EFFECTS: constructs customer writer to write to destination file
    public CustomerJsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        customerWriter = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of customerList to file
    public void write(CustomerList customerList) {
        JSONObject json = customerList.toCustomerJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        customerWriter.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        customerWriter.print(json);
    }

}
