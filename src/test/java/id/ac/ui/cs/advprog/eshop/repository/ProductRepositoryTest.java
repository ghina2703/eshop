package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setup() {
        productRepository = new ProductRepository();
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb55e89f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        List<Product> products = productRepository.findAll();
        assertTrue(products.contains(product));
        Product savedProduct = products.get(products.indexOf(product));
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        List<Product> products = productRepository.findAll();
        assertTrue(products.isEmpty(), "Daftar produk harus kosong");
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb55e89f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap User");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        List<Product> products = productRepository.findAll();
        assertEquals(2, products.size(), "Seharusnya ada dua produk");
        assertTrue(products.contains(product1), "Produk1 harus ada dalam daftar");
        assertTrue(products.contains(product2), "Produk2 harus ada dalam daftar");
    }

    @Test
    void testEditProductName() {
        Product product = new Product();
        product.setProductId("eb55e89f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        product.setProductName("Sampo Cap Super");
        productRepository.update(product);
        List<Product> products = productRepository.findAll();
        assertTrue(products.contains(product));
        Product savedProduct = products.get(products.indexOf(product));
        assertEquals("Sampo Cap Super", savedProduct.getProductName(), "Nama produk harus diperbarui");
    }

    @Test
    void testEditProductQuantity() {
        Product product = new Product();
        product.setProductId("eb55e89f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        product.setProductQuantity(200);
        productRepository.update(product);
        List<Product> products = productRepository.findAll();
        assertTrue(products.contains(product));
        Product savedProduct = products.get(products.indexOf(product));
        assertEquals(200, savedProduct.getProductQuantity(), "Jumlah produk harus diperbarui");
    }

    @Test
    void testEditProductQuantityToNegative() {
        Product product = new Product();
        product.setProductId("eb55e89f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        product.setProductQuantity(-50);
        productRepository.update(product);
        List<Product> products = productRepository.findAll();
        assertTrue(products.contains(product));
        Product savedProduct = products.get(products.indexOf(product));
        assertTrue(savedProduct.getProductQuantity() < 0, "Jumlah produk tidak boleh negatif");
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("eb55e89f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        productRepository.create(product);
        List<Product> products = productRepository.findAll();
        assertTrue(products.contains(product), "Produk harus ada dalam repository");
        productRepository.delete(product.getProductId());
        products = productRepository.findAll();
        assertFalse(products.contains(product), "Produk harus dihapus dari repository");
    }
}
