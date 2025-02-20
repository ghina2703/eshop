package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testCreateProduct() {
        // Arrange
        Product product = new Product();
        product.setProductName("Product A");
        product.setProductQuantity(10);

        // Act
        when(productRepository.create(any(Product.class))).thenReturn(product);

        Product createdProduct = productService.create(product);

        // Assert
        assertNotNull(createdProduct);
        assertEquals("Product A", createdProduct.getProductName());
        assertEquals(10, createdProduct.getProductQuantity());

        // Verify interaction with the repository
        verify(productRepository, times(1)).create(any(Product.class));
    }

    @Test
    void testFindAll() {
        // Arrange
        Product product1 = new Product();
        product1.setProductName("Product A");
        product1.setProductQuantity(10);

        Product product2 = new Product();
        product2.setProductName("Product B");
        product2.setProductQuantity(20);

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        // Act
        List<Product> products = productService.findAll();

        // Assert
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Product A", products.get(0).getProductName());
        assertEquals("Product B", products.get(1).getProductName());

        // Verify interaction with the repository
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        // Arrange
        Product product = new Product();
        product.setProductName("Product A");
        product.setProductQuantity(10);
        product.setProductId("1");

        when(productRepository.findById("1")).thenReturn(product);

        // Act
        Product foundProduct = productService.findById("1");

        // Assert
        assertNotNull(foundProduct);
        assertEquals("Product A", foundProduct.getProductName());
        assertEquals(10, foundProduct.getProductQuantity());

        // Verify interaction with the repository
        verify(productRepository, times(1)).findById("1");
    }

    @Test
    void testFindByIdNotFound() {
        // Arrange
        when(productRepository.findById("non-existing-id")).thenReturn(null);

        // Act
        Product foundProduct = productService.findById("non-existing-id");

        // Assert
        assertNull(foundProduct);

        // Verify interaction with the repository
        verify(productRepository, times(1)).findById("non-existing-id");
    }

    @Test
    void testUpdateProduct() {
        // Arrange
        Product existingProduct = new Product();
        existingProduct.setProductId("1");
        existingProduct.setProductName("Product A");
        existingProduct.setProductQuantity(10);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("1");
        updatedProduct.setProductName("Product A Updated");
        updatedProduct.setProductQuantity(15);

        when(productRepository.findById("1")).thenReturn(existingProduct);
        when(productRepository.update(any(Product.class))).thenReturn(updatedProduct);

        // Act
        Product result = productService.update(updatedProduct);

        // Assert
        assertNotNull(result);
        assertEquals("Product A Updated", result.getProductName());
        assertEquals(15, result.getProductQuantity());

        // Verify interactions with the repository
        verify(productRepository, times(1)).findById("1");
        verify(productRepository, times(1)).update(any(Product.class));
    }

    @Test
    void testUpdateProductNotFound() {
        // Arrange
        Product updatedProduct = new Product();
        updatedProduct.setProductId("non-existing-id");

        when(productRepository.findById("non-existing-id")).thenReturn(null);

        // Act
        Product result = productService.update(updatedProduct);

        // Assert
        assertNull(result);

        // Verify interaction with the repository
        verify(productRepository, times(1)).findById("non-existing-id");
    }

    @Test
    void testDeleteProduct() {
        // Arrange
        Product product = new Product();
        product.setProductId("1");

        doNothing().when(productRepository).delete("1");

        // Act
        productService.delete("1");

        // Assert
        verify(productRepository, times(1)).delete("1");
    }

    @Test
    void testDeleteProductNotFound() {
        // Arrange
        doNothing().when(productRepository).delete("non-existing-id");

        // Act
        productService.delete("non-existing-id");

        // Assert
        verify(productRepository, times(1)).delete("non-existing-id");
    }
}
