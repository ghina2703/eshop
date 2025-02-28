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
        this.carRepository = carRepository;  // Dependency Injection untuk CarRepository
    }

    @Override
    public Car create(Car car) {
        return carRepository.create(car);  // Sederhana, hanya meneruskan ke repository
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();  // Ambil data dari repository, tidak ada logika tambahan
    }

    @Override
    public Car findById(String carId) {
        return carRepository.findById(carId);  // Ambil data berdasarkan ID
    }

    @Override
    public Car update(String carId, Car car) {
        Car existingCar = carRepository.findById(carId);
        if (existingCar != null) {
            existingCar.setCarName(car.getCarName());
            existingCar.setCarColor(car.getCarColor());
            existingCar.setCarQuantity(car.getCarQuantity());
            return carRepository.update(carId, existingCar);  // Update data
        }
        return null;  // Jika tidak ditemukan
    }

    @Override
    public void deleteCarById(String carId) {
        carRepository.delete(carId);  // Hapus mobil berdasarkan ID
    }
}
