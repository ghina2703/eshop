package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void testCreateCar() {
        Car car = new Car("1", "CarName", 10, "Red");
        when(carRepository.create(car)).thenReturn(car);

        Car result = carService.create(car);

        assertNotNull(result);
        assertEquals("CarName", result.getProductName());
        verify(carRepository, times(1)).create(car);
    }

    @Test
    void testFindAllCars() {
        List<Car> cars = Arrays.asList(
                new Car("1", "CarOne", 5, "Blue"),
                new Car("2", "CarTwo", 3, "Red")
        );

        when(carRepository.findAll()).thenReturn(cars);

        List<Car> result = carService.findAll();

        assertEquals(2, result.size());
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void testFindCarById_Found() {
        Car car = new Car("1", "CarOne", 5, "Blue");
        when(carRepository.findById("1")).thenReturn(car);

        Car result = carService.findById("1");

        assertNotNull(result);
        assertEquals("CarOne", result.getProductName());
        verify(carRepository, times(1)).findById("1");
    }

    @Test
    void testFindCarById_NotFound() {
        when(carRepository.findById("1")).thenReturn(null);

        Car result = carService.findById("1");

        assertNull(result);
        verify(carRepository, times(1)).findById("1");
    }

    @Test
    void testUpdateCar_Found() {
        Car updatedCar = new Car("1", "UpdatedCar", 7, "Green");
        when(carRepository.update("1", updatedCar)).thenReturn(updatedCar);

        Car result = carService.update("1", updatedCar);

        assertNotNull(result);
        assertEquals("UpdatedCar", result.getProductName());
        assertEquals("Green", result.getCarColor());
        verify(carRepository, times(1)).update("1", updatedCar);
    }

    @Test
    void testUpdateCar_NotFound() {
        Car updatedCar = new Car("1", "UpdatedCar", 7, "Green");
        when(carRepository.update("1", updatedCar)).thenReturn(null);

        Car result = carService.update("1", updatedCar);

        assertNull(result);
        verify(carRepository, times(1)).update("1", updatedCar);
    }

    @Test
    void testDeleteCar() {
        doNothing().when(carRepository).delete("1");

        carService.deleteCarById("1");

        verify(carRepository, times(1)).delete("1");
    }
}
