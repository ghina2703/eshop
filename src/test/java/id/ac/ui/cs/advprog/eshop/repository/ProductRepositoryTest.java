package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
    }

    @Test
    void testCreateProduct() {
        Product product = new Product("1", "Laptop", 10);
        Product createdProduct = productRepository.create(product);

        assertNotNull(createdProduct);
        assertEquals("Laptop", createdProduct.getProductName());
        assertEquals(10, createdProduct.getProductQuantity());
    }

    @Test
    void testFindAll() {
        Product product1 = new Product("1", "Laptop", 10);
        Product product2 = new Product("2", "Mouse", 20);
        productRepository.create(product1);
        productRepository.create(product2);

        List<Product> products = productRepository.findAll();
        assertEquals(2, products.size());
    }

    @Test
    void testFindByIdExists() {
        Product product = new Product("1", "Keyboard", 5);
        productRepository.create(product);

        Product foundProduct = productRepository.findById("1");
        assertNotNull(foundProduct);
        assertEquals("Keyboard", foundProduct.getProductName());
    }

    @Test
    void testFindByIdNotExists() {
        Product foundProduct = productRepository.findById("99");
        assertNull(foundProduct);
    }

    @Test
    void testUpdateProductExists() {
        Product product = new Product("1", "Monitor", 15);
        productRepository.create(product);

        Product updatedProduct = new Product("1", "Gaming Monitor", 30);
        Product result = productRepository.update("1", updatedProduct);

        assertNotNull(result);
        assertEquals("Gaming Monitor", result.getProductName());
        assertEquals(30, result.getProductQuantity());
    }

    @Test
    void testUpdateProductNotExists() {
        Product updatedProduct = new Product("99", "Headset", 5);
        Product result = productRepository.update("99", updatedProduct);

        assertNull(result);
    }

    @Test
    void testDeleteProductExists() {
        Product product = new Product("1", "Speaker", 8);
        productRepository.create(product);

        productRepository.delete("1");
        assertNull(productRepository.findById("1"));
    }

    @Test
    void testDeleteProductNotExists() {
        productRepository.delete("99");
        assertTrue(productRepository.findAll().isEmpty());
    }
}
