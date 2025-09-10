package com.telran.cars.items.clerk;

import com.telran.cars.items.RentCompanyItem;
import com.telran.cars.model.IRentCompany;
import view.InputOutput;

import java.time.LocalDate;

public class RentCarItem extends RentCompanyItem {
    //private static final String FORMAT = "yyyy-MM-dd";
    private static final int MIN_RENT_DAYS = 1;
    private static final int MAX_RENT_DAYS = 30;
    public RentCarItem(InputOutput inOut, IRentCompany company, String fileName) {
        super(inOut, company, fileName);
    }

    @Override
    public String displayedName() {
        return "Rent car";
    }

    @Override
    public void perform() {
        String regNumber = getRegNumberExisted();
        if (regNumber == null) return;
        Long licenceId = getLicenseIdIfExists();
        if (licenceId == null) return;
        LocalDate rentDate = inOut.inputDate("Enter rent date " + format, format);
        if (rentDate == null) return;
        // check rentDate >= current date!
        Integer rentDays = inOut.inputInteger("Enter rent days %d-%d", MIN_RENT_DAYS, MAX_RENT_DAYS);
        if (rentDays == null) return;
        inOut.outputLine(company.rentCar(regNumber, licenceId, rentDate, rentDays));
        saveResult();
    }
}
