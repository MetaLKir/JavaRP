package telran.employee.controller;

import telran.employee.model.Employee;
import telran.employee.model.Manager;
import telran.employee.model.SalesManager;
import telran.employee.model.WageEmployee;

public class EmployeeAppl {
    public static void main(String[] args) {
        Employee[] firm = new Employee[5];
        firm[0] = new Manager(1000, "John", "Smith", 180, 20_000, 20);
        firm[1] = new WageEmployee(2000, "Mary", "Smith", 180, 50);
        firm[2] = new SalesManager(3000, "Peter", "Jackson", 180, 100_000, 0.05);
        firm[3] = new SalesManager(4000, "Tigran", "Petrosian", 90, 500_000, 0.1);
//        firm[4] = new Employee();
        printArray(firm);
        double total = totalSalary(firm);
        System.out.println("Total salary: " + total);
    }

    private static double totalSalary(Employee[] firm) {
        double sum = 0;
        for (int i = 0; i < firm.length; i++) {
            if (firm[i] != null) {
                sum += firm[i].calcSalary();
            }
        }
        return sum;
    }

    private static void printArray(Employee[] firm) {
        for (int i = 0; i < firm.length; i++) {
            if (firm[i] != null) {
                System.out.println(firm[i]);
            }
        }
    }
}
