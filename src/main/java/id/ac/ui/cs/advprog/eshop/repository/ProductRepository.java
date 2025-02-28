package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductRepository {
    private final ProductService productService;

    @Autowired
    public ProductRepository(ProductService productService) {
        this.productService = productService;
    }

    public Product create(Product product) {
        return productService.create(product);
    }

    public List<Product> findAll() {
        return productService.findAll();
    }

    public Product findById(String productId) {
        return productService.findById(productId);
    }

    public Product update(Product updatedProduct) {
        String productId = updatedProduct.getProductId();
        return productService.update(productId, updatedProduct);
    }

    public void delete(String productId) {
        productService.delete(productId);
    }
}
