package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car extends Product {
    private String carColor;

    public Car(String productId, String productName, int productQuantity, String carColor) {
        super(productId, productName, productQuantity);
        this.carColor = carColor;
    }

}
