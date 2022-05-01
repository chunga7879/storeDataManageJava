package persistance;

import org.json.JSONObject;

public interface WritableCustomer {
    // EFFECTS: returns this as JSON object
    JSONObject toCustomerJson();
}