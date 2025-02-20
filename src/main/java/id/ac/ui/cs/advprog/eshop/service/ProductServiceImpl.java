package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        productRepository.create(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(String productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Product update(Product product) {
        Product existingProduct = productRepository.findById(product.getProductId());
        if (existingProduct != null) {
            existingProduct.setProductName(product.getProductName());
            existingProduct.setProductQuantity(product.getProductQuantity());
            return productRepository.update(existingProduct);
        }
        return null; // Jika tidak ditemukan, return null
    }

    @Override
    public void delete(String productId) {
        productRepository.delete(productId);
    }
}
