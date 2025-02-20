package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        product.setProductId(UUID.randomUUID().toString());
        productData.add(product);
        return product;
    }

    public List<Product> findAll() {
        return new ArrayList<>(productData);
    }

    public Product findById(String productId) {
        return productData.stream()
                .filter(p -> p.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    public Product update(Product updatedProduct) {
        if (updatedProduct == null || updatedProduct.getProductId() == null) {
            return null;
        }

        for (Product product : productData) {
            if (updatedProduct.getProductId().equals(product.getProductId())) {
                boolean isUpdated = false;

                if (updatedProduct.getProductName() != null &&
                        (product.getProductName() == null || !updatedProduct.getProductName().equals(product.getProductName()))) {
                    product.setProductName(updatedProduct.getProductName());
                    isUpdated = true;
                }

                if (updatedProduct.getProductQuantity() != product.getProductQuantity()) {
                    product.setProductQuantity(updatedProduct.getProductQuantity());
                    isUpdated = true;
                }

                return isUpdated ? product : product;
            }
        }
        return null;
    }

    public void delete(String productId) {
        productData.removeIf(product -> product.getProductId().equals(productId));
    }

}
