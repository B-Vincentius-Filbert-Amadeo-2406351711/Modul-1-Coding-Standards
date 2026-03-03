package id.ac.ui.cs.advprog.eshop.service.filter;

import id.ac.ui.cs.advprog.eshop.model.Car;

public class CarNameFilter implements CarFilter {
    private final String carName;

    public CarNameFilter(String carName) {
        this.carName = carName;
    }

    @Override
    public boolean match(Car car) {
        return car.getCarName() != null && car.getCarName().equals(carName);
    }
}
