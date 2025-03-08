package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class Order {
    private String id;
    private List<Product> products;
    private Long orderTime;
    private String author;
    private String status;
    private String orderName;

    public Order(String id, List<Product> products, Long orderTime, String author) {
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("Products list cannot be empty!");
        }
        this.id = id;
        this.products = products;
        this.orderTime = orderTime;
        this.author = author;
        this.status = OrderStatus.WAITING_PAYMENT.getValue();
    }

    public Order(String id, List<Product> products, Long orderTime, String author, String status) {
        this(id, products, orderTime, author);
        this.setStatus(status);
    }

    public void setStatus(String status) {
        if (!OrderStatus.contains(status)) {
            throw new IllegalArgumentException("Invalid order status: " + status);
        }
        this.status = status;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderName() {
        return orderName;
    }

}
