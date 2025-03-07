package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {
    private PaymentService paymentService;
    private PaymentRepository paymentRepository;
    private Order order;
    private Payment payment;
    private String id = "12345";
    private String method = "Voucher";
    private String status = "PENDING";
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentRepository = mock(PaymentRepository.class);
        paymentService = new PaymentService(paymentRepository);
        paymentData = Map.of("voucherCode", "ESHOP1234ABC5678");
        order = new Order(id, null, 1708560000L, "Safira Sudrajat");
        payment = new Payment(id, method, status, paymentData);
    }

    @Test
    void testAddPayment() {
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment result = paymentService.addPayment(order, "Voucher", paymentData);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(method, result.getMethod());
        assertEquals(status, result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetStatusSuccess() {
        paymentService.addPayment(order, "Voucher", paymentData);
        paymentService.setStatus(payment, "SUCCESS");

        assertEquals("SUCCESS", payment.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetStatusRejected() {
        paymentService.addPayment(order, "Voucher", paymentData);
        paymentService.setStatus(payment, "REJECTED");

        assertEquals("FAILED", order.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testGetPayment() {
        when(paymentRepository.findById(id)).thenReturn(payment);

        Payment result = paymentService.getPayment(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
    }
}
