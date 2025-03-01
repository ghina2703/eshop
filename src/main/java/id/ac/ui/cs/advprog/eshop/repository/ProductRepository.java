package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public List<Product> findAll() {
        return new ArrayList<>(productData);
    }

    public Product findById(String productId) {
        return productData.stream()
                .filter(product -> product.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    public Product update(String productId, Product updatedProduct) {
        Optional<Product> existingProduct = productData.stream()
                .filter(product -> product.getProductId().equals(productId))
                .findFirst();
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setProductName(updatedProduct.getProductName());
            product.setProductQuantity(updatedProduct.getProductQuantity());
            return product;
        }
        return null;
    }

    public void delete(String productId) {
        productData.removeIf(product -> product.getProductId().equals(productId));
    }
}
