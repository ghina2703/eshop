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

    @Mock
    private ReadOnlyProductStorage readOnlyProductStorage;

    @Mock
    private WritableProductStorage writableProductStorage;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("1");
        product.setProductName("Test Product");
        product.setProductQuantity(100);
    }

    @Test
    void testCreate() {
        when(writableProductStorage.create(product)).thenReturn(product);
        when(productRepository.create(product)).thenReturn(product);

        Product result = productService.create(product);
        assertNotNull(result);
        assertEquals(product.getProductId(), result.getProductId());

        verify(writableProductStorage, times(1)).create(product);
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAll() {
        List<Product> products = Arrays.asList(product);
        when(readOnlyProductStorage.findAll()).thenReturn(products);

        List<Product> result = productService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());

        verify(readOnlyProductStorage, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(readOnlyProductStorage.findById(product.getProductId())).thenReturn(product);

        Product result = productService.findById(product.getProductId());
        assertNotNull(result);
        assertEquals(product.getProductId(), result.getProductId());

        verify(readOnlyProductStorage, times(1)).findById(product.getProductId());
    }

    @Test
    void testUpdate() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("1");
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setProductQuantity(200);

        when(productRepository.findById("1")).thenReturn(product);
        when(writableProductStorage.update("1", updatedProduct)).thenReturn(updatedProduct);
        when(productRepository.update(updatedProduct)).thenReturn(updatedProduct);

        Product result = productService.update("1", updatedProduct);
        assertNotNull(result);
        assertEquals(updatedProduct.getProductName(), result.getProductName());
        assertEquals(updatedProduct.getProductQuantity(), result.getProductQuantity());

        verify(productRepository, times(1)).findById("1");
        verify(writableProductStorage, times(1)).update("1", updatedProduct);
        verify(productRepository, times(1)).update(updatedProduct);
    }

    @Test
    void testUpdateProductNotFound() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("999");
        updatedProduct.setProductName("Nonexistent Product");
        updatedProduct.setProductQuantity(0);

        when(productRepository.findById("999")).thenReturn(null);

        Product result = productService.update("999", updatedProduct);
        assertNull(result);

        verify(productRepository, times(1)).findById("999");
    }

    @Test
    void testDelete() {
        doNothing().when(writableProductStorage).delete(product.getProductId());
        doNothing().when(productRepository).delete(product.getProductId());

        productService.delete(product.getProductId());

        verify(writableProductStorage, times(1)).delete(product.getProductId());
        verify(productRepository, times(1)).delete(product.getProductId());
    }
}
