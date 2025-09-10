package com.telran.cars.items.manager;

import com.telran.cars.items.RentCompanyItem;
import com.telran.cars.model.IRentCompany;
import view.InputOutput;

public class RemoveCarItem extends RentCompanyItem {
    public RemoveCarItem(InputOutput inOut, IRentCompany company, String fileName) {
        super(inOut, company, fileName);
    }

    @Override
    public String displayedName() {
        return "Remove car";
    }

    @Override
    public void perform() {
        String regNumber = getRegNumberExisted();
        if (regNumber == null) return;
        inOut.outputLine(company.removeCar(regNumber));
        saveResult();
    }
}
