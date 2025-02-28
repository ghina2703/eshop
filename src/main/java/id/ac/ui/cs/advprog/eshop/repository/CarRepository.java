package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import id.ac.ui.cs.advprog.eshop.service.CarIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CarRepository {
    @Autowired
    private final CarService carService;
    private final CarIdGenerator carIdGenerator;

    @Autowired
    public CarRepository(CarService carService, CarIdGenerator carIdGenerator) {
        this.carService = carService;
        this.carIdGenerator = carIdGenerator;
    }

    public Car create(Car car) {
        if (car.getCarId() == null) {
            car.setCarId(carIdGenerator.generateCarId());
        }
        return carService.create(car);
    }

    public List<Car> findAll() {
        return carService.findAll();
    }

    public Car findById(String id) {
        return carService.findById(id);
    }

    public Car update(String id, Car updatedCar) {
        return carService.update(id, updatedCar);
    }

    public void delete(String id) {
        carService.deleteCarById(id);
    }
}