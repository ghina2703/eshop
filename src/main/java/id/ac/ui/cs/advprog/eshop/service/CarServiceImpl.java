package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car create(Car car) {
        return carRepository.create(car);
    }

    @Override
    public List<Car> findAll() {
        return (List<Car>) carRepository.findAll();
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
