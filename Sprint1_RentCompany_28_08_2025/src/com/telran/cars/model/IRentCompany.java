package com.telran.cars.model;

import com.telran.cars.dto.*;
import com.telran.cars.dto.enums.CarsReturnCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public interface IRentCompany extends Serializable {
    // SPRINT 1
    int getGasPrice();
    void setGasPrice(int price);
    int getFinePercent();
    void setFinePercent(int finePercent);
    CarsReturnCode addModel(Model model);
    Model getModel(String modelName);
    CarsReturnCode addCar(Car car);
    Car getCar(String regNumber);
    CarsReturnCode addDriver(Driver driver);
    Driver getDriver(long licenseId);
    // SPRINT 2
    CarsReturnCode rentCar(String regNumber, long licenseId, LocalDate rentDate, int rentDays);
    List<Car> getCarsByDriver(long licenseId);
    List<Driver> getDriversByCar(String regNumber);
    List<Car> getCarsByModel(String modelName);
    List<RentRecord> getRentRecordAtDate(LocalDate from, LocalDate to);

}
