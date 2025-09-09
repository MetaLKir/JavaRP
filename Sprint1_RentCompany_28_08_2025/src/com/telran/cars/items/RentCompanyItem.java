package com.telran.cars.items;

import com.telran.cars.model.IRentCompany;
import view.InputOutput;
import view.Item;

public abstract class RentCompanyItem implements Item {
    protected InputOutput inOut;
    protected IRentCompany company;
    protected String fileName;

    public RentCompanyItem(InputOutput inOut, IRentCompany company, String fileName) {
        this.inOut = inOut;
        this.company = company;
        this.fileName = fileName;
    }

    protected String getRegNumberNotExisted() {
        String regNumber = inOut.inputString("Enter registration number");
        if (regNumber == null) return null;
        if (company.getCar(regNumber) != null) {
            inOut.outputLine("Car already exists");
            return null;
        }
        return regNumber;
    }

    protected String getRegNumberExisted() {
        String regNumber = inOut.inputString("Enter registration number");
        if (regNumber == null) return null;
        if (company.getCar(regNumber) == null) {
            inOut.outputLine("Car not found");
            return null;
        }
        return regNumber;
    }

    protected Long getLicenseIdIfNotExists() {
        Long id = inOut.inputLong("Enter license ID");
        if(id == null) return null;
        if(company.getDriver(id) != null){
            inOut.outputLine("Driver already exists");
            return null;
        }
        return id;
    }

    protected Long getLicenseIdIfExists() {
        Long id = inOut.inputLong("Enter license ID");
        if(id == null) return null;
        if(company.getDriver(id) == null){
            inOut.outputLine("Driver not found");
            return null;
        }
        return id;
    }
}
