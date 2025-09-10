package com.telran.cars.items.driver;

import com.telran.cars.dto.Car;
import com.telran.cars.items.RentCompanyItem;
import com.telran.cars.model.IRentCompany;
import view.InputOutput;

import java.util.List;

public class GetCarsByDriverItem extends RentCompanyItem {

    public GetCarsByDriverItem(InputOutput inOut, IRentCompany company, String fileName) {
        super(inOut, company, fileName);
    }

    @Override
    public String displayedName() {
        return "Display cars data driving a given driver";
    }

    @Override
    public void perform() {
        Long licenceId = getLicenseIdIfExists();
        if (licenceId == null) return;
        List<Car> cars = company.getCarsByDriver(licenceId);
        if (cars.isEmpty()){
            inOut.outputLine("No cars for " + licenceId);
            return;
        }
        cars.forEach(inOut::outputLine);
    }
}
