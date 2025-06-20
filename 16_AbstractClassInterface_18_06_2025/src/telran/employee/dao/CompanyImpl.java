package telran.employee.dao;

import telran.employee.model.Employee;

public class CompanyImpl implements Company {
    Employee[] employees;
    int size; // already existed employees

    public CompanyImpl(int capacity) {
        employees = new Employee[capacity];
    }

    @Override
    public boolean addEmployee(Employee employee) {
        // TODO
        return false;
    }

    @Override
    public Employee removeEmployee(int id) {
        return null;
    }

    @Override
    public Employee findEmployee(int id) {
        return null;
    }

    @Override
    public double totalSalary() {
        return 0;
    }

    @Override
    public double averageSalary() {
        return 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printEmployees() {

    }

    @Override
    public double totalSales() {
        return 0;
    }
}
