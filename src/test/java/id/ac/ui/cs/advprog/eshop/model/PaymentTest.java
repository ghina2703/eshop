package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Payment payment;
    private String id = "12345";
    private String method = "Voucher";
    private String status = "PENDING";
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentData = Map.of("voucherCode", "ESHOP1234ABC5678");
        payment = new Payment(id, method, status, paymentData);
    }

    @Test
    void testPaymentInitialization() {
        assertNotNull(payment);
        assertEquals(id, payment.getId());
        assertEquals(method, payment.getMethod());
        assertEquals(status, payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testSettersAndGetters() {
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());

        payment.setMethod("Bank Transfer");
        assertEquals("Bank Transfer", payment.getMethod());
    }
}
