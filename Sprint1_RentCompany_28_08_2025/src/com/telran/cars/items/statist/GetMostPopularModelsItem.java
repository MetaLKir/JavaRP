package com.telran.cars.items.statist;

import com.telran.cars.items.RentCompanyItem;
import com.telran.cars.model.IRentCompany;
import view.InputOutput;

import java.time.LocalDate;
import java.util.List;

public class GetMostPopularModelsItem extends RentCompanyItem {
    private static final int MIN_YEAR = LocalDate.now().minusYears(120).getYear();
    private static final int MAX_YEAR = LocalDate.now().minusYears(18).getYear();

    public GetMostPopularModelsItem(InputOutput inOut, IRentCompany company, String fileName) {
        super(inOut, company, fileName);
    }

    @Override
    public String displayedName() {
        return "Display most popular model names";
    }

    @Override
    public void perform() {
        fillFromToDates();
        if (from == null || to == null) return;
        Integer fromAge = inOut.inputInteger("Enter year from range", MIN_YEAR, MAX_YEAR);
        if (fromAge == null) return;
        Integer toAge = inOut.inputInteger("Enter year to range", MIN_YEAR, MAX_YEAR);
        if (toAge == null) return;

        List<String> models = company.getMostPopularCarModels(from, to, fromAge, toAge);
        if (models.isEmpty()) {
            inOut.outputLine("No models");
            return;
        }
        models.forEach(inOut::outputLine);
    }


}
