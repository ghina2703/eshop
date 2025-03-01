package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarRepositoryTest {
    private CarRepository carRepository;
    private Car car1;
    private Car car2;

    @BeforeEach
    void setUp() {
        carRepository = new CarRepository();
        car1 = new Car(null, "Car A", 5, "Red");
        car2 = new Car("2", "Car B", 3, "Blue");

        carRepository.create(car1);
        carRepository.create(car2);
    }

    @Test
    void testCreateWithGeneratedId() {
        Car car3 = new Car(null, "Car C", 7, "Black");
        Car createdCar = carRepository.create(car3);

        assertNotNull(createdCar);
        assertNotNull(createdCar.getProductId());
        assertEquals("Car C", createdCar.getProductName());
        assertEquals(3, carRepository.findAll().size());
    }

    @Test
    void testGenerateCarId() {
        String generatedId1 = carRepository.create(new Car(null, "Car D", 2, "Green")).getProductId();
        String generatedId2 = carRepository.create(new Car(null, "Car E", 1, "Yellow")).getProductId();

        assertNotNull(generatedId1);
        assertNotNull(generatedId2);
        assertNotEquals(generatedId1, generatedId2);
    }

    @Test
    void testFindAll() {
        List<Car> allCars = carRepository.findAll();
        assertEquals(2, allCars.size());
    }

    @Test
    void testFindByIdExists() {
        Car foundCar = carRepository.findById(car2.getProductId());
        assertNotNull(foundCar);
        assertEquals("Car B", foundCar.getProductName());
    }

    @Test
    void testFindByIdNotExists() {
        Car foundCar = carRepository.findById("99");
        assertNull(foundCar);
    }

    @Test
    void testUpdateExistingCar() {
        String existingCarId = car1.getProductId();
        assertNotNull(existingCarId, "Car ID should not be null");
        assertNotNull(carRepository.findById(existingCarId), "Car should exist before update");

        Car updatedCar = new Car(existingCarId, "Updated Car A", 10, "Green");
        Car result = carRepository.update(existingCarId, updatedCar);
        assertNotNull(result, "Updated car should not be null");
        assertEquals("Updated Car A", result.getProductName());
        assertEquals(10, result.getProductQuantity());
        assertEquals("Green", result.getCarColor());
    }

    @Test
    void testUpdateNonExistingCar() {
        Car updatedCar = new Car("99", "Non-Existent Car", 10, "Yellow");
        Car result = carRepository.update("99", updatedCar);
        assertNull(result);
    }

    @Test
    void testDeleteExistingCar() {
        carRepository.delete(car1.getProductId());
        assertNull(carRepository.findById(car1.getProductId()));
        assertEquals(1, carRepository.findAll().size());
    }

    @Test
    void testDeleteNonExistingCar() {
        carRepository.delete("99");
        assertEquals(2, carRepository.findAll().size());
    }
}
