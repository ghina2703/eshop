package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;

public interface WritableProductStorage {
    Product create(Product product);
    Product update(String productId, Product updatedProduct);
    void delete(String productId);
}
