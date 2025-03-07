package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceTest {
    private PaymentService paymentService;
    private PaymentRepository paymentRepository;
    private OrderRepository orderRepository;
    private Order order;
    private Payment payment;

    @BeforeEach
    void setUp() {
        paymentRepository = mock(PaymentRepository.class);
        orderRepository = mock(OrderRepository.class);

        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);
        products.add(product);

        Map<String, String> paymentData = Map.of("voucherCode", "ESHOP1234ABC5678");

        payment = new Payment("12345-payment", "Voucher", "PENDING", paymentData);
        order = new Order("12345", products, 1708560000L, "Safira Sudrajat");

        paymentService = new PaymentService(paymentRepository, orderRepository);
    }

    @Test
    void testAddPayment() {
        Map<String, String> paymentData = Map.of("voucherCode", "ESHOP1234ABC5678");

        doNothing().when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order, "Voucher", paymentData);

        assertNotNull(result);
        assertEquals("PENDING", result.getStatus());

        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetStatusSuccess() {
        doNothing().when(paymentRepository).save(any(Payment.class));
        when(orderRepository.findById(anyString())).thenReturn(order);

        Payment result = paymentService.setStatus(payment, "SUCCESS");

        assertEquals("SUCCESS", result.getStatus());
        assertEquals("SUCCESS", order.getStatus());

        verify(paymentRepository, times(1)).save(any(Payment.class));
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testSetStatusRejected() {
        doNothing().when(paymentRepository).save(any(Payment.class));
        when(orderRepository.findById(anyString())).thenReturn(order);

        Payment result = paymentService.setStatus(payment, "REJECTED");

        assertEquals("FAILED", result.getStatus());
        assertEquals("FAILED", order.getStatus());

        verify(paymentRepository, times(1)).save(any(Payment.class));
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testGetPayment() {
        when(paymentRepository.findById(anyString())).thenReturn(payment);

        Payment result = paymentService.getPayment("12345-payment");

        assertNotNull(result);
        assertEquals("12345-payment", result.getId());
    }

    @Test
    void testAddVoucherPaymentValidCode() {
        Payment result = paymentService.addVoucherPayment(order, "ESHOP1234ABC5678");

        assertNotNull(result);
        assertEquals("SUCCESS", result.getStatus());

        verify(paymentRepository, times(1)).save(result);
    }

    @Test
    void testAddVoucherPaymentInvalidCode() {
        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addVoucherPayment(order, "INVALIDCODE");
        });

        verify(paymentRepository, never()).save(any(Payment.class));
    }

    @Test
    void testAddBankTransferPaymentValidData() {
        Map<String, String> paymentData = Map.of("bankAccount", "123456789");

        Payment result = paymentService.addBankTransferPayment(order, "123456789");

        assertNotNull(result);
        assertEquals("SUCCESS", result.getStatus());
        verify(paymentRepository, times(1)).save(result);
    }

    @Test
    void testAddBankTransferPaymentInvalidData() {
        String invalidBankAccount = "INVALID_ACCOUNT";

        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addBankTransferPayment(order, invalidBankAccount);
        });

        verify(paymentRepository, never()).save(any(Payment.class));
    }

    @Test
    void testSetStatusBankTransferSuccess() {
        doNothing().when(paymentRepository).save(any(Payment.class));
        when(orderRepository.findById(anyString())).thenReturn(order);

        Payment result = paymentService.setStatus(payment, "SUCCESS");

        assertEquals("SUCCESS", result.getStatus());
        assertEquals("SUCCESS", order.getStatus());

        verify(paymentRepository, times(1)).save(any(Payment.class));
        verify(orderRepository, times(1)).save(any(Order.class));
    }
}
