package id.ac.ui.cs.advprog.eshop.service.filter;

import id.ac.ui.cs.advprog.eshop.model.Car;

public interface CarFilter {
    boolean match(Car car);
}
