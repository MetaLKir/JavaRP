package com.telran;


import com.telran.cars.items.SaveAndExitItem;
import com.telran.cars.items.clerk.AddDriverItem;
import com.telran.cars.items.clerk.GetCarsByModelItem;
import com.telran.cars.items.clerk.RentCarItem;
import com.telran.cars.items.clerk.ReturnCarItem;
import com.telran.cars.items.driver.GetCarItem;
import com.telran.cars.items.driver.GetCarsByDriverItem;
import com.telran.cars.items.driver.GetDriverItem;
import com.telran.cars.items.driver.GetDriversByCarItem;
import com.telran.cars.items.manager.AddCarItem;
import com.telran.cars.items.manager.AddModelItem;
import com.telran.cars.items.manager.RemoveCarItem;
import com.telran.cars.items.manager.RemoveModelItem;
import com.telran.cars.items.statist.GetMostActiveDriversItem;
import com.telran.cars.items.statist.GetMostPopularModelsItem;
import com.telran.cars.items.statist.GetMostProfitableItem;
import com.telran.cars.items.technician.GetRecordItem;
import com.telran.cars.model.IRentCompany;
import com.telran.cars.model.RentCompanyEmbedded;
import view.*;

public class RentCompanyApplItems {
    static IRentCompany company;
    static InputOutput inOut;
    private static final String FILE_NAME = "company_items.data";

    public static void main(String[] args) {
        inOut = new ConsoleInputOutput();
        company = new RentCompanyEmbedded().restoreFromFile(FILE_NAME);
        Menu menu = new Menu(getMainMenuItems(), inOut);
        menu.runMenu();
    }

    private static Item[] getMainMenuItems() {
        Item[] items = {
                new SubMenuItem("Clerk", inOut, getClerkItems()),
                new SubMenuItem("Driver", inOut, getDriverItems()),
                new SubMenuItem("Manager", inOut, getManagerItems()),
                new SubMenuItem("Statist", inOut, getStatistItems()),
                new SubMenuItem("Technician", inOut, getTechnicianItems()),
                new SaveAndExitItem(inOut, company, FILE_NAME)
        };
        return items;
    }

    private static Item[] getTechnicianItems() {
        Item[] items = {
                new GetRecordItem(inOut, company, FILE_NAME),
                new ExitItem()
        };
        return items;
    }

    private static Item[] getStatistItems() {
        Item[] items = {
                new GetMostActiveDriversItem(inOut, company, FILE_NAME),
                new GetMostPopularModelsItem(inOut, company, FILE_NAME),
                new GetMostProfitableItem(inOut, company, FILE_NAME),
                new ExitItem()
        };
        return items;
    }

    private static Item[] getManagerItems() {
        Item[] items = {
                new AddModelItem(inOut, company, FILE_NAME),
                new AddCarItem(inOut, company, FILE_NAME),
                new RemoveCarItem(inOut, company, FILE_NAME),
                new RemoveModelItem(inOut, company, FILE_NAME),
                new ExitItem()
        };
        return items;
    }

    private static Item[] getDriverItems() {
        Item[] items = {
                new GetCarItem(inOut, company, FILE_NAME),
                new GetDriverItem(inOut, company, FILE_NAME),
                new GetCarsByDriverItem(inOut, company, FILE_NAME),
                new GetDriversByCarItem(inOut, company, FILE_NAME),
                new ExitItem()
        };
        return items;
    }

    private static Item[] getClerkItems() {
        Item[] items = {
                new AddDriverItem(inOut, company, FILE_NAME),
                new GetCarsByModelItem(inOut, company, FILE_NAME),
                new RentCarItem(inOut, company, FILE_NAME),
                new ReturnCarItem(inOut, company, FILE_NAME),
                new ExitItem()
        };
        return items;
    }
}
