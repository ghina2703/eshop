package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class InMemoryReadOnlyProductStorage implements ReadOnlyProductStorage {

    private List<Product> productList = new ArrayList<>();

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productList);
    }

    @Override
    public Product findById(String productId) {
        return productList.stream()
                .filter(product -> product.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }
}
