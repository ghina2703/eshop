package id.ac.ui.cs.advprog.eshop.service;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CarIdGenerator {
    public String generateCarId() {
        return UUID.randomUUID().toString();
    }
}