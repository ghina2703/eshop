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

    private final ReadOnlyProductStorage readOnlyProductStorage;
    private final WritableProductStorage writableProductStorage;

    @Autowired
    public ProductServiceImpl(ReadOnlyProductStorage readOnlyProductStorage, WritableProductStorage writableProductStorage) {
        this.readOnlyProductStorage = readOnlyProductStorage;
        this.writableProductStorage = writableProductStorage;
    }

    @Override
    public Product create(Product product) {
        writableProductStorage.create(product);
        return productRepository.create(product);
    }

    @Override
    public List<Product> findAll() {
        return readOnlyProductStorage.findAll();
    }

    @Override
    public Product findById(String productId) {
        return readOnlyProductStorage.findById(productId);
    }

    @Override
    public Product update(String productId, Product product) {
        Product existingProduct = productRepository.findById(productId);
        if (existingProduct != null) {
            existingProduct.setProductName(product.getProductName());
            existingProduct.setProductQuantity(product.getProductQuantity());
            writableProductStorage.update(productId, existingProduct);
            return productRepository.update(existingProduct);
        }
        return null;
    }

    @Override
    public void delete(String productId) {
        writableProductStorage.delete(productId);
        productRepository.delete(productId);
    }
}
