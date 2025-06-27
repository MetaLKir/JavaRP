package telran.product.dao;

import telran.product.model.Product;
import java.util.function.Predicate;

public interface ProductStorage {
    boolean addProduct(Product product);
    Product removeProduct(String id);
    Product findProductById(String id);
    Product[] findProductsByName(String name);
    Product[] findProductsByCategory(String category);
    Product[] findProductsByPrice(double min);
    Product[] findProductsByQuantity(int min, int max);
    int getStorageSize();
}
