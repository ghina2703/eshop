package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import java.util.Map;

public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        Payment payment = new Payment(order.getId(), method, OrderStatus.WAITING_PAYMENT.getValue(), paymentData);
        paymentRepository.save(payment);
        return payment;
    }

    public void setStatus(Payment payment, OrderStatus status) {
        if (status == OrderStatus.SUCCESS) {
            payment.setStatus(OrderStatus.SUCCESS.getValue());
            paymentRepository.save(payment);
            Order order = new Order(payment.getId(), null, 1708560000L, "Safira Sudrajat");
            order.setStatus(OrderStatus.SUCCESS.getValue());
        } else if (status == OrderStatus.FAILED) {
            payment.setStatus(OrderStatus.FAILED.getValue());
            paymentRepository.save(payment);
            Order order = new Order(payment.getId(), null, 1708560000L, "Safira Sudrajat");
            order.setStatus(OrderStatus.FAILED.getValue());
        }
    }

    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }
}
