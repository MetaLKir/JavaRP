package com.telran.cars.items;

import com.telran.cars.model.IRentCompany;
import com.telran.util.Persistable;
import view.InputOutput;

public class SaveAndExitItem extends RentCompanyItem {

    public SaveAndExitItem(InputOutput inOut, IRentCompany company, String fileName) {
        super(inOut, company, fileName);
    }


    @Override
    public String displayedName() {
        return "Save and exit";
    }

    @Override
    public void perform() {
        ((Persistable) company).save(fileName);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
