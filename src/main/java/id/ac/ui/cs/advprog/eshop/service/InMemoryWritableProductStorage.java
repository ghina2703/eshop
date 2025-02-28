package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class InMemoryWritableProductStorage implements WritableProductStorage {

    private List<Product> productList = new ArrayList<>();

    @Override
    public Product create(Product product) {
        productList.add(product);
        return product;
    }

    @Override
    public Product update(String productId, Product updatedProduct) {
        for (int i = 0; i < productList.size(); i++) {
            Product existingProduct = productList.get(i);
            if (existingProduct.getProductId().equals(productId)) {
                existingProduct.setProductName(updatedProduct.getProductName());
                existingProduct.setProductQuantity(updatedProduct.getProductQuantity());
                return existingProduct;
            }
        }
        return null;
    }

    @Override
    public void delete(String productId) {
        productList.removeIf(product -> product.getProductId().equals(productId));
    }
}
