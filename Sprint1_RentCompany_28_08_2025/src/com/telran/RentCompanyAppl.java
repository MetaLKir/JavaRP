package com.telran;

import com.telran.cars.dto.*;
import com.telran.cars.model.IRentCompany;
import com.telran.cars.model.RentCompanyEmbedded;
import com.telran.util.Persistable;

import java.time.LocalDate;
import java.util.List;

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
        final LocalDate RENT_DATE = LocalDate.of(2025, 8, 1);
        final int RENT_DAYS = 3;


        IRentCompany company = new RentCompanyEmbedded();
        Model model1 = new Model(MODEL_NAME, GAS_TANK, COMPANY, COUNTRY, PRICE_PER_DAY);
        Car car1 = new Car(REG_NUMBER, COLOR, MODEL_NAME);
        Driver driver1 = new Driver(LICENSE, NAME, YEAR_OF_BIRTHDAY, PHONE);
        //====================================================================
        // add
        System.out.println("add car: " + company.addCar(car1)); // NO_MODEL
        System.out.println("add model: " + company.addModel(model1)); // OK
        System.out.println("add car: " + company.addCar(car1)); // OK
        System.out.println("add driver: " + company.addDriver(driver1)); // OK

        //====================================================================
        System.out.println("=".repeat(50));
        // get
        System.out.println("get model: " + company.getModel(MODEL_NAME));
        System.out.println("get model: " + company.getModel(NAME));
        System.out.println("get car: " + company.getCar(REG_NUMBER));
        System.out.println("get car: " + company.getCar(PHONE));
        System.out.println("get driver: " + company.getDriver(LICENSE));
        System.out.println("get driver: " + company.getDriver(2L));
        //====================================================================
        // rent car
        System.out.println("rent car: " + company.rentCar(REG_NUMBER, LICENSE, RENT_DATE, RENT_DAYS));
        System.out.println("rent car: " + company.rentCar(REG_NUMBER, LICENSE, RENT_DATE, RENT_DAYS));

        Car car2 = new Car(REG_NUMBER + 1, COLOR, MODEL_NAME);
        Car car3 = new Car(REG_NUMBER + 2, COLOR, MODEL_NAME);
        System.out.println("add car2: " + company.addCar(car2));
        car2.setFlRemoved(true);
        System.out.println("rent car2: " + company.rentCar(REG_NUMBER + 1, LICENSE, RENT_DATE, RENT_DAYS));
        System.out.println("rent car2: " + company.rentCar(REG_NUMBER + 100, LICENSE, RENT_DATE, RENT_DAYS));
        System.out.println("add car3: " + company.addCar(car3));
        System.out.println("rent car3: " + company.rentCar(REG_NUMBER + 2, LICENSE + 1, RENT_DATE, RENT_DAYS));

        //====================================================================
        System.out.println("=".repeat(50));
        // save / restore
        String file = "company1.data";
        ((Persistable) company).save(file);

        IRentCompany restored = RentCompanyEmbedded.restoreFromFile(file);
        System.out.println("restored get model: " + restored.getModel(MODEL_NAME));
        System.out.println("restored get car: " + restored.getCar(REG_NUMBER));
        System.out.println("restored get driver: " + restored.getDriver(LICENSE));

        List<Car> carsOfModel  = restored.getCarsByModel(MODEL_NAME);
        System.out.println("Cars of model: " + carsOfModel);

        List<RentRecord> records = restored.getRentRecordsAtDate(RENT_DATE, RENT_DATE.plusDays(RENT_DAYS));
        System.out.println("records at date: " + records);

        List<Car> carsByDriver = restored.getCarsByDriver(LICENSE);
        System.out.println("cars by driver: " + carsByDriver);

        List<Driver> driversByCar = restored.getDriversByCar(REG_NUMBER);
        System.out.println("drivers by car: " + driversByCar);

        // negative
        List<Car> carsOfModel_N  = restored.getCarsByModel(MODEL_NAME + 1);
        System.out.println("Cars of model (negative): " + carsOfModel_N);

        List<RentRecord> records_N = restored.getRentRecordsAtDate(RENT_DATE.plusDays(RENT_DAYS), RENT_DATE.plusDays(10));
        System.out.println("records at date (negative): " + records_N);

        List<Car> carsByDriver_N = restored.getCarsByDriver(LICENSE + 1000);
        System.out.println("cars by driver (negative): " + carsByDriver_N);

        List<Driver> driversByCar_N = restored.getDriversByCar(REG_NUMBER + 1000);
        System.out.println("drivers by car (negative): " + driversByCar_N);

    }
}
