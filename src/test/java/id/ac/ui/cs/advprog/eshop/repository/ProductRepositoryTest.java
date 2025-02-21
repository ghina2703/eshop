package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setup() {
        productRepository = new ProductRepository();
    }

    private Product createTestProduct(String name, int quantity) {
        Product product = new Product();
        product.setProductId(UUID.randomUUID().toString());
        product.setProductName(name);
        product.setProductQuantity(quantity);
        return product;
    }

    @Test
    void testCreateAndFind() {
        Product product = createTestProduct("Sampo Cap Bambang", 100);
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
        Product product1 = createTestProduct("Sampo Cap Bambang", 100);
        Product product2 = createTestProduct("Sampo Cap User", 50);

        productRepository.create(product1);
        productRepository.create(product2);

        List<Product> products = productRepository.findAll();
        assertEquals(2, products.size(), "Seharusnya ada dua produk");
        assertTrue(products.contains(product1));
        assertTrue(products.contains(product2));
    }

    @Test
    void testUpdateProductWithPartialChanges() {
        Product product = createTestProduct("Shampoo Original", 100);
        productRepository.create(product);

        product.setProductName("Shampoo Plus");
        Product result = productRepository.update(product);

        assertNotNull(result, "Produk harus berhasil diperbarui");
        assertEquals("Shampoo Plus", result.getProductName(), "Nama harus berubah");
        assertEquals(100, result.getProductQuantity(), "Jumlah tidak boleh berubah");
    }

    @Test
    void testUpdateProductWithNoChanges() {
        Product product = createTestProduct("Shampoo Original", 100);
        productRepository.create(product);

        Product result = productRepository.update(product);

        assertNotNull(result, "Produk tetap ada meskipun tidak ada perubahan");
        assertSame(product, result, "Produk yang dikembalikan harus memiliki referensi yang sama");
    }

    @Test
    void testDeleteProduct() {
        Product product = createTestProduct("Sampo Cap Bambang", 100);
        productRepository.create(product);

        List<Product> products = productRepository.findAll();
        assertTrue(products.contains(product));

        productRepository.delete(product.getProductId());
        products = productRepository.findAll();
        assertFalse(products.contains(product), "Produk harus dihapus dari repository");
    }

    @Test
    void testDeleteNonExistentProduct() {
        productRepository.delete("non-existent-id");
        List<Product> products = productRepository.findAll();
        assertTrue(products.isEmpty(), "Menghapus produk yang tidak ada tidak boleh mengubah repository");
    }

    @Test
    void testFindById() {
        Product product = createTestProduct("Shampoo", 100);
        productRepository.create(product);

        Product foundProduct = productRepository.findById(product.getProductId());
        assertNotNull(foundProduct, "Produk harus ditemukan");
        assertEquals(product.getProductId(), foundProduct.getProductId());

        Product notFoundProduct = productRepository.findById("non-existent-id");
        assertNull(notFoundProduct, "Produk dengan ID tidak valid harus menghasilkan null");
    }

    @Test
    void testCreateProductWithEmptyName() {
        Product product = createTestProduct("", 10);
        productRepository.create(product);

        List<Product> products = productRepository.findAll();
        assertTrue(products.contains(product), "Produk dengan nama kosong masih bisa ditambahkan");
    }

    @Test
    void testCreateProductWithZeroQuantity() {
        Product product = createTestProduct("Shampoo Zero", 0);
        productRepository.create(product);

        List<Product> products = productRepository.findAll();
        assertTrue(products.contains(product), "Produk dengan jumlah nol harus bisa disimpan");
    }

    @Test
    void testUpdateNonExistentProduct() {
        Product product = createTestProduct("Non-existent Product", 100);
        product.setProductId("non-existent-id");

        Product result = productRepository.update(product);
        assertNull(result, "Produk tidak ada, sehingga tidak bisa diperbarui");
    }

    @Test
    void testUpdateProductWithQuantityChangeOnly() {
        Product product = createTestProduct("Shampoo Original", 100);
        productRepository.create(product);

        product.setProductQuantity(200);
        Product result = productRepository.update(product);

        assertNotNull(result, "Produk harus berhasil diperbarui");
        assertEquals("Shampoo Original", result.getProductName(), "Nama tidak boleh berubah");
        assertEquals(200, result.getProductQuantity(), "Jumlah harus berubah");
    }

    @Test
    void testUpdateWithSameValues() {
        Product product = createTestProduct("Shampoo", 50);
        productRepository.create(product);

        Product updatedProduct = createTestProduct("Shampoo", 50);
        updatedProduct.setProductId(product.getProductId());

        Product result = productRepository.update(updatedProduct);

        assertNotNull(result, "Produk tetap harus ada meskipun tidak ada perubahan");
        assertSame(product, result, "Referensi objek harus tetap sama karena tidak ada perubahan");
    }

    @Test
    void testUpdateNullProduct() {
        Product result = productRepository.update(null);
        assertNull(result, "Update produk null harus menghasilkan null");
    }

    @Test
    void testUpdateProductWithNullId() {
        Product product = createTestProduct("Invalid Product", 30);
        product.setProductId(null);

        Product result = productRepository.update(product);
        assertNull(result, "Update produk dengan ID null harus gagal");
    }

    @Test
    void testUpdateProductWithEmptyName() {
        Product product = createTestProduct("", 30);
        productRepository.create(product);

        product.setProductName("Updated Name");
        Product result = productRepository.update(product);

        assertNotNull(result, "Produk harus berhasil diperbarui meskipun awalnya memiliki nama kosong");
        assertEquals("Updated Name", result.getProductName(), "Nama harus diperbarui dengan nilai baru");
    }

    @Test
    void testUpdateProductWithInvalidId() {
        Product product = createTestProduct("Shampoo", 50);
        productRepository.create(product);

        Product invalidProduct = createTestProduct("Invalid", 30);
        invalidProduct.setProductId(null);

        Product result = productRepository.update(invalidProduct);
        assertNull(result, "Produk dengan ID null tidak boleh diperbarui");

        invalidProduct.setProductId("");
        result = productRepository.update(invalidProduct);
        assertNull(result, "Produk dengan ID kosong tidak boleh diperbarui");
    }

    @Test
    void testDeleteWithNullId() {
        productRepository.delete(null);
        List<Product> products = productRepository.findAll();
        assertTrue(products.isEmpty(), "Hapus produk dengan ID null tidak boleh mengubah repository");
    }

    @Test
    void testDeleteWithEmptyId() {
        productRepository.delete("");
        List<Product> products = productRepository.findAll();
        assertTrue(products.isEmpty(), "Hapus produk dengan ID kosong tidak boleh mengubah repository");
    }

    @Test
    void testUpdateProductWithZeroQuantityToNegative() {
        Product product = createTestProduct("Shampoo", 0);
        productRepository.create(product);

        product.setProductQuantity(-5);
        Product result = productRepository.update(product);

        assertNotNull(result, "Produk harus tetap diperbarui meskipun jumlah negatif");
        assertEquals(-5, result.getProductQuantity(), "Jumlah produk harus diperbarui menjadi negatif");
    }

    @Test
    void testUpdateProductWithZeroQuantityToPositive() {
        Product product = createTestProduct("Shampoo", 0);
        productRepository.create(product);

        product.setProductQuantity(10);
        Product result = productRepository.update(product);

        assertNotNull(result, "Produk harus diperbarui");
        assertEquals(10, result.getProductQuantity(), "Jumlah produk harus berubah dari 0 ke 10");
    }

    @Test
    void testDeleteAfterUpdate() {
        Product product = createTestProduct("Shampoo", 10);
        productRepository.create(product);

        product.setProductQuantity(20);
        productRepository.update(product);

        productRepository.delete(product.getProductId());
        List<Product> products = productRepository.findAll();
        assertFalse(products.contains(product), "Produk harus dihapus setelah diperbarui");
    }

    @Test
    void testUpdateProductWithSingleCharacterChange() {
        Product product = createTestProduct("Shampo", 50);
        productRepository.create(product);

        product.setProductName("Shampoo");
        Product result = productRepository.update(product);

        assertNotNull(result, "Produk harus diperbarui");
        assertEquals("Shampoo", result.getProductName(), "Nama produk harus diperbarui dengan satu karakter tambahan");
    }

    @Test
    void testDeleteOneProductFromMultipleProducts() {
        Product product1 = createTestProduct("Shampoo", 10);
        Product product2 = createTestProduct("Conditioner", 20);

        productRepository.create(product1);
        productRepository.create(product2);

        productRepository.delete(product1.getProductId());
        List<Product> products = productRepository.findAll();

        assertEquals(1, products.size(), "Seharusnya hanya satu produk yang tersisa");
        assertFalse(products.contains(product1), "Produk pertama harus dihapus");
        assertTrue(products.contains(product2), "Produk kedua harus tetap ada");
    }

    @Test
    void testFindByIdWithNullId() {
        Product result = productRepository.findById(null);
        assertNull(result, "Mencari produk dengan ID null harus menghasilkan null");
    }

    @Test
    void testFindByIdWithEmptyId() {
        Product result = productRepository.findById("");
        assertNull(result, "Mencari produk dengan ID kosong harus menghasilkan null");
    }

    @Test
    void testUpdateProductWithNullName() {
        Product product = createTestProduct("Shampoo", 100);
        productRepository.create(product);

        product.setProductName(null);
        Product result = productRepository.update(product);

        assertNotNull(result, "Produk tetap harus diperbarui meskipun nama null");
        assertNull(result.getProductName(), "Nama produk harus diperbarui menjadi null");
    }

    @Test
    void testUpdateProductWithNullQuantity() {
        Product product = createTestProduct("Shampoo", 100);
        productRepository.create(product);

        product.setProductQuantity(0);
        Product result = productRepository.update(product);

        assertNotNull(result, "Produk tetap harus diperbarui meskipun jumlah 0");
        assertEquals(0, result.getProductQuantity(), "Jumlah produk harus diperbarui menjadi 0");
    }

    @Test
    void testUpdateProductWithNoChangesMultipleTimes() {
        Product product = createTestProduct("Shampoo", 100);
        productRepository.create(product);

        Product updatedProduct = createTestProduct("Shampoo", 100);
        updatedProduct.setProductId(product.getProductId());

        Product result1 = productRepository.update(updatedProduct);
        Product result2 = productRepository.update(updatedProduct);

        assertNotNull(result1, "Produk harus ada meskipun tidak ada perubahan");
        assertSame(product, result1, "Referensi objek harus tetap sama");
        assertSame(result1, result2, "Mengupdate tanpa perubahan berulang kali harus tetap mengembalikan objek yang sama");
    }

    @Test
    void testUpdateProductWithWhitespaceName() {
        Product product = createTestProduct("Shampoo", 100);
        productRepository.create(product);

        product.setProductName(" ");
        Product result = productRepository.update(product);

        assertNotNull(result, "Produk harus tetap diperbarui meskipun namanya hanya berisi spasi");
        assertEquals(" ", result.getProductName(), "Nama produk harus diperbarui menjadi string spasi");
    }

    @Test
    void testFindByIdWithMalformedUUID() {
        String malformedUUID = "12345";
        Product result = productRepository.findById(malformedUUID);
        assertNull(result, "Product with malformed UUID should return null");
    }

    @Test
    void testDeleteProductWithMalformedUUID() {
        String malformedUUID = "12345";
        productRepository.delete(malformedUUID);
        List<Product> products = productRepository.findAll();
        assertTrue(products.isEmpty(), "Repository should remain empty after attempting to delete with malformed UUID");
    }

}
