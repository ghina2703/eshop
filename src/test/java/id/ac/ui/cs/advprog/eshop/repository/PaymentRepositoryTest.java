package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    private PaymentRepository paymentRepository;
    private Payment payment;
    private String id = "12345";
    private String method = "Voucher";
    private String status = "PENDING";
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        paymentData = Map.of("voucherCode", "ESHOP1234ABC5678");
        payment = new Payment(id, method, status, paymentData);
    }

    @Test
    void testSaveAndFindById() {
        paymentRepository.save(payment);
        Payment foundPayment = paymentRepository.findById(id);
        assertNotNull(foundPayment);
        assertEquals(id, foundPayment.getId());
        assertEquals(method, foundPayment.getMethod());
        assertEquals(status, foundPayment.getStatus());
        assertEquals(paymentData, foundPayment.getPaymentData());
    }

    @Test
    void testFindAllPayments() {
        paymentRepository.save(payment);
        assertEquals(1, paymentRepository.findAll().size());
    }

    @Test
    void testFindByIdNotFound() {
        Payment foundPayment = paymentRepository.findById("nonexistent-id");
        assertNull(foundPayment);
    }

    @Test
    void testFindByIdWithInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> paymentRepository.findById(null));
        assertThrows(IllegalArgumentException.class, () -> paymentRepository.findById(""));
    }

    @Test
    void testSaveWithNullPayment() {
        assertThrows(IllegalArgumentException.class, () -> paymentRepository.save(null));
    }
}