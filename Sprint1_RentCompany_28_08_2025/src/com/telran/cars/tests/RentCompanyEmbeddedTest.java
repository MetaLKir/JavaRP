package com.telran.cars.tests;

import com.telran.cars.dto.Car;
import com.telran.cars.dto.Driver;
import com.telran.cars.dto.Model;
import com.telran.cars.model.IRentCompany;
import com.telran.cars.model.RentCompanyEmbedded;
import com.telran.util.Persistable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static com.telran.cars.dto.enums.CarsReturnCode.*; // to use enum values directly

class RentCompanyEmbeddedTest {
    final String MODEL_NAME = "Model1";
    final int GAS_TANK = 50;
    final int PRICE_PER_DAY = 200;
    final long LICENSE = 1000L;
    final String REG_NUMBER = "100";
    final String COMPANY = "Company1";
    final String COUNTRY = "Country1";
    final String COLOR = "color1";
    final String DRIVER_NAME = "name1";
    final int YEAR_OF_BIRTHDAY = 1990;
    final String PHONE = "123456789";

    private Model model;
    private Car car;
    private Driver driver;

    private IRentCompany company;

    @BeforeEach
    void setUp() {
        model = new Model(MODEL_NAME, GAS_TANK, COMPANY, COUNTRY, PRICE_PER_DAY);
        car = new Car(REG_NUMBER, COLOR, MODEL_NAME);
        driver = new Driver(LICENSE, DRIVER_NAME, YEAR_OF_BIRTHDAY, PHONE);
        company = new RentCompanyEmbedded();
        ((Persistable) company).save("company2.data");
    }

    @Test
    void add_get_Entities_Positive() {
        assertEquals(OK, company.addModel(model));
        assertEquals(OK, company.addCar(car));
        assertEquals(OK, company.addDriver(driver));

        assertEquals(model, company.getModel(MODEL_NAME));
        assertEquals(car, company.getCar(REG_NUMBER));
        assertEquals(driver, company.getDriver(LICENSE));
    }

    @Test
    void addCar_NO_MODEL(){
        assertEquals(NO_MODEL, company.addCar(car));
        assertNull(company.getCar(REG_NUMBER));
    }

    @Test
    void add_DuplicateEntities(){
        assertEquals(OK, company.addModel(model));
        assertEquals(OK, company.addCar(car));
        assertEquals(OK, company.addDriver(driver));

        assertEquals(MODEL_EXISTS, company.addModel(model));
        assertEquals(CAR_EXISTS, company.addCar(car));
        assertEquals(DRIVER_EXISTS, company.addDriver(driver));
    }

    @Test
    void get_when_not_added(){
        assertNull(company.getCar(REG_NUMBER));
        assertNull(company.getDriver(LICENSE));
        assertNull(company.getModel(MODEL_NAME));
    }

    @Test
    void save_restore_Positive() {
        assertEquals(OK, company.addModel(model));
        assertEquals(OK, company.addCar(car));
        assertEquals(OK, company.addDriver(driver));

        String file = "companyTest.data";
        ((Persistable) company).save(file);

        IRentCompany restored = RentCompanyEmbedded.restoreFromFile(file);
        assertNotNull(restored);

        assertEquals(model, restored.getModel(MODEL_NAME));
        assertEquals(car, restored.getCar(REG_NUMBER));
        assertEquals(driver, restored.getDriver(LICENSE));
    }

    @Test
    void save_restore_Negative() {
        String file = "companyTest_fail.data";
        IRentCompany restored = RentCompanyEmbedded.restoreFromFile(file);
        assertNotNull(restored);
        assertInstanceOf(RentCompanyEmbedded.class, restored);
        assertNull(restored.getModel(MODEL_NAME));
        assertNull(restored.getCar(REG_NUMBER));
        assertNull(restored.getDriver(LICENSE));
    }


}