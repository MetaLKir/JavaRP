package employee.controller;

import employee.model.Employee;

public class EmployeeAppl {
    public static void main(String[] args) {
        Employee empl1 = new Employee(1000, "Peter", 10_000, 2.25,"Male");
        empl1.display();
        System.out.println(empl1.calcTaxIsrael());
        System.out.println(Employee.salaryNetto(empl1.getSalary(), empl1.calcTaxIsrael()));
        System.out.println(empl1.getId());
        empl1.setSalary(15_000);
        System.out.println(empl1.calcTaxIsrael());
        System.out.println(Employee.salaryNetto(empl1.getSalary(), empl1.calcTaxIsrael()));
        empl1.setSalary(8000);
        System.out.println("Salary: " + empl1.getSalary());

        Employee empl2 = new Employee(2000, "Mary", 12_000, 2.75, "Female");

        double totalTax = empl1.calcTaxIsrael() + empl2.calcTaxIsrael();
        System.out.println("Total Tax: " + totalTax);
        System.out.println("Total salary = " + Employee.salaryNetto(empl1.getSalary()+empl2.getSalary(), totalTax));
    }
}
