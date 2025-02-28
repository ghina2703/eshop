package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    void setup() {
        product = new Product();
        product.setProductId("eb55e89f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
    }

    @Test
    void testCreate() {
        when(productService.create(product)).thenReturn(product);
        Product createdProduct = productRepository.create(product);
        assertNotNull(createdProduct, "Created product should not be null");
        assertEquals(product.getProductId(), createdProduct.getProductId());
        assertEquals(product.getProductName(), createdProduct.getProductName());
        assertEquals(product.getProductQuantity(), createdProduct.getProductQuantity());
    }

    @Test
    void testFindAll() {
        when(productService.findAll()).thenReturn(List.of(product));
        List<Product> products = productRepository.findAll();
        assertNotNull(products, "Products list should not be null");
        assertEquals(1, products.size(), "There should be exactly one product");
        assertTrue(products.contains(product), "The product should be in the list");
    }

    @Test
    void testFindById() {
        when(productService.findById(product.getProductId())).thenReturn(product);
        when(productService.findById("non-existent-id")).thenReturn(null);
        Product foundProduct = productRepository.findById(product.getProductId());
        Product notFoundProduct = productRepository.findById("non-existent-id");
        assertNotNull(foundProduct, "Product should be found");
        assertEquals(product.getProductId(), foundProduct.getProductId());
        assertNull(notFoundProduct, "Product should not be found for a non-existent ID");
    }

    @Test
    void testUpdate() {
        when(productService.update(product.getProductId(), product)).thenReturn(product);
        Product updatedProduct = productRepository.update(product);
        assertNotNull(updatedProduct, "Updated product should not be null");
        assertEquals(product.getProductId(), updatedProduct.getProductId());
        assertEquals(product.getProductName(), updatedProduct.getProductName());
        assertEquals(product.getProductQuantity(), updatedProduct.getProductQuantity());
    }

    @Test
    void testDelete() {
        doNothing().when(productService).delete(product.getProductId());
        when(productService.findAll()).thenReturn(List.of(product));
        productRepository.delete(product.getProductId());
        verify(productService, times(1)).delete(product.getProductId());
        productRepository.findAll();
        verify(productService, times(1)).findAll();
    }

    @Test
    void testDeleteNonExistentProduct() {
        doNothing().when(productService).delete("non-existent-id");
        when(productService.findAll()).thenReturn(List.of());
        productRepository.delete("non-existent-id");
        verify(productService, times(1)).delete("non-existent-id");
        List<Product> products = productRepository.findAll();
        assertTrue(products.isEmpty(), "Product list should be empty after attempting to delete non-existent product");
    }
}