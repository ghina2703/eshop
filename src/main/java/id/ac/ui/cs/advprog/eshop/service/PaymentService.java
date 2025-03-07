package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;

import java.util.List;
import java.util.Map;

public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentService(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    public Payment addVoucherPayment(Order order, String voucherCode) {
        if (!isValidVoucherCode(voucherCode)) {
            throw new IllegalArgumentException("Invalid voucher code");
        }

        Map<String, String> paymentData = Map.of("voucherCode", voucherCode);
        Payment payment = new Payment(order.getId() + "-payment", "Voucher", "SUCCESS", paymentData);
        paymentRepository.save(payment);
        return payment;
    }

    private boolean isValidVoucherCode(String voucherCode) {
        return voucherCode.length() == 16 && voucherCode.startsWith("ESHOP") && voucherCode.substring(5).matches("\\d{8}");
    }

    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        String paymentId = order.getId() + "-payment";
        Payment payment = new Payment(paymentId, method, "PENDING", paymentData);
        paymentRepository.save(payment);
        return payment;
    }

    public Payment setStatus(Payment payment, String status) {
        payment.setStatus(status);
        paymentRepository.save(payment);

        Order order = orderRepository.findById(payment.getId());
        if (order != null) {
            if ("SUCCESS".equals(status)) {
                order.setStatus("SUCCESS");
            } else if ("REJECTED".equals(status)) {
                order.setStatus("FAILED");
            }
            orderRepository.save(order);
        }
        return payment;
    }

    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
