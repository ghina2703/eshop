package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderControllerTest {
    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @Mock
    private Model model;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    void testCreateOrderPage() throws Exception {
        mockMvc.perform(get("/order/create"))
                .andExpect(status().isOk());

        String viewName = orderController.createOrderPage(model);
        assertEquals("createOrder", viewName);
    }

    @Test
    void testHistoryOrderPage() throws Exception {
        mockMvc.perform(get("/order/history"))
                .andExpect(status().isOk());

        String viewName = orderController.historyOrderPage();
        assertEquals("orderHistory", viewName);
    }

    @Test
    void testHistoryOrderPost() throws Exception {
        mockMvc.perform(post("/order/history"))
                .andExpect(status().isOk());

        String viewName = orderController.historyOrderPost();
        assertEquals("orderHistory", viewName);
    }

    @Test
    void testPayOrderPage() throws Exception {
        mockMvc.perform(get("/order/pay/123"))
                .andExpect(status().isOk());

        String viewName = orderController.payOrderPage();
        assertEquals("payOrder", viewName);
    }

    @Test
    void testPayOrderPost() throws Exception {
        mockMvc.perform(post("/order/pay/123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("history"));

        String viewName = orderController.payOrderPost();
        assertEquals("redirect:history", viewName);
    }
}
