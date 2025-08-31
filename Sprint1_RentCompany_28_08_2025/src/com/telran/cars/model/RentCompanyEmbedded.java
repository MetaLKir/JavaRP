package com.telran.cars.model;

import com.telran.cars.dto.Car;
import com.telran.cars.dto.Driver;
import com.telran.cars.dto.Model;
import com.telran.cars.dto.enums.CarsReturnCode;
import com.telran.util.Persistable;

import static com.telran.cars.dto.enums.CarsReturnCode.*; // to use enum values directly

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RentCompanyEmbedded extends AbstractRentCompany implements Persistable {
    Map<String, Car> cars = new HashMap<>(); // key = regNumber
    Map<Long, Driver> drivers = new HashMap<>(); // key = driver's licenceID
    Map<String, Model> models = new HashMap<>(); // key = modelName


    @Override
    public CarsReturnCode addModel(Model model) {
        return models.putIfAbsent(model.getModelName(), model) == null ? OK : MODEL_EXISTS;
    }

    @Override
    public Model getModel(String modelName) {
        return models.get(modelName);
    }

    @Override
    public CarsReturnCode addCar(Car car) {
        if (!models.containsKey(car.getModelName())) return NO_MODEL;
        return cars.putIfAbsent(car.getRegNumber(), car) == null ? OK : CAR_EXISTS;
    }

    @Override
    public Car getCar(String regNumber) {
        return cars.get(regNumber);
    }

    @Override
    public CarsReturnCode addDriver(Driver driver) {
        return drivers.putIfAbsent(driver.getLicenseId(), driver) == null ? OK : DRIVER_EXISTS;
    }

    @Override
    public Driver getDriver(long licenseId) {
        return drivers.get(licenseId);
    }

    @Override
    public void save(String fileName) {
        try (ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oOut.writeObject(this);
        } catch (IOException e) {
            System.out.println("Error in method save: " + e.getMessage());
        }
    }

    public static IRentCompany restoreFromFile(String fileName){
        try(ObjectInputStream oInput = new ObjectInputStream(new FileInputStream(fileName))){
            return (IRentCompany) oInput.readObject();
        } catch (Exception e){
            System.out.println(fileName + " new object has been created " + e.getMessage());
            return new RentCompanyEmbedded();
        }
    }
}
