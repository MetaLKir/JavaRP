package telran.cars.dao;

import telran.cars.model.Car;
import java.util.ArrayList;
import java.util.function.Predicate;

public class GarageImpl implements Garage {
    private final Car[] cars;
    private int size;

    public GarageImpl(int capacity) {
        this.cars = new Car[capacity];
    }

    @Override
    public boolean addCar(Car car) {
        if (car == null || size == cars.length || findCarByRegNumber(car.getRegNumber()) != null)
            return false;

        cars[size++] = car;
        return true;
    }

    @Override
    public Car removeCar(String regNumber) {
        for (int i = 0; i < size; i++) {
            if(!cars[i].getRegNumber().equals(regNumber)) continue;

            Car carToRemove = cars[i];
            cars[i] = cars[size - 1];
            cars[size - 1] = null;
            size--;
            return carToRemove;
        }
        return null;
    }

    @Override
    public int getGarageSize() {
        return size;
    }

    @Override
    public Car findCarByRegNumber(String regNumber) {
        for (int i = 0; i < size; i++) {
            if(cars[i].getRegNumber().equals(regNumber))
                return cars[i];
        }
        return null;
    }

    @Override
    public Car[] findCarsByModel(String model) {
        Predicate<Car> predicate = new Predicate<Car>() {
            @Override
            public boolean test(Car car) {
                return car.getModel().equals(model);
            }
        };
        return findCarsByPredicate(predicate);
    }

    @Override
    public Car[] findCarsByCompany(String company) {
        return findCarsByPredicate(c -> c.getCompany().equals(company));
    }

    @Override
    public Car[] findCarsByEngine(double min, double max) {
        return findCarsByPredicate(c -> c.getEngine() >= min && c.getEngine() <= max);
    }

    @Override
    public Car[] findCarsByColor(String color) {
        return findCarsByPredicate(c -> c.getColor().equals(color));
    }

    @Override
    public Car[] findCarsByPredicate(Predicate<Car> predicate){
        ArrayList<Car> carsToReturn = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (predicate.test(cars[i]))
                carsToReturn.add(cars[i]);
        }

        return carsToReturn.toArray(new Car[0]);
    }
}
