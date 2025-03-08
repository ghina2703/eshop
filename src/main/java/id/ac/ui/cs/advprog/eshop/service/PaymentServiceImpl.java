package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentDetails) {
        Payment payment = new Payment(order.getId(), method, paymentDetails);
        paymentRepository.save(payment);
        return payment;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        Order order = orderRepository.findById(payment.getId());

        if (order != null) {
            if ("SUCCESS".equals(status)) {
                order.setStatus(OrderStatus.SUCCESS.getValue());
            } else if ("REJECTED".equals(status)) {
                order.setStatus(OrderStatus.FAILED.getValue());
            } else {
                throw new IllegalArgumentException("Invalid status: " + status);
            }
            orderRepository.save(order);
            payment.setStatus(status);
            paymentRepository.save(payment);
        } else {
            throw new NoSuchElementException("Order not found for payment ID: " + payment.getId());
        }
        return payment;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
