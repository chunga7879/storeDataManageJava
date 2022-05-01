package producttest;

import model.product.Product;
import model.product.ProductList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProductListTest {
    ProductList productList;

    @BeforeEach
    public void runBefore() {
        productList = new ProductList("Product List");
    }

    @Test
    public void addProductToList() {
        Product product1 = new Product("Desk", 150.0, 10);
        productList.addProductToList(product1);
        assertEquals(1, productList.getProductList().size());

        Product product2 = new Product("Chocolate", 2.0, 300);
        productList.addProductToList(product2);
        assertEquals(2, productList.getProductList().size());

    }

    @Test
    public void testRemoveProductFromListIfProductInProductList() {
        Product product1 = new Product("Desk", 150.0, 10);
        Product product2 = new Product("Chocolate", 2.0, 300);
        Product product3 = new Product("Shampoo", 5.0, 150);
        productList.addProductToList(product1);
        productList.addProductToList(product2);
        productList.addProductToList(product3);
        assertEquals(3, productList.getProductList().size());

        productList.removeProductFromList(product2);
        assertEquals(2, productList.getProductList().size());
        assertEquals(product3, productList.getProductList().get(1));

        productList.removeProductFromList(product1);
        assertEquals(1, productList.getProductList().size());
        assertEquals(product3, productList.getProductList().get(0));

        productList.removeProductFromList(product3);
        assertEquals(0, productList.getProductList().size());

    }

    @Test
    public void testRemoveProductFromListIfProductNotInProductList() {
        Product product1 = new Product("Desk", 150.0, 10);
        Product product2 = new Product("Chocolate", 2.0, 300);
        Product product3 = new Product("Shampoo", 5.0, 150);
        Product product4 = new Product("Candy", 1.0, 400);

        productList.addProductToList(product1);
        productList.addProductToList(product3);
        assertEquals(2, productList.getProductList().size());

        assertFalse(productList.removeProductFromList(product2));
        assertEquals(2, productList.getProductList().size());

        assertFalse(productList.removeProductFromList(product4));
        assertEquals(2, productList.getProductList().size());

    }


}
