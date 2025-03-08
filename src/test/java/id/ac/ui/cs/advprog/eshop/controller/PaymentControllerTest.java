package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PaymentControllerTest {
    private MockMvc mockMvc;

    @Mock
    private PaymentService paymentService;

    @Mock
    private Model model;

    @InjectMocks
    private PaymentController paymentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();
    }

    @Test
    void testGetPaymentDetailForm() throws Exception {
        mockMvc.perform(get("/payment/detail"))
                .andExpect(status().isOk());

        String viewName = paymentController.getPaymentDetailForm();
        assertEquals("payment/payment_detail_form", viewName);
    }

    @Test
    void testGetPaymentDetail() throws Exception {
        Payment payment = new Payment("123-payment", "Voucher", null);
        when(paymentService.getPayment("123-payment")).thenReturn(payment);

        mockMvc.perform(get("/payment/detail/123-payment"))
                .andExpect(status().isOk());

        String viewName = paymentController.getPaymentDetail("123-payment", model);
        assertEquals("payment/payment_detail", viewName);
        verify(model).addAttribute("payment", payment);
    }

    @Test
    void testGetAllPayments() throws Exception {
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment("123-payment", "Voucher", null));

        when(paymentService.getAllPayments()).thenReturn(payments);

        mockMvc.perform(get("/payment/admin/list"))
                .andExpect(status().isOk());

        String viewName = paymentController.getAllPayments(model);
        assertEquals("payment/admin_payment_list", viewName);
        verify(model).addAttribute("payments", payments);
    }

    @Test
    void testGetAdminPaymentDetail() throws Exception {
        Payment payment = new Payment("123-payment", "Voucher", null);
        when(paymentService.getPayment("123-payment")).thenReturn(payment);

        mockMvc.perform(get("/payment/admin/detail/123-payment"))
                .andExpect(status().isOk());

        String viewName = paymentController.getAdminPaymentDetail("123-payment", model);
        assertEquals("payment/admin_payment_detail", viewName);
        verify(model).addAttribute("payment", payment);
    }

    @Test
    void testSetPaymentStatus() throws Exception {
        Payment payment = new Payment("123-payment", "Voucher", null);
        when(paymentService.getPayment("123-payment")).thenReturn(payment);

        mockMvc.perform(post("/payment/admin/set-status/123-payment")
                        .param("status", "SUCCESS"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/payment/admin/list"));

        paymentController.setPaymentStatus("123-payment", "SUCCESS");
        verify(paymentService).setStatus(payment, "SUCCESS");
    }
}
