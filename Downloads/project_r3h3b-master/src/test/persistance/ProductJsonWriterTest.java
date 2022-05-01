package persistance;

import model.product.Product;
import model.product.ProductList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ProductJsonWriterTest extends ProductJsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ProductList productList = new ProductList("Product List");
            ProductJsonWriter writer = new ProductJsonWriter("./data/my\0illegal:fileName.customerjson");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyProductList() {
        try {
            ProductList productList = new ProductList("Product List");
            ProductJsonWriter writer =
                    new ProductJsonWriter("./data/testProductWriterEmptyProductList.json");
            writer.open();
            writer.write(productList);
            writer.close();

            ProductJsonReader reader = new ProductJsonReader("./data/testProductWriterEmptyProductList.json");
            productList = reader.read();
            assertEquals("Product List", productList.getProductListName());
            assertEquals(0, productList.numProduct());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralProductList() {
        try {
            ProductList productList = new ProductList("Product List");
            productList.addProductToList(new Product("Desk", 150.0, 10));
            productList.addProductToList(new Product("Chocolate", 2.0, 300));
            productList.addProductToList(new Product("Shampoo", 5.0, 150));
            ProductJsonWriter writer = new ProductJsonWriter("./data/testWriterGeneralProductList.json");
            writer.open();
            writer.write(productList);
            writer.close();

            ProductJsonReader reader = new ProductJsonReader("./data/testWriterGeneralProductList.json");
            productList = reader.read();
            assertEquals("Product List", productList.getProductListName());
            List<Product> productList1 = productList.getProductList();
            assertEquals(3, productList1.size());
            checkProduct("Desk", 150.0, 10, productList1.get(0));
            checkProduct("Chocolate", 2.0, 300, productList1.get(1));
            checkProduct("Shampoo", 5.0, 150,productList1.get(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }
}
