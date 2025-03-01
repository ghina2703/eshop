package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class CarRepository {
    private final List<Car> carData = new ArrayList<>();

    public Car create(Car car) {
        if (car.getProductId() == null) {
            car.setProductId(generateCarId());
        }
        carData.add(car);
        return car;
    }

    public List<Car> findAll() {
        return new ArrayList<>(carData);
    }

    public Car findById(String id) {
        return carData.stream()
                .filter(car -> car.getProductId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Car update(String id, Car updatedCar) {
        for (Car car : carData) {
            if (car.getProductId().equals(id)) {
                car.setProductName(updatedCar.getProductName());
                car.setCarColor(updatedCar.getCarColor());
                car.setProductQuantity(updatedCar.getProductQuantity());
                return car;
            }
        }
        return null;
    }

    public void delete(String id) {
        carData.removeIf(car -> car.getProductId().equals(id));
    }

    private String generateCarId() {
        return UUID.randomUUID().toString();
    }
}
