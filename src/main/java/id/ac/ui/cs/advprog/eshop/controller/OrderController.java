package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.service.OrderService;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService service;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/create")
    public String createOrderPage(Model model) {
        model.addAttribute("order", new Order());
        return "createOrder";
    }

    @GetMapping("/history")
    public String historyOrderPage() {
        return "orderHistory";
    }

    @PostMapping("/history")
    public String historyOrderPost() {
        return "orderHistory";
    }

    @GetMapping("/pay/{orderId}")
    public String payOrderPage(@PathVariable String orderId, Model model) {
        Order order = service.findById(orderId);
        if (order == null) {
            model.addAttribute("error", "Order not found!");
            return "orderHistory";
        }
        model.addAttribute("order", order);
        return "payOrder";
    }

    @PostMapping("/pay/{orderId}")
    public String payOrder(@PathVariable String orderId, @RequestParam String method, RedirectAttributes redirectAttributes) {
        Order order = service.findById(orderId);
        if (order == null) {
            redirectAttributes.addFlashAttribute("error", "Order not found!");
            return "redirect:/order/history";
        }
        Payment payment = paymentService.addPayment(order, method, Map.of());
        redirectAttributes.addFlashAttribute("paymentId", payment.getId());
        return "redirect:/payment/detail/" + payment.getId();
    }
}
