package com.telran.cars.tests;

import com.telran.cars.dto.*;
import com.telran.cars.model.IRentCompany;
import com.telran.cars.model.RentCompanyEmbedded;
import com.telran.util.Persistable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    final LocalDate RENT_DATE = LocalDate.of(2025, 8, 1);
    final int RENT_DAYS = 3;

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
    void addCar_NO_MODEL() {
        assertEquals(NO_MODEL, company.addCar(car));
        assertNull(company.getCar(REG_NUMBER));
    }

    @Test
    void add_DuplicateEntities() {
        assertEquals(OK, company.addModel(model));
        assertEquals(OK, company.addCar(car));
        assertEquals(OK, company.addDriver(driver));

        assertEquals(MODEL_EXISTS, company.addModel(model));
        assertEquals(CAR_EXISTS, company.addCar(car));
        assertEquals(DRIVER_EXISTS, company.addDriver(driver));
    }

    @Test
    void get_when_not_added() {
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

    // SPRINT 2 =================================================================
    @Test
    void RentCar_Positive() {
        assertEquals(OK, company.addModel(model));
        assertEquals(OK, company.addCar(car));
        assertEquals(OK, company.addDriver(driver));

        assertEquals(OK, company.rentCar(REG_NUMBER, LICENSE, RENT_DATE, RENT_DAYS));
    }

    @Test
    void rentCar_NoCar() {
        assertEquals(OK, company.addModel(model));
//        assertEquals(OK, company.addCar(car));
        assertEquals(OK, company.addDriver(driver));

        assertEquals(NO_CAR, company.rentCar(REG_NUMBER, LICENSE, RENT_DATE, RENT_DAYS));
    }

    @Test
    void rentCar_NoDriver() {
        assertEquals(OK, company.addModel(model));
        assertEquals(OK, company.addCar(car));
//        assertEquals(OK, company.addDriver(driver));

        assertEquals(NO_DRIVER, company.rentCar(REG_NUMBER, LICENSE, RENT_DATE, RENT_DAYS));
    }

    @Test
    void rentCar_flRemove_InUse() {
        assertEquals(OK, company.addModel(model));
        assertEquals(OK, company.addCar(car));
        assertEquals(OK, company.addDriver(driver));

        car.setFlRemoved(true);
        assertEquals(CAR_REMOVED, company.rentCar(REG_NUMBER, LICENSE, RENT_DATE, RENT_DAYS));

        car.setFlRemoved(false);
        car.setInUse(true);
        assertEquals(CAR_IN_USE, company.rentCar(REG_NUMBER, LICENSE, RENT_DATE, RENT_DAYS));
    }

    @Test
    void rentRecords() {
        assertEquals(OK, company.addModel(model));
        assertEquals(OK, company.addCar(car));
        assertEquals(OK, company.addDriver(driver));

        RentRecord expected = new RentRecord(REG_NUMBER, LICENSE, RENT_DATE, RENT_DAYS);
        company.rentCar(REG_NUMBER, LICENSE, RENT_DATE, RENT_DAYS);

        List<RentRecord> res = company.getRentRecordAtDate(RENT_DATE, RENT_DATE.plusDays(RENT_DAYS));
        assertEquals(1, res.size());
        assertEquals(expected, res.get(0));

        List<RentRecord> res1 = company.getRentRecordAtDate(RENT_DATE.plusDays(RENT_DAYS), RENT_DATE.plusDays(10));
        assertTrue(res1.isEmpty());
    }

    @Test
    void carsByDriver() {
        assertEquals(OK, company.addModel(model));
        assertEquals(OK, company.addCar(car));
        assertEquals(OK, company.addDriver(driver));
        company.rentCar(REG_NUMBER, LICENSE, RENT_DATE, RENT_DAYS);

        List<Car> res = company.getCarsByDriver(LICENSE);
        assertEquals(1, res.size());
        assertEquals(car, res.get(0));

        res = company.getCarsByDriver(LICENSE + 1);
        assertTrue(res.isEmpty());
    }

    @Test
    void driversByCar() {
        assertEquals(OK, company.addModel(model));
        assertEquals(OK, company.addCar(car));
        assertEquals(OK, company.addDriver(driver));
        company.rentCar(REG_NUMBER, LICENSE, RENT_DATE, RENT_DAYS);

        List<Driver> res = company.getDriversByCar(REG_NUMBER);
        assertEquals(1, res.size());
        assertEquals(driver, res.get(0));

        res = company.getDriversByCar(REG_NUMBER + 1);
        assertTrue(res.isEmpty());
    }

    @Test
    void carsByModel() {
        assertEquals(OK, company.addModel(model));
        assertEquals(OK, company.addCar(car));
        assertEquals(OK, company.addDriver(driver));

        List<Car> res = company.getCarsByModel(MODEL_NAME);
        assertEquals(1, res.size());
        assertEquals(car, res.get(0));

        Car car1 = new Car(REG_NUMBER + 1, COLOR + "R", MODEL_NAME);
        assertEquals(OK, company.addCar(car1));

        car1.setInUse(true);
        res = company.getCarsByModel(MODEL_NAME);
        assertEquals(1, res.size());
        assertEquals(car, res.get(0));

        car1.setInUse(false);
        car1.setFlRemoved(true);
        res = company.getCarsByModel(MODEL_NAME);
        assertEquals(1, res.size());
        assertEquals(car, res.get(0));

        res = company.getCarsByModel(MODEL_NAME + 11);
        assertTrue(res.isEmpty());
    }
    // removeCar TEST ======================================================
    @Test
    void removeCarsInUse() {
        assertEquals(OK, company.addModel(model));
        assertEquals(OK, company.addCar(car));
        assertEquals(OK, company.addDriver(driver));
        company.rentCar(REG_NUMBER, LICENSE, RENT_DATE, RENT_DAYS);

        RemovedCarData nC = new RemovedCarData(car, null);
        assertEquals(nC, company.removeCar(REG_NUMBER));

        assertNull(company.removeCar(REG_NUMBER + 100));

        car.setFlRemoved(true);
        RemovedCarData nC1 = new RemovedCarData(car, new ArrayList<>());
        assertNull(company.removeCar(REG_NUMBER));
    }

    @Test
    void removeCarsNotInUse() {
        assertEquals(OK, company.addModel(model));
        assertEquals(OK, company.addCar(car));
        assertEquals(OK, company.addDriver(driver));

        RemovedCarData nC = new RemovedCarData(car, new ArrayList<>());
        assertEquals(nC, company.removeCar(REG_NUMBER));
    }

    // returnCar TEST ====================================================================
    @Test
    void returnCar_wrongCar() {
        company.addModel(model);
        company.addCar(car);
        company.addDriver(driver);
        company.rentCar(REG_NUMBER, LICENSE, RENT_DATE, RENT_DAYS);

        Car car1 = new Car(REG_NUMBER + 1, COLOR + "R", MODEL_NAME);
        RemovedCarData expected = new RemovedCarData(null, null);
        RemovedCarData actual = company.returnCar(car1.getRegNumber(), LICENSE, RENT_DATE.plusDays(RENT_DAYS), 0, 100);
        assertEquals(expected, actual);
    }

    @Test
    void returnCar_wrongDriver() {
        company.addModel(model);
        company.addCar(car);
        company.addDriver(driver);
        company.rentCar(REG_NUMBER, LICENSE, RENT_DATE, RENT_DAYS);

        RemovedCarData expected = new RemovedCarData(null, null);
        RemovedCarData actual = company.returnCar(car.getRegNumber(), LICENSE + 1, RENT_DATE.plusDays(RENT_DAYS), 0, 100);
        assertEquals(expected, actual);
    }

    @Test
    void returnCar_Positive() {
        company.addModel(model);
        company.addCar(car);
        company.addDriver(driver);
        company.rentCar(REG_NUMBER, LICENSE, RENT_DATE, RENT_DAYS);

        RemovedCarData expected = new RemovedCarData(car, null);
        RemovedCarData actual = company.returnCar(car.getRegNumber(), LICENSE, RENT_DATE.plusDays(RENT_DAYS), 0, 100);
        assertEquals(expected, actual);
    }

    @Test
    void returnCar_CarToRemove() { // max damage
        company.addModel(model);
        company.addCar(car);
        company.addDriver(driver);
        company.rentCar(REG_NUMBER, LICENSE, RENT_DATE, RENT_DAYS);

        List<RentRecord> listRentRecordsForExpected = company.getRentRecordAtDate(RENT_DATE, RENT_DATE.plusDays(RENT_DAYS));
        RemovedCarData expected = new RemovedCarData(car,listRentRecordsForExpected);
        RemovedCarData actual = company.returnCar(car.getRegNumber(), LICENSE, RENT_DATE.plusDays(RENT_DAYS), 100, 100);
        assertEquals(expected, actual);
    }
    // removeModel TEST ====================================================================
    @Test
    void removeModel_CarNeverRented(){
        company.addModel(model);
        company.addCar(car);
        company.addDriver(driver);
        RemovedCarData rcd = new RemovedCarData(car, new ArrayList<>());
        List<RemovedCarData> expected = new ArrayList<>();
        expected.add(rcd);
        assertEquals(expected, company.removeModel(MODEL_NAME));
    }

    @Test
    void removeModel_carInUse(){
        company.addModel(model);
        company.addCar(car);
        company.addDriver(driver);
        company.rentCar(REG_NUMBER, LICENSE, RENT_DATE, RENT_DAYS);
        RemovedCarData rcd = new RemovedCarData(car, null);
        List<RemovedCarData> expected = new ArrayList<>();
        expected.add(rcd);
        assertEquals(expected, company.removeModel(MODEL_NAME));
    }

    @Test
    void removeModel_noCars(){
        company.addModel(model);
        company.addDriver(driver);
        List<RemovedCarData> expected = new ArrayList<>();
        assertEquals(expected, company.removeModel(MODEL_NAME));
    }
}