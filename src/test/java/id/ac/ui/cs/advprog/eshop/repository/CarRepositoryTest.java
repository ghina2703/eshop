package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import id.ac.ui.cs.advprog.eshop.service.CarIdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CarRepositoryTest {

    @Mock
    private CarService carService;

    @Mock
    private CarIdGenerator carIdGenerator;

    @InjectMocks
    private CarRepository carRepository;

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
    void testCreateCar_WithGeneratedId() {
        Car car = createTestCar(null, "Toyota", "Red", 5);
        when(carIdGenerator.generateCarId()).thenReturn("generated-id");
        when(carService.create(any(Car.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Car createdCar = carRepository.create(car);

        assertNotNull(createdCar);
        assertEquals("generated-id", createdCar.getCarId());
        assertEquals("Toyota", createdCar.getCarName());
        assertEquals("Red", createdCar.getCarColor());
        assertEquals(5, createdCar.getCarQuantity());

        verify(carIdGenerator, times(1)).generateCarId();
        verify(carService, times(1)).create(any(Car.class));
    }

    @Test
    void testCreateCar_WithExistingId() {
        Car car = createTestCar("existing-id", "Honda", "Blue", 10);
        when(carService.create(any(Car.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Car createdCar = carRepository.create(car);

        assertNotNull(createdCar);
        assertEquals("existing-id", createdCar.getCarId());
        assertEquals("Honda", createdCar.getCarName());
        assertEquals("Blue", createdCar.getCarColor());
        assertEquals(10, createdCar.getCarQuantity());

        verify(carIdGenerator, never()).generateCarId();
        verify(carService, times(1)).create(any(Car.class));
    }

    @Test
    void testFindAll() {
        List<Car> allCars = Arrays.asList(
                createTestCar("1", "Toyota", "Red", 5),
                createTestCar("2", "Honda", "Blue", 3)
        );
        when(carService.findAll()).thenReturn(allCars);

        List<Car> cars = carRepository.findAll();

        assertNotNull(cars);
        assertEquals(2, cars.size());
        assertEquals("Toyota", cars.get(0).getCarName());
        assertEquals("Honda", cars.get(1).getCarName());

        verify(carService, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Car car = createTestCar("1", "Toyota", "Red", 5);
        when(carService.findById("1")).thenReturn(car);

        Car foundCar = carRepository.findById("1");

        assertNotNull(foundCar);
        assertEquals("Toyota", foundCar.getCarName());
        assertEquals(5, foundCar.getCarQuantity());

        verify(carService, times(1)).findById("1");
    }

    @Test
    void testFindById_NotFound() {
        when(carService.findById("non-existing-id")).thenReturn(null);

        Car foundCar = carRepository.findById("non-existing-id");

        assertNull(foundCar);
        verify(carService, times(1)).findById("non-existing-id");
    }

    @Test
    void testUpdateCar() {
        Car updatedCar = createTestCar("1", "Updated Toyota", "Green", 10);
        when(carService.update(any(String.class), any(Car.class))).thenReturn(updatedCar);

        Car result = carRepository.update("1", updatedCar);

        assertNotNull(result);
        assertEquals("Updated Toyota", result.getCarName());
        assertEquals("Green", result.getCarColor());
        assertEquals(10, result.getCarQuantity());

        verify(carService, times(1)).update(any(String.class), any(Car.class));
    }

    @Test
    void testUpdateCar_NotFound() {
        Car updatedCar = createTestCar("non-existing-id", "Updated Car", "Black", 12);
        when(carService.update(any(String.class), any(Car.class))).thenReturn(null);

        Car result = carRepository.update("non-existing-id", updatedCar);

        assertNull(result);
        verify(carService, times(1)).update(any(String.class), any(Car.class));
    }

    @Test
    void testDeleteCar() {
        doNothing().when(carService).deleteCarById("1");

        carRepository.delete("1");

        verify(carService, times(1)).deleteCarById("1");
    }

    @Test
    void testDeleteCar_NotFound() {
        doNothing().when(carService).deleteCarById("non-existing-id");

        carRepository.delete("non-existing-id");

        verify(carService, times(1)).deleteCarById("non-existing-id");
    }
}
