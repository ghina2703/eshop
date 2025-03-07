package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import java.util.Map;

public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        Payment payment = new Payment(order.getId(), method, "PENDING", paymentData);
        paymentRepository.save(payment);
        return payment;
    }

    public void setStatus(Payment payment, String status) {
        if (status.equals("SUCCESS")) {
            payment.setStatus("SUCCESS");
            paymentRepository.save(payment);
        } else if (status.equals("REJECTED")) {
            payment.setStatus("REJECTED");
            paymentRepository.save(payment);
            Order order = new Order(payment.getId(), null, 1708560000L, "Safira Sudrajat");
            order.setStatus("FAILED");
        }
    }

    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }
}
