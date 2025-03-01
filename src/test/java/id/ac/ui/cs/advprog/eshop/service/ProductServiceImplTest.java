package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        product1 = new Product("1", "Laptop", 10);
        product2 = new Product("2", "Mouse", 20);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.create(product1)).thenReturn(product1);

        Product createdProduct = productService.create(product1);

        assertNotNull(createdProduct);
        assertEquals("Laptop", createdProduct.getProductName());
        assertEquals(10, createdProduct.getProductQuantity());
        verify(productRepository, times(1)).create(product1);
    }

    @Test
    void testFindAll() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.findAll();

        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdExists() {
        when(productRepository.findById("1")).thenReturn(product1);

        Product foundProduct = productService.findById("1");

        assertNotNull(foundProduct);
        assertEquals("Laptop", foundProduct.getProductName());
        verify(productRepository, times(1)).findById("1");
    }

    @Test
    void testFindByIdNotExists() {
        when(productRepository.findById("99")).thenReturn(null);

        Product foundProduct = productService.findById("99");

        assertNull(foundProduct);
        verify(productRepository, times(1)).findById("99");
    }

    @Test
    void testUpdateProductExists() {
        when(productRepository.update("1", product1)).thenReturn(product1);

        Product updatedProduct = productService.update("1", product1);

        assertNotNull(updatedProduct);
        assertEquals("Laptop", updatedProduct.getProductName());
        verify(productRepository, times(1)).update("1", product1);
    }

    @Test
    void testUpdateProductNotExists() {
        when(productRepository.update("99", product1)).thenReturn(null);

        Product updatedProduct = productService.update("99", product1);

        assertNull(updatedProduct);
        verify(productRepository, times(1)).update("99", product1);
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).delete("1");

        productService.delete("1");

        verify(productRepository, times(1)).delete("1");
    }
}
