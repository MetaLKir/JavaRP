package com.telran.cars.model;

import com.telran.cars.dto.Car;
import com.telran.cars.dto.Driver;
import com.telran.cars.dto.Model;
import com.telran.cars.dto.RentRecord;
import com.telran.cars.dto.enums.CarsReturnCode;
import com.telran.util.Persistable;

import static com.telran.cars.dto.enums.CarsReturnCode.*; // to use enum values directly

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class RentCompanyEmbedded extends AbstractRentCompany implements Persistable {
    // SPRINT 1
    Map<String, Car> cars = new HashMap<>(); // key = regNumber
    Map<Long, Driver> drivers = new HashMap<>(); // key = driver's licenceID
    Map<String, Model> models = new HashMap<>(); // key = modelName
    // SPRINT 2
    Map<String, List<Car>> modelCars = new HashMap<>(); // key = model name -> list cars
    Map<Long, List<RentRecord>> driverRecords = new HashMap<>(); // key = licenseId
    Map<String, List<RentRecord>> carRecords = new HashMap<>(); // key = regNumber
    Map<LocalDate, List<RentRecord>> records = new TreeMap<>(); // key = rentDate

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

        boolean res = cars.putIfAbsent(car.getRegNumber(), car) == null;
        if (!res) return CAR_EXISTS;

        addModelCars(car);
        return OK;
    }

    private void addModelCars(Car car) {
        String modelName = car.getModelName();
        List<Car> listCars = modelCars.getOrDefault(modelName, new ArrayList<>());
        listCars.add(car);
        modelCars.putIfAbsent(modelName, listCars);
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
    public CarsReturnCode rentCar(String regNumber, long licenseId, LocalDate rentDate, int rentDays) {
        Car car = getCar(regNumber);
        if (car == null) return NO_CAR;
        if (car.isFlRemoved()) return CAR_REMOVED;
        if (car.isInUse()) return CAR_IN_USE;
        if (!drivers.containsKey(licenseId)) return NO_DRIVER;
        RentRecord record = new RentRecord(regNumber, licenseId, rentDate, rentDays);
        addToCarRecords(record);
        addToDriverRecords(record);
        addToRecords(record);
        car.setInUse(true);
        return OK;
    }

    private void addToCarRecords(RentRecord record) {
        carRecords.computeIfAbsent(record.getRegNumber(), k -> new ArrayList<>()).add(record);
    }

    private void addToDriverRecords(RentRecord record) {
        driverRecords.computeIfAbsent(record.getLicenseId(), k -> new ArrayList<>()).add(record);
    }

    private void addToRecords(RentRecord record) {
//        LocalDate rentDate = record.getRentDate();
//        List<RentRecord> recordsList = records.getOrDefault(rentDate, new ArrayList<>());
//        recordsList.add(record);
//        records.putIfAbsent(rentDate, recordsList);
        records.computeIfAbsent(record.getRentDate(), k -> new ArrayList<>()).add(record);
    }

    @Override
    public List<Car> getCarsByDriver(long licenseId) {
        List<RentRecord> recordsList = driverRecords.getOrDefault(licenseId, new ArrayList<>());
        return recordsList.stream().
                map(r -> getCar(r.getRegNumber())).
                distinct().
                toList();
    }

    @Override
    public List<Driver> getDriversByCar(String regNumber) {
        List<RentRecord> recordsList = carRecords.getOrDefault(regNumber, new ArrayList<>());
        return recordsList.stream().
                map(r -> getDriver(r.getLicenseId())).
                distinct().
                toList();
    }

    @Override
    public List<Car> getCarsByModel(String modelName) {
        List<Car> res = modelCars.getOrDefault(modelName, new ArrayList<>());
        return res.stream().
                filter(c -> !c.isFlRemoved() && !c.isInUse()).
                toList();
    }

    @Override
    public List<RentRecord> getRentRecordAtDate(LocalDate from, LocalDate to) {
        Collection<List<RentRecord>> res = ((TreeMap<LocalDate, List<RentRecord>>) records).subMap(from, to).values();
        return res.stream().
                flatMap(l -> l.stream()).
                toList();
    }

    @Override
    public void save(String fileName) {
        try (ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oOut.writeObject(this);
        } catch (IOException e) {
            System.out.println("Error in method save: " + e.getMessage());
        }
    }

    public static IRentCompany restoreFromFile(String fileName) {
        try (ObjectInputStream oInput = new ObjectInputStream(new FileInputStream(fileName))) {
            return (IRentCompany) oInput.readObject();
        } catch (Exception e) {
            System.out.println(fileName + " new object has been created " + e.getMessage());
            return new RentCompanyEmbedded();
        }
    }
}
