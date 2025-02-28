package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Override
    public Car create(Car car) {
        return carRepository.create(car);
    }

    @Override
    public List<Car> findAll() {
        List<Car> allCar = carRepository.findAll();
        return allCar;
    }

    @Override
    public Car findById(String carId) {
        return carRepository.findById(carId);
    }

    @Override
    public Car update(String carId, Car car) {
        Car existingCar = carRepository.findById(carId);
        if (existingCar != null) {
            existingCar.setCarName(car.getCarName());
            existingCar.setCarColor(car.getCarColor());
            existingCar.setCarQuantity(car.getCarQuantity());
            return carRepository.update(carId, existingCar);
        }
        return null;
    }

    @Override
    public void deleteCarById(String carId) {
        carRepository.delete(carId);
    }
}
