package telran.product.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.product.dao.ProductStorage;
import telran.product.dao.ProductStorageImpl;
import telran.product.model.Product;

import static org.junit.jupiter.api.Assertions.*;

class ProductStorageTest {
    ProductStorage storage;
    Product[] products;

    @BeforeEach
    void setUp() {
        storage = new ProductStorageImpl(5);

        products = new Product[4];
        products[0] = new Product("1001", "Beer", "drink", 30, 200);
        products[1] = new Product("1002", "Cola", "drink", 10, 100);
        products[2] = new Product("1003", "Cucumber", "vegetable", 8, 50);
        products[3] = new Product("1004", "Cucumber", "vegetable", 15, 25);

        for (Product product : products){
            storage.addProduct(product);
        }
    }

    @Test
    void addProduct() {
        assertFalse(storage.addProduct(null));
        assertEquals(4, storage.getStorageSize());

        assertFalse(storage.addProduct(products[2]));
        assertEquals(4, storage.getStorageSize());

        Product productToAdd1 = new Product("1005", "Tomato", "vegetable", 5, 50);
        assertTrue(storage.addProduct(productToAdd1));
        assertEquals(5, storage.getStorageSize());
        assertEquals(productToAdd1, storage.findProductById(productToAdd1.getId()));

        Product productToAdd2 = new Product("1006", "Banana", "vegetable", 5, 50);
        assertFalse(storage.addProduct(productToAdd2));
        assertEquals(5, storage.getStorageSize());
    }

    @Test
    void removeProduct() {
        assertNull(storage.removeProduct("6666666"));
        assertEquals(4, storage.getStorageSize());

        assertEquals(products[2], storage.removeProduct(products[2].getId()));
        assertEquals(3, storage.getStorageSize());

        assertNull(storage.removeProduct(products[2].getId()));
        assertEquals(3, storage.getStorageSize());
    }

    @Test
    void findProductById() {
        assertNull(storage.findProductById("6666666"));
        assertEquals(products[3], storage.findProductById(products[3].getId()));
    }

    @Test
    void findProductsByName() {
        Product[] expectedProducts = {products[2], products[3]};
        Product[] actualProducts = storage.findProductsByName("Cucumber");
        assertArrayEquals(expectedProducts, actualProducts);

        expectedProducts = new Product[0];
        actualProducts = storage.findProductsByName("aslkdfghaph");
        assertArrayEquals(expectedProducts, actualProducts);
    }

    @Test
    void findProductsByCategory() {
        Product[] expectedProducts = {products[0], products[1]};
        Product[] actualProducts = storage.findProductsByCategory("drink");
        assertArrayEquals(expectedProducts, actualProducts);

        expectedProducts = new Product[0];
        actualProducts = storage.findProductsByCategory("aslkdfghaph");
        assertArrayEquals(expectedProducts, actualProducts);
    }

    @Test
    void findProductsByPrice() {
        Product[] expectedProducts = {products[0], products[3]};
        Product[] actualProducts = storage.findProductsByPrice(15);
        assertArrayEquals(expectedProducts, actualProducts);

        expectedProducts = new Product[0];
        actualProducts = storage.findProductsByPrice(Integer.MAX_VALUE);
        assertArrayEquals(expectedProducts, actualProducts);
    }

    @Test
    void findProductsByQuantity() {
        Product[] expectedProducts = {products[1], products[2]};
        Product[] actualProducts = storage.findProductsByQuantity(50, 150);
        assertArrayEquals(expectedProducts, actualProducts);

        expectedProducts = new Product[0];
        actualProducts = storage.findProductsByQuantity(Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertArrayEquals(expectedProducts, actualProducts);
    }

    @Test
    void getStorageSize() {
        assertEquals(4, storage.getStorageSize());

        storage.addProduct(null);
        assertEquals(4, storage.getStorageSize());

        storage.addProduct(products[2]);
        assertEquals(4, storage.getStorageSize());

        Product productToAdd1 = new Product("1005", "Tomato", "vegetable", 5, 50);
        storage.addProduct(productToAdd1);
        assertEquals(5, storage.getStorageSize());

        Product productToAdd2 = new Product("1006", "Banana", "vegetable", 5, 50);
        storage.addProduct(productToAdd2);
        assertEquals(5, storage.getStorageSize());

        storage.removeProduct(products[1].getId());
        assertEquals(4, storage.getStorageSize());

        storage.removeProduct(products[1].getId());
        assertEquals(4, storage.getStorageSize());
    }
}
