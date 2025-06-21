package telran.employee.dao;

import telran.employee.model.Employee;
import telran.employee.model.SalesManager;

public class CompanyImpl implements Company {
    Employee[] employees;
    int size; // already existed employees

    public CompanyImpl(int capacity) {
        employees = new Employee[capacity];
    }

    @Override
    public boolean addEmployee(Employee employee) {
        if (size == employees.length)
            return false;

        for (int i = 0; i < size; i++) {
            if (employees[i] == employee)
                return false;
        }

        employees[size] = employee;
        size++;
        return true;
    }

    @Override
    public Employee removeEmployee(int id) {
        for (int i = 0; i < size; i++) {
            if(employees[i].getId() == id) {
                Employee employeeToRemove = employees[i];
                employees[i] = employees[size - 1];
                employees[size - 1] = null;
                size--;
                return employeeToRemove;
            }
        }
        return null;
    }

    @Override
    public Employee findEmployee(int id) {
        for (int i = 0; i < size; i++) {
            if(employees[i].getId() == id)
                return employees[i];
        }
        return null;
    }

    @Override
    public double totalSalary() {
        double salaryTotal = 0;
        for (int i = 0; i < size; i++) {
            salaryTotal += employees[i].calcSalary();
        }
        return salaryTotal;
    }

    @Override
    public double averageSalary() {
        double salaryAverage = 0;
        for (int i = 0; i < size; i++) {
            salaryAverage += employees[i].calcSalary();
        }
        salaryAverage /= size;
        return salaryAverage;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printEmployees() {
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }

    @Override
    public double totalSales() {
        double salesSum = 0;
        for (int i = 0; i < size; i++) {
            if(employees[i] instanceof SalesManager seller)
                salesSum += seller.getSalesValue();
        }
        return salesSum;
    }
}
