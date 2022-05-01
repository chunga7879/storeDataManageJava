package persistance;

import org.json.JSONObject;

public interface WritableProduct {
    // EFFECTS: returns this as JSON object
    JSONObject toProductJson();
}
