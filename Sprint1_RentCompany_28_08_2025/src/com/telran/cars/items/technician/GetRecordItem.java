package com.telran.cars.items.technician;

import com.telran.cars.dto.RentRecord;
import com.telran.cars.items.RentCompanyItem;
import com.telran.cars.model.IRentCompany;
import view.InputOutput;

import java.util.List;

public class GetRecordItem extends RentCompanyItem {
    public GetRecordItem(InputOutput inOut, IRentCompany company, String fileName) {
        super(inOut, company, fileName);
    }

    @Override
    public String displayedName() {
        return "Display records";
    }

    @Override
    public void perform() {
        fillFromToDates();
        if (from == null || to == null) return;
        List<RentRecord> records = company.getRentRecordsAtDate(from, to);
        if(records.isEmpty()){
            inOut.outputLine("No records");
            return;
        }
        records.forEach(inOut::outputLine);
    }
}
