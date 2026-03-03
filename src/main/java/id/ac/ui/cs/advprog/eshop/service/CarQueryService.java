package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.filter.CarFilter;

import java.util.List;

public interface CarQueryService {
    List<Car> findAll();
    Car findById(String carId);
    List<Car> findByFilter(CarFilter filter);
}
