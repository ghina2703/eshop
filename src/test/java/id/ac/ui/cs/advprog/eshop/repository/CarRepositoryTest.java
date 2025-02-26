package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

class CarRepositoryTest {

    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        carRepository = new CarRepository();
    }

    @Test
    void testCreateCar() {
        Car carWithoutId = new Car();
        carWithoutId.setCarName("Toyota");
        carWithoutId.setCarColor("Red");
        carWithoutId.setCarQuantity(5);

        Car createdCarWithoutId = carRepository.create(carWithoutId);
        assertNotNull(createdCarWithoutId.getCarId());
        assertEquals("Toyota", createdCarWithoutId.getCarName());
        assertEquals("Red", createdCarWithoutId.getCarColor());
        assertEquals(5, createdCarWithoutId.getCarQuantity());

        Car carWithId = new Car();
        carWithId.setCarId("existing-id");
        carWithId.setCarName("Honda");
        carWithId.setCarColor("Blue");
        carWithId.setCarQuantity(10);

        Car createdCarWithId = carRepository.create(carWithId);
        assertEquals("existing-id", createdCarWithId.getCarId());
        assertEquals("Honda", createdCarWithId.getCarName());
        assertEquals("Blue", createdCarWithId.getCarColor());
        assertEquals(10, createdCarWithId.getCarQuantity());
    }

    @Test
    void testFindAll() {
        Car car1 = new Car();
        car1.setCarName("Toyota");
        car1.setCarColor("Red");
        car1.setCarQuantity(5);
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setCarName("Honda");
        car2.setCarColor("Blue");
        car2.setCarQuantity(3);
        carRepository.create(car2);

        Iterator<Car> iterator = carRepository.findAll();
        assertTrue(iterator.hasNext());
        assertEquals("Toyota", iterator.next().getCarName());
        assertEquals("Honda", iterator.next().getCarName());
    }

    @Test
    void testFindById() {
        Car car = new Car();
        car.setCarName("Toyota");
        car.setCarColor("Red");
        car.setCarQuantity(5);
        carRepository.create(car);

        Car foundCar = carRepository.findById(car.getCarId());
        assertNotNull(foundCar);
        assertEquals("Toyota", foundCar.getCarName());

        Car notFoundCar = carRepository.findById("non-existing-id");
        assertNull(notFoundCar);
    }

    @Test
    void testUpdate() {
        Car car = new Car();
        car.setCarName("Toyota");
        car.setCarColor("Red");
        car.setCarQuantity(5);
        carRepository.create(car);

        car.setCarName("Updated Toyota");
        car.setCarColor("Green");
        car.setCarQuantity(10);

        Car updatedCar = carRepository.update(car.getCarId(), car);

        assertNotNull(updatedCar);
        assertEquals("Updated Toyota", updatedCar.getCarName());
        assertEquals("Green", updatedCar.getCarColor());
        assertEquals(10, updatedCar.getCarQuantity());

        Car nonExistingCar = carRepository.update("non-existing-id", car);
        assertNull(nonExistingCar);
    }

    @Test
    void testDelete() {
        Car car = new Car();
        car.setCarName("Toyota");
        car.setCarColor("Red");
        car.setCarQuantity(5);
        carRepository.create(car);

        carRepository.delete(car.getCarId());
        Car deletedCar = carRepository.findById(car.getCarId());
        assertNull(deletedCar);
    }

    @Test
    void testDeleteNonExistingCar() {
        carRepository.delete("non-existing-id");
    }
}
