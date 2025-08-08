package telran.employee.dao;


import telran.employee.model.Employee;
import telran.employee.model.SalesManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CompanyImplList implements Company {
    List<Employee> employees;
    int capacity; // max employees quantity

    public CompanyImplList(int capacity) {
        employees = new ArrayList<>();
        this.capacity = capacity;
    }

    @Override
    public boolean addEmployee(Employee employee) {
        if (employee == null || employees.size() == capacity
                || findEmployee(employee.getId()) != null) {
            return false;
        }
        return employees.add(employee);
    }

    @Override
    public Employee removeEmployee(int id) {
        Employee victim = findEmployee(id);
        employees.remove(victim);
        return victim;
    }

    @Override
    public Employee findEmployee(int id) {
        for (Employee e : employees) {
            if (e.getId() == id)
                return e;
        }
        return null;
    }

    @Override
    public double totalSalary() {
        double sum = 0;
        for (Employee e : employees) {
            sum += e.calcSalary();
        }
        return sum;
    }

    @Override
    public int quantity() {
        return employees.size();
    }

    @Override
    public void printEmployees() {
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    @Override
    public double totalSales() {
        double sum = 0;
        for (Employee employee : employees) {
            if (employee instanceof SalesManager) {
                SalesManager sm = (SalesManager) employee;
                sum += sm.getSalesValue();
            }
        }
        return sum;
    }

    @Override
    public Employee[] findEmployeesHoursGreaterThan(int hours) {
        //return findEmployeesByPredicate(new HoursPredicate(hours));
        return findEmployeesByPredicate((e) -> e.getHours() >= hours);
    }

    @Override
    public Employee[] findEmployeesSalaryBetween(double min, double max) {
        Predicate<Employee> predicate = new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.calcSalary() >= min && employee.calcSalary() < max;
            }
        };
        return findEmployeesByPredicate(predicate);
    }

    private Employee[] findEmployeesByPredicate(Predicate<Employee> predicate) {
        List<Employee> res = new ArrayList<>();
        for (Employee e : employees) {
            if (predicate.test(e))
                res.add(e);
        }
        return res.toArray(new Employee[res.size()]);
    }
}
