package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build(); // Set up mock MVC
    }

    @Test
    void testCreateProductPage() throws Exception {
        // Act and Assert
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testCreateProductPost() throws Exception {
        // Arrange
        Product product = new Product();
        product.setProductName("Product A");
        product.setProductQuantity(10);

        // Act and Assert
        mockMvc.perform(post("/product/create")
                        .flashAttr("product", product))  // Use flashAttr to simulate form submission
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        // Verify service interaction
        verify(productService, times(1)).create(any(Product.class));
    }

    @Test
    void testProductListPage() throws Exception {
        // Arrange
        Product product1 = new Product();
        product1.setProductName("Product A");
        product1.setProductQuantity(10);

        Product product2 = new Product();
        product2.setProductName("Product B");
        product2.setProductQuantity(20);

        List<Product> allProducts = Arrays.asList(product1, product2);
        when(productService.findAll()).thenReturn(allProducts);

        // Act and Assert
        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("productList"))
                .andExpect(model().attribute("products", allProducts));

        // Verify service interaction
        verify(productService, times(1)).findAll();
    }

    @Test
    void testEditProductPage() throws Exception {
        // Arrange
        String productId = "1";
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName("Product A");
        product.setProductQuantity(10);

        when(productService.findById(productId)).thenReturn(product);

        // Act and Assert
        mockMvc.perform(get("/product/edit/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(view().name("editProduct"))
                .andExpect(model().attribute("product", product));

        // Verify service interaction
        verify(productService, times(1)).findById(productId);
    }

    @Test
    void testEditProductPageNotFound() throws Exception {
        // Arrange
        String productId = "non-existing-id";
        when(productService.findById(productId)).thenReturn(null);

        // Act and Assert
        mockMvc.perform(get("/product/edit/{id}", productId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        // Verify service interaction
        verify(productService, times(1)).findById(productId);
    }

    @Test
    void testEditProductPost() throws Exception {
        // Arrange
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Updated Product");
        product.setProductQuantity(30);

        // Act and Assert
        mockMvc.perform(post("/product/edit")
                        .flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        // Verify service interaction
        verify(productService, times(1)).update(any(Product.class));
    }

    @Test
    void testDeleteProduct() throws Exception {
        // Arrange
        String productId = "1";

        // Act and Assert
        mockMvc.perform(get("/product/delete/{id}", productId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        // Verify service interaction
        verify(productService, times(1)).delete(productId);
    }
}
