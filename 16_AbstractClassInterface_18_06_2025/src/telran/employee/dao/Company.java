package telran.employee.dao;

import telran.employee.model.Employee;

public interface Company {

    boolean addEmployee(Employee employee);
    Employee removeEmployee(int id);
    Employee findEmployee(int id);
    double totalSalary();
    double averageSalary();
    int size();
    void printEmployees();
    double totalSales();
    Employee[] findEmployeesHoursGreaterThan(int hours);
    Employee[] findEmployeesSalaryBetween(double min, double max);

}
