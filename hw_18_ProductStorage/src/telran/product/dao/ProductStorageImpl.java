package telran.product.dao;

import telran.product.model.Product;

import java.util.ArrayList;
import java.util.function.Predicate;

public class ProductStorageImpl implements ProductStorage {
    private final Product[] products;
    private int size;

    public ProductStorageImpl(int capacity) {
        products = new Product[capacity];
    }

    @Override
    public boolean addProduct(Product product) {
        if(size == products.length || product == null || findProductById(product.getId()) != null)
            return false;

        products[size++] = product;
        return true;
    }

    @Override
    public Product removeProduct(String id) {
        for (int i = 0; i < size; i++) {
            if (!products[i].getId().equals(id)) continue;

            Product toRemove = products[i];
            products[i] = products[--size];
            products[size] = null;
            return toRemove;
        }
        return null;
    }

    @Override
    public Product findProductById(String id) {
        for (int i = 0; i < size; i++) {
            if (products[i].getId().equals(id))
                return products[i];
        }
        return null;
    }

    @Override
    public Product[] findProductsByName(String name) {
        return findProductByPredicate(p -> p.getName().equals(name));
    }

    @Override
    public Product[] findProductsByCategory(String category) {
        return findProductByPredicate(p -> p.getCategory().equals(category));
    }

    @Override
    public Product[] findProductsByPrice(double min) {
        return findProductByPredicate(p -> p.getPrice() >= min);
    }

    @Override
    public Product[] findProductsByQuantity(int min, int max) {
        Predicate<Product> predicate = p -> p.getQuantity() >= min && p.getQuantity() <= max;
        return findProductByPredicate(predicate);
    }

    @Override
    public int getStorageSize() {
        return size;
    }

    private Product[] findProductByPredicate(Predicate<Product> predicate){
        ArrayList<Product> matchingProducts = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if(predicate.test(products[i]))
                matchingProducts.add(products[i]);
        }
        return matchingProducts.toArray(new Product[0]);
    }
}
