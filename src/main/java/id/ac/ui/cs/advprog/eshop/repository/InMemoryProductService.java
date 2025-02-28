package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Primary
public class InMemoryProductService implements ProductService {
    private List<Product> productData = new ArrayList<>();

    @Override
    public Product create(Product product) {
        product.setProductId(UUID.randomUUID().toString());
        productData.add(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productData);
    }

    @Override
    public Product findById(String productId) {
        return productData.stream()
                .filter(p -> p.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Product update(String productId, Product updatedProduct) { // updated method signature
        if (updatedProduct == null || updatedProduct.getProductId() == null) {
            return null;
        }
        for (Product product : productData) {
            if (productId.equals(product.getProductId())) { // matching by productId
                product.setProductName(updatedProduct.getProductName());
                product.setProductQuantity(updatedProduct.getProductQuantity());
                return product;
            }
        }
        return null;
    }

    @Override
    public void delete(String productId) {
        productData.removeIf(product -> product.getProductId().equals(productId));
    }
}
