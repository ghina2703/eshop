package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class Product {
    private String productId;
    private String productName = "";
    private int productQuantity = 0;

    public Product() {
        this.productId = UUID.randomUUID().toString();
    }

    public Product(String productId, String productName, int productQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.productQuantity = productQuantity;
    }

}