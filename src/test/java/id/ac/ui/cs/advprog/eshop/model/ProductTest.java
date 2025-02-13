package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class ProductTest {
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
        ProductRepository productRepository = new ProductRepository();
        productRepository.create(this.product);
        List<Product> products = productRepository.findAll();
        assertTrue(products.contains(this.product), "Produk harus ada di dalam repository");
        productRepository.delete(this.product.getProductId());
        products = productRepository.findAll();
        assertFalse(products.contains(this.product), "Produk harus dihapus dari repository");
    }
}
