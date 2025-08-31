package com.telran.cars.model;

import com.telran.cars.dto.*;
import com.telran.cars.dto.enums.CarsReturnCode;

import java.io.Serializable;

public interface IRentCompany extends Serializable {
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

}
