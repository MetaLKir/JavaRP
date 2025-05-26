package telran.computer.controller;

import telran.computer.model.Computer;
import telran.computer.model.Laptop;
import telran.computer.model.Smartphone;

public class ComputerAppl {

    public static void main(String[] args) {
        Computer[] shop = new Computer[3];
        shop[0] = new Computer("i5", 12, 1024, "Pupkin and Co.");
        shop[1] = new Laptop("i7", 16, 1024, "Asus", 4, 2.3);
        shop[2] = new Smartphone("Snapdragon", 8, 256, "Poco", 18, 0.1, 1234567890000L);
        showBrands(shop);
        if (shop[2] instanceof Smartphone){
            Smartphone smart1 = (Smartphone) shop[2];
            System.out.println(smart1.getImei());
        }
        System.out.println("==================================");
        showComputers(shop);
        System.out.println("==================================");
        showHoursOfBattery(shop);
        System.out.println("==================================");
        printArray(shop);
        System.out.println("==================================");
        Computer comp = new Computer("i5", 12, 1024, "Pupkin and Co.");
        System.out.println(shop[0].equals(comp));
    }

    private static void printArray(Computer[] shop) {
        for (int i = 0; i < shop.length; i++) {
            System.out.println(shop[i]);
        }
    }

    private static void showHoursOfBattery(Computer[] shop) {
        for (int i = 0; i < shop.length; i++) {
            if(shop[i] instanceof Laptop laptop){ // casting with assignment
                System.out.println("Brand: " + shop[i].getBrand() +
                        ", Hours: " + laptop.getHours());
            }
        }
    }

    private static void showComputers(Computer[] shop) {
        for(Computer computer : shop){
            computer.display();
            System.out.println();
        }
    }


    private static void showBrands(Computer[] shop) {
        for (Computer computer : shop) {
            System.out.println(computer.getBrand());
        }
    }
}
