package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import java.util.List;
import java.util.Map;

public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    @Override
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

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        String paymentId = order.getId() + "-payment";
        Payment payment = new Payment(paymentId, method, "PENDING", paymentData);
        paymentRepository.save(payment);
        return payment;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        if (payment == null) {
            throw new IllegalArgumentException("Payment cannot be null");
        }

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

    @Override
    public Payment getPayment(String paymentId) {
        if (paymentId == null || paymentId.isEmpty()) {
            throw new IllegalArgumentException("Payment ID cannot be null or empty");
        }
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment addBankTransferPayment(Order order, String bankAccount) {
        if (!isValidBankAccount(bankAccount)) {
            throw new IllegalArgumentException("Invalid bank account");
        }

        Map<String, String> paymentData = Map.of("bankAccount", bankAccount);
        Payment payment = new Payment(order.getId() + "-payment", "Bank Transfer", "SUCCESS", paymentData);
        paymentRepository.save(payment);
        return payment;
    }

    private boolean isValidBankAccount(String bankAccount) {
        return bankAccount.matches("\\d{9}");
    }

}
