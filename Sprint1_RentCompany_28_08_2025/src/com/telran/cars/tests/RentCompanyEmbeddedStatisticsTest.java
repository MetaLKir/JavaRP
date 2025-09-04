package com.telran.cars.tests;

import com.telran.cars.dto.Car;
import com.telran.cars.dto.Driver;
import com.telran.cars.dto.Model;
import com.telran.cars.model.IRentCompany;
import com.telran.cars.model.RentCompanyEmbedded;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RentCompanyEmbeddedStatisticsTest {
    private static final String MODEL = "model";
    private static final String CAR = "car";
    IRentCompany company = new RentCompanyEmbedded();
    int[] prices = {100, 100, 100, 100, 10_000};
    int[] years = {2000, 1995, 1950, 1948};
    LocalDate fromDate = LocalDate.of(1900, 1, 1);
    LocalDate toDate = LocalDate.of(2300, 1, 1);
    LocalDate rentDate = LocalDate.of(2020, 1, 1);

    @BeforeEach
    void setUp() {
        createModels();
        createCars();
        createDrivers();
        rentReturns();
        company.setFinePercent(8);
        company.setGasPrice(13);
    }


    // methods for setUp ============================================
    private void createModels() {
        for (int i = 0; i < prices.length; i++) {
            company.addModel(new Model(MODEL + i, 50, "company" + i, "country" + 1, prices[i]));
        }
    }

    private void createCars() {
        for (int i = 0; i < prices.length; i++) {
            company.addCar(new Car(CAR + i, "color" + i, MODEL + i));
        }
    }

    private void createDrivers() {
        for (int licenseId = 0; licenseId < years.length; licenseId++) {
            company.addDriver(new Driver(licenseId, "name" + licenseId, years[licenseId], "phone" + licenseId));
        }
    }

    private void rentReturns() {
        int[] licenseIds = {0, 0, 1, 1, 2, 2, 3, 3, 0};
        String[] regNumbers = {
                CAR + 0,
                CAR + 1,
                CAR + 0,
                CAR + 1,
                CAR + 2,
                CAR + 3,
                CAR + 2,
                CAR + 3,
                CAR + 4};
        int rentDays = 5;
        LocalDate cursor = rentDate;
        for (int i = 0; i < regNumbers.length; i++) {
            company.rentCar(regNumbers[i], licenseIds[i], cursor, rentDays);
            company.returnCar(regNumbers[i], licenseIds[i], cursor.plusDays(rentDays), 0, 100);
            cursor = cursor.plusDays(rentDays + 1);
        }

    }

    // TESTS =========================================================
    @Test
    void getMostPopularCarModels() {
        int ageYoungFrom = rentDate.getYear() - years[0];
        int ageYoungTo = rentDate.getYear() - years[1] + 1;
        int ageOldFrom = rentDate.getYear() - years[2];
        int ageOldTo = rentDate.getYear() - years[3] + 1;

        List<String> youngModels = company.getMostPopularCarModels(fromDate, toDate, ageYoungFrom, ageYoungTo);
        assertEquals(2, youngModels.size());
        assertTrue(youngModels.contains(MODEL + 0));
        assertTrue(youngModels.contains(MODEL + 1));

        List<String> oldModels = company.getMostPopularCarModels(fromDate, toDate, ageOldFrom, ageOldTo);
        assertEquals(2, oldModels.size());
        assertTrue(oldModels.contains(MODEL + 2));
        assertTrue(oldModels.contains(MODEL + 3));
    }
    @Test
    void getMostPopularCarModels_Negative() {
        int ageYoungFrom = rentDate.getYear() - years[0];
        int ageYoungTo = rentDate.getYear() - years[1] + 1;
        int ageOldFrom = rentDate.getYear() - years[2];
        int ageOldTo = rentDate.getYear() - years[3] + 1;

        List<String> negative1 = company.getMostPopularCarModels(fromDate, fromDate.plusYears(50), ageYoungFrom, ageYoungTo);
        assertTrue(negative1.isEmpty());

        List<String> negative2 = company.getMostPopularCarModels(fromDate, toDate, 40, 50);
        assertTrue(negative2.isEmpty());

    }

    @Test
    void getMostProfitableCarModels() {
        List<String> profitableM = company.getMostProfitableCarModels(fromDate, toDate);
        assertEquals(1, profitableM.size());
        assertEquals(MODEL + 4, profitableM.getFirst());
    }

    @Test
    void getMostActiveDrivers() {
        List<Driver> activeDrivers = company.getMostActiveDrivers();
        assertEquals(1, activeDrivers.size());
        assertEquals(0L, activeDrivers.getFirst().getLicenseId());
    }

    @Test
    void getMostActiveDrivers1() {
        company.removeCar(CAR + 4);
        List<Driver> activeDrivers = company.getMostActiveDrivers();
        assertEquals(4, activeDrivers.size());
        assertTrue(activeDrivers.contains(company.getDriver(0)));
        assertTrue(activeDrivers.contains(company.getDriver(1)));
        assertTrue(activeDrivers.contains(company.getDriver(2)));
        assertTrue(activeDrivers.contains(company.getDriver(3)));

        List<String> youngModels = company.getMostPopularCarModels(fromDate, toDate, 0, 120);
        assertEquals(4, youngModels.size());
        assertTrue(youngModels.contains(MODEL+0));
        assertTrue(youngModels.contains(MODEL+1));
        assertTrue(youngModels.contains(MODEL+2));
        assertTrue(youngModels.contains(MODEL+3));
    }
}