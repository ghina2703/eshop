package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car extends Product {
    private String carId;
    private String carColor;

    public Car(String productId, String productName, int productQuantity, String carColor, String carId) {
        super(productId, productName, productQuantity);
        this.carColor = carColor;
        this.carId = carId;
    }

    public Car(String productId, String productName, int productQuantity, String carColor) {
        super(productId, productName, productQuantity);
        this.carColor = carColor;
        this.carId = super.getProductId();
    }

    public String getCarId() {
        return this.carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }
}
