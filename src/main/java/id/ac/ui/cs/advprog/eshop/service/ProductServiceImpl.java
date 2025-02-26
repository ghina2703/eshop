package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    private void validateProductQuantity(Product product) {
        if (product.getProductQuantity() < 0) {
            throw new IllegalArgumentException("Quantity tidak boleh negative");
        }
    }

    @Override
    public Product create(Product product) {
        validateProductQuantity(product);
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
    public Product update(String productId, Product product) {
        validateProductQuantity(product);
        Product existingProduct = productRepository.findById(product.getProductId());
        if (existingProduct != null) {
            existingProduct.setProductName(product.getProductName());
            existingProduct.setProductQuantity(product.getProductQuantity());
            return productRepository.update(existingProduct);
        }
        return null;
    }

    @Override
    public void delete(String productId) {
        productRepository.delete(productId);
    }
}
