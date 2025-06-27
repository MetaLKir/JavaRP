package telran.cars.dao;

import telran.cars.model.Car;
import java.util.function.Predicate;

public interface Garage {

    boolean addCar(Car car);
    Car removeCar(String regNumber);
    int getGarageSize();
    Car findCarByRegNumber(String regNumber);
    Car[] findCarsByModel(String model);
    Car[] findCarsByCompany(String company);
    Car[] findCarsByEngine(double min, double max);
    Car[] findCarsByColor(String color);
    Car[] findCarsByPredicate(Predicate<Car> predicate);
}
