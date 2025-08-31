package com.telran;

import com.telran.cars.dto.*;
import com.telran.cars.model.IRentCompany;
import com.telran.cars.model.RentCompanyEmbedded;
import com.telran.util.Persistable;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class RentCompanyAppl {
    public static void main(String[] args) {
        final String MODEL_NAME = "Model1";
        final int GAS_TANK = 50;
        final int PRICE_PER_DAY = 200;
        final long LICENSE = 1000L;
        final String REG_NUMBER = "100";
        final String COMPANY = "Company1";
        final String COUNTRY = "Country1";
        final String COLOR = "color1";
        final String NAME = "name1";
        final int YEAR_OF_BIRTHDAY = 1990;
        final String PHONE = "123456789";

        IRentCompany company = new RentCompanyEmbedded();
        Model model1 = new Model(MODEL_NAME, GAS_TANK, COMPANY, COUNTRY, PRICE_PER_DAY);
        Car car1 = new Car(REG_NUMBER, COLOR, MODEL_NAME);
        Driver driver1 = new Driver(LICENSE, NAME, YEAR_OF_BIRTHDAY, PHONE);

        // add
        System.out.println("add car: " + company.addCar(car1)); // NO_MODEL
        System.out.println("add model: " + company.addModel(model1)); // OK
        System.out.println("add car: " + company.addCar(car1)); // OK
        System.out.println("add driver: " + company.addDriver(driver1)); // OK

        System.out.println("=".repeat(50));
        // get
        System.out.println("get model: " + company.getModel(MODEL_NAME));
        System.out.println("get model: " + company.getModel(NAME));
        System.out.println("get car: " + company.getCar(REG_NUMBER));
        System.out.println("get car: " + company.getCar(PHONE));
        System.out.println("get driver: " + company.getDriver(LICENSE));
        System.out.println("get driver: " + company.getDriver(2L));

        System.out.println("=".repeat(50));
        // save / restore
        String file = "company1.data";
        ((Persistable) company).save(file);

        IRentCompany restored = RentCompanyEmbedded.restoreFromFile(file);
        System.out.println("restored get model: " + restored.getModel(MODEL_NAME));
        System.out.println("restored get car: " + restored.getCar(REG_NUMBER));
        System.out.println("restored get driver: " + restored.getDriver(LICENSE));

    }
}
