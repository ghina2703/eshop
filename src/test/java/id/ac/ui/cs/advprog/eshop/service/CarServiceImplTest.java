package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Car createTestCar(String id, String name, String color, int quantity) {
        Car car = new Car();
        car.setCarId(id);
        car.setCarName(name);
        car.setCarColor(color);
        car.setCarQuantity(quantity);
        return car;
    }

    @Test
    void testCreateCar() {
        Car car = createTestCar(null, "Toyota", "Red", 5);
        when(carRepository.create(any(Car.class))).thenReturn(car);
        Car createdCar = carService.create(car);

        assertNotNull(createdCar);
        assertEquals("Toyota", createdCar.getCarName());
        assertEquals("Red", createdCar.getCarColor());
        assertEquals(5, createdCar.getCarQuantity());

        verify(carRepository, times(1)).create(any(Car.class));
    }

    @Test
    void testFindAllCars() {
        // Creating test data
        List<Car> allCars = asList(
                createTestCar("1", "Toyota", "Red", 5),
                createTestCar("2", "Honda", "Blue", 3)
        );

        // Mocking findAll() to return a List instead of an Iterator
        when(carRepository.findAll()).thenReturn(allCars);

        // Calling the method
        List<Car> cars = carService.findAll();

        // Asserting results
        assertNotNull(cars);
        assertEquals(2, cars.size());
        assertEquals("Toyota", cars.get(0).getCarName());
        assertEquals("Honda", cars.get(1).getCarName());

        // Verifying interactions with carRepository
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Car car = createTestCar("1", "Toyota", "Red", 5);
        when(carRepository.findById("1")).thenReturn(car);
        Car foundCar = carService.findById("1");

        assertNotNull(foundCar);
        assertEquals("Toyota", foundCar.getCarName());
        assertEquals(5, foundCar.getCarQuantity());

        verify(carRepository, times(1)).findById("1");
    }

    @Test
    void testFindByIdNotFound() {
        when(carRepository.findById("non-existing-id")).thenReturn(null);
        Car foundCar = carService.findById("non-existing-id");

        assertNull(foundCar);
        verify(carRepository, times(1)).findById("non-existing-id");
    }

    @Test
    void testUpdateCar() {
        Car updatedCar = createTestCar("1", "Updated Toyota", "Green", 10);
        when(carRepository.findById("1")).thenReturn(updatedCar);
        when(carRepository.update(any(String.class), any(Car.class))).thenReturn(updatedCar);
        carService.update("1", updatedCar);

        assertNotNull(updatedCar);
        assertEquals("Updated Toyota", updatedCar.getCarName());
        assertEquals("Green", updatedCar.getCarColor());
        assertEquals(10, updatedCar.getCarQuantity());

        verify(carRepository, times(1)).update(any(String.class), any(Car.class));
    }

    @Test
    void testUpdateCarNotFound() {
        Car updatedCar = createTestCar("non-existing-id", "Updated Car", "Black", 12);
        when(carRepository.findById("non-existing-id")).thenReturn(null);
        Car result = carService.update("non-existing-id", updatedCar);
        assertNull(result);
        verify(carRepository, times(0)).update(any(String.class), any(Car.class));
    }

    @Test
    void testDeleteCar() {
        doNothing().when(carRepository).delete("1");
        carService.deleteCarById("1");

        verify(carRepository, times(1)).delete("1");
    }

    @Test
    void testDeleteCarNotFound() {
        doNothing().when(carRepository).delete("non-existing-id");
        carService.deleteCarById("non-existing-id");
        verify(carRepository, times(1)).delete("non-existing-id");
    }
}
