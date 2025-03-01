package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
    }

    @Test
    void testDefaultConstructor() {
        assertNotNull(product.getProductId(), "Product ID harus diinisialisasi otomatis");
        assertEquals("", product.getProductName(), "Product name harus default kosong");
        assertEquals(0, product.getProductQuantity(), "Product quantity harus default 0");
    }

    @Test
    void testParameterizedConstructor() {
        Product testProduct = new Product("123", "Shampoo", 50);

        assertEquals("123", testProduct.getProductId(), "Product ID harus sesuai dengan input");
        assertEquals("Shampoo", testProduct.getProductName(), "Product name harus sesuai dengan input");
        assertEquals(50, testProduct.getProductQuantity(), "Product quantity harus sesuai dengan input");
    }

    @Test
    void testSetAndGetProductId() {
        product.setProductId("456");
        assertEquals("456", product.getProductId(), "Product ID harus sesuai dengan setter");
    }

    @Test
    void testSetAndGetProductName() {
        product.setProductName("Conditioner");
        assertEquals("Conditioner", product.getProductName(), "Product name harus sesuai dengan setter");
    }

    @Test
    void testSetAndGetProductQuantity() {
        product.setProductQuantity(100);
        assertEquals(100, product.getProductQuantity(), "Product quantity harus sesuai dengan setter");
    }

    @Test
    void testSetProductNameEmpty() {
        product.setProductName("");
        assertEquals("", product.getProductName(), "Product name bisa diubah menjadi string kosong");
    }

    @Test
    void testSetProductQuantityNegative() {
        product.setProductQuantity(-1);
        assertEquals(-1, product.getProductQuantity(), "Product quantity bisa diubah menjadi angka negatif");
    }
}
