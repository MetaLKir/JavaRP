package telran.enums.controller;

import telran.enums.model.Day;
import telran.enums.model.Operation;
import telran.enums.model.Priority;

public class EnumAppl {
    public static void main(String[] args) {
        Day today = Day.MONDAY;
        System.out.println(today);

        switch (today){
            case MONDAY -> System.out.println("יום שני");
            case TUESDAY -> System.out.println("יום שלישי");
            case WEDNESDAY -> System.out.println("יום רביעי");
            case THURSDAY -> System.out.println("יום חמישי");
            case FRIDAY -> System.out.println("יום שישי");
            case SATURDAY -> System.out.println("שבת");
            case SUNDAY -> System.out.println("יום ראשון");

            default -> System.out.println("Another days");
        }

        System.out.println("=".repeat(100));
        Priority task = Priority.MEDIUM;
        System.out.println(task);
        System.out.println(task.getLevel());

        System.out.println("=".repeat(100));
        System.out.println(Priority.values());
        for (int i = 0; i < Priority.values().length; i++) {
            Priority priority =  Priority.values()[i];
            System.out.println(priority.name() + " at " + priority.ordinal());
        }

        System.out.println("=".repeat(100));

        Operation opp1 = Operation.PLUS;
        double res = opp1.apply(3, 4);
        System.out.println(res);

        res = Operation.MINUS.apply(3, 4);
        System.out.println(res);
    }
}
