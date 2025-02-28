package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static java.util.Arrays.asList;
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
        MockitoAnnotations.openMocks(this);
    }

    private Product createTestProduct(String id, String name, int quantity) {
        Product product = new Product();
        product.setProductId(id);
        product.setProductName(name);
        product.setProductQuantity(quantity);
        return product;
    }

    @Test
    void testCreateProduct() {
        Product product = createTestProduct(null, "Product A", 10);
        when(productRepository.create(any(Product.class))).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct);
        assertEquals("Product A", createdProduct.getProductName());
        assertEquals(10, createdProduct.getProductQuantity());

        verify(productRepository, times(1)).create(any(Product.class));
    }

    @Test
    void testFindAll() {
        List<Product> allProducts = asList(
                createTestProduct("1", "Product A", 10),
                createTestProduct("2", "Product B", 20)
        );

        when(productRepository.findAll()).thenReturn(allProducts);

        List<Product> products = productService.findAll();

        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Product A", products.get(0).getProductName());
        assertEquals("Product B", products.get(1).getProductName());

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Product product = createTestProduct("1", "Product A", 10);
        when(productRepository.findById("1")).thenReturn(product);

        Product foundProduct = productService.findById("1");

        assertNotNull(foundProduct);
        assertEquals("Product A", foundProduct.getProductName());
        assertEquals(10, foundProduct.getProductQuantity());

        verify(productRepository, times(1)).findById("1");
    }

    @Test
    void testFindByIdNotFound() {
        when(productRepository.findById("non-existing-id")).thenReturn(null);

        Product foundProduct = productService.findById("non-existing-id");

        assertNull(foundProduct);
        verify(productRepository, times(1)).findById("non-existing-id");
    }

    @Test
    void testUpdateProduct() throws Exception {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("1");
        updatedProduct.setProductName("Product A Updated");
        updatedProduct.setProductQuantity(15);
        when(productRepository.findById("1")).thenReturn(updatedProduct);
        when(productRepository.update(any(Product.class))).thenReturn(updatedProduct);

        Product result = productService.update("1", updatedProduct);
        assertNotNull(result);
        assertEquals("Product A Updated", result.getProductName());
        assertEquals(15, result.getProductQuantity());
        verify(productRepository, times(1)).update(any(Product.class));
    }

    @Test
    void testUpdateProductNotFound() {
        Product updatedProduct = createTestProduct("non-existing-id", "Updated Product", 15);
        when(productRepository.findById("non-existing-id")).thenReturn(null);
        Product result = productService.update(updatedProduct.getProductId(), updatedProduct);
        assertNull(result);
        verify(productRepository, times(0)).update(any(Product.class));
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).delete("1");

        productService.delete("1");

        verify(productRepository, times(1)).delete("1");
    }

    @Test
    void testDeleteProductNotFound() {
        doNothing().when(productRepository).delete("non-existing-id");

        productService.delete("non-existing-id");

        verify(productRepository, times(1)).delete("non-existing-id");
    }
}