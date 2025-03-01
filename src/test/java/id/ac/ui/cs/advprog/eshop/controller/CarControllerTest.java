package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CarControllerTest {

    @Mock
    private CarService carService;

    @Mock
    private Model model;

    @InjectMocks
    private CarController carController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCarPage() {
        String viewName = carController.createCarPage(model);
        assertEquals("CreateCar", viewName);
        verify(model, times(1)).addAttribute(eq("car"), any(Car.class));
    }

    @Test
    void testCreateCarPost() {
        Car car = new Car("someProductId", "someProductName", 10, "Red");
        String result = carController.createCarPost(car);
        assertEquals("redirect:/car/listCar", result);
        verify(carService, times(1)).create(car);
    }

    @Test
    void testCarListPage() {
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("1", "Car1", 5, "Blue"));
        carList.add(new Car("2", "Car2", 3, "Red"));

        when(carService.findAll()).thenReturn(carList);

        String viewName = carController.carListPage(model);
        assertEquals("CarList", viewName);
        verify(model, times(1)).addAttribute("cars", carList);
    }

    @Test
    void testEditCarPage_Found() {
        Car car = new Car("1", "Car1", 5, "Blue");
        when(carService.findById("1")).thenReturn(car);

        String viewName = carController.editCarPage("1", model);
        assertEquals("EditCar", viewName);
        verify(model, times(1)).addAttribute("car", car);
    }

    @Test
    void testEditCarPage_NotFound() {
        when(carService.findById("1")).thenReturn(null);

        String viewName = carController.editCarPage("1", model);
        assertEquals("redirect:/car/listCar", viewName);
    }

    @Test
    void testEditCarPost() {
        Car car = new Car("1", "Car1", 5, "Blue");
        String result = carController.editCarPost(car);
        assertEquals("redirect:/car/listCar", result);
        verify(carService, times(1)).update(car.getProductId(), car);
    }

    @Test
    void testDeleteCar() {
        String result = carController.deleteCar("1");
        assertEquals("redirect:/car/listCar", result);
        verify(carService, times(1)).deleteCarById("1");
    }
}
