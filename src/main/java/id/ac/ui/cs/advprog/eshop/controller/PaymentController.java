package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/detail")
    public String getPaymentDetailForm() {
        return "payment/payment_detail_form";
    }

    @GetMapping("/detail/{paymentId}")
    public String getPaymentDetail(@PathVariable String paymentId, Model model) {
        model.addAttribute("payment", paymentService.getPayment(paymentId));
        return "payment/payment_detail";
    }

    @GetMapping("/admin/list")
    public String getAllPayments(Model model) {
        model.addAttribute("payments", paymentService.getAllPayments());
        return "payment/admin_payment_list";
    }

    @GetMapping("/admin/detail/{paymentId}")
    public String getAdminPaymentDetail(@PathVariable String paymentId, Model model) {
        model.addAttribute("payment", paymentService.getPayment(paymentId));
        return "payment/admin_payment_detail";
    }

    @PostMapping("/admin/set-status/{paymentId}")
    public String setPaymentStatus(@PathVariable String paymentId, @RequestParam String status) {
        Payment payment = paymentService.getPayment(paymentId);

        if (payment != null && (status.equals("SUCCESS") || status.equals("REJECTED"))) {
            paymentService.setStatus(payment, status);
        }

        return "redirect:/payment/admin/list";
    }
}
