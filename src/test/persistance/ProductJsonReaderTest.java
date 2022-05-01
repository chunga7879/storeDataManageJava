package persistance;

import model.product.Product;
import model.product.ProductList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ProductJsonReaderTest extends ProductJsonTest {

    @Test
    void testProductReaderNonExistentFile() {
        ProductJsonReader reader = new ProductJsonReader("./data/noSuchFileCustomer.json");
        try {
            ProductList productList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyProductList() {
        ProductJsonReader reader = new ProductJsonReader("./data/testProductWriterEmptyProductList.json");
        try {
            ProductList productList = reader.read();
            assertEquals("Product List", productList.getProductListName());
            assertEquals(0, productList.numProduct());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralProductList() {
        ProductJsonReader reader = new ProductJsonReader("./data/testWriterGeneralProductList.json");
        try {
            ProductList productList = reader.read();
            assertEquals("Product List", productList.getProductListName());
            List<Product> products = productList.getProductList();
            assertEquals(3, products.size());
            checkProduct("Desk", 150.0, 10, products.get(0));
            checkProduct("Chocolate", 2.0, 300, products.get(1));
            checkProduct("Shampoo", 5.0, 150,products.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
