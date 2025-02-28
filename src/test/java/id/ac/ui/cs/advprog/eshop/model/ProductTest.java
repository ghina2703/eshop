package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
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
class ProductTest {

    @Mock
    private ProductService productService;  // Mock the ProductService

    @InjectMocks
    private ProductRepository productRepository;  // Inject the mock ProductService into ProductRepository

    Product product;

    @BeforeEach
    void setup() {
        this.product = new Product();
        this.product.setProductId("eb55e89f-1c39-460e-8860-71af6af63bd6");
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);
    }

    @Test
    void testGetProductId() {
        assertEquals("eb55e89f-1c39-460e-8860-71af6af63bd6", this.product.getProductId());
    }

    @Test
    void testGetProductName() {
        assertEquals("Sampo Cap Bambang", this.product.getProductName());
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(100, this.product.getProductQuantity());
    }

    @Test
    void testNegativeQuantity() {
        this.product.setProductQuantity(-50);
        assertTrue(this.product.getProductQuantity() < 0, "Jumlah produk tidak boleh negatif");
    }

    @Test
    void testEditProductName() {
        this.product.setProductName("Sampo Cap Super");
        assertEquals("Sampo Cap Super", this.product.getProductName(), "Nama produk harus diperbarui");
    }

    @Test
    void testEditProductQuantity() {
        this.product.setProductQuantity(150);
        assertEquals(150, this.product.getProductQuantity(), "Jumlah produk harus diperbarui");
    }

    @Test
    void testEditProductQuantityToNegative() {
        this.product.setProductQuantity(-10);
        assertTrue(this.product.getProductQuantity() < 0, "Jumlah produk tidak boleh negatif");
    }

    @Test
    void testDeleteProduct() {
        when(productService.create(this.product)).thenReturn(this.product);
        when(productService.findAll()).thenReturn(List.of(this.product));
        doNothing().when(productService).delete(this.product.getProductId());

        productRepository.create(this.product);
        List<Product> products = productRepository.findAll();
        assertTrue(products.contains(this.product), "Produk harus ada di dalam repository");

        productRepository.delete(this.product.getProductId());
        products = productRepository.findAll();
        assertFalse(products.contains(this.product), "Produk harus dihapus dari repository");
    }

    @Test
    void testDefaultConstructor() {
        Product product = new Product();

        assertNotNull(product.getProductId(), "Product ID harus diinisialisasi secara otomatis");
        assertEquals("", product.getProductName(), "Product name harus default kosong");
        assertEquals(0, product.getProductQuantity(), "Product quantity harus default 0");
    }

    @Test
    void testParameterizedConstructor() {
        String productId = "12345";
        String productName = "Shampoo";
        int productQuantity = 10;

        Product product = new Product(productId, productName, productQuantity);

        assertEquals(productId, product.getProductId(), "Product ID harus sesuai dengan input");
        assertEquals(productName, product.getProductName(), "Product name harus sesuai dengan input");
        assertEquals(productQuantity, product.getProductQuantity(), "Product quantity harus sesuai dengan input");
    }

    @Test
    void testSetAndGetProductId() {
        Product product = new Product();
        String newId = "abc123";

        product.setProductId(newId);
        assertEquals(newId, product.getProductId(), "Setter harus mengubah Product ID dengan benar");
    }

    @Test
    void testSetAndGetProductName() {
        Product product = new Product();
        String newName = "Conditioner";

        product.setProductName(newName);
        assertEquals(newName, product.getProductName(), "Setter harus mengubah Product name dengan benar");
    }

    @Test
    void testSetAndGetProductQuantity() {
        Product product = new Product();
        int newQuantity = 50;

        product.setProductQuantity(newQuantity);
        assertEquals(newQuantity, product.getProductQuantity(), "Setter harus mengubah Product quantity dengan benar");
    }
}
