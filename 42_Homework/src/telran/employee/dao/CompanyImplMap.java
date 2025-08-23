package telran.employee.dao;

import telran.employee.model.Employee;
import telran.employee.model.SalesManager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class CompanyImplMap implements Company {
    Map<Integer, Employee> employees;
    int capacity;// max telran.employee quantity

    public CompanyImplMap(int capacity) {
        this.capacity = capacity;
        employees = new LinkedHashMap<>();

    }

    @Override
    public boolean addEmployee(Employee employee) {
        if (employee == null || employees.size() == capacity) {
            return false;
        }
        return employees.putIfAbsent(employee.getId(), employee) == null;
    }

    @Override
    public Employee removeEmployee(int id) {
        return employees.remove(id);
    }

    @Override
    public Employee findEmployee(int id) {
        return employees.get(id);
    }

    @Override
    public double totalSalary() {
        double sum = 0;
        for (Employee e : employees.values()) {
            sum += e.calcSalary();
        }
        return sum;
    }

    // made in interface
//    @Override
//    public double averageSalary() {
//        return totalSalary() / size;
//    }

    @Override
    public int quantity() {
        return employees.size();
    }

    @Override
    public void printEmployees() {
//		for (int i = 0; i < size; i++) {
//			System.out.println(employees[i]);
//		}
        for (Employee e : employees.values()) {
            System.out.println(e);
        }
    }

    @Override
    public double totalSales() {
        double sum = 0;
        for (Employee e : employees.values()) {
            if (e instanceof SalesManager sm) {
                sum += sm.getSalesValue();
            }
        }
        return sum;
    }

    @Override
    public Employee[] findEmployeesHoursGreaterThan(int hours) {
        return findEmployeesByPredicate((e) -> e.getHours() >= hours);
    }

    @Override
    public Employee[] findEmployeesSalaryBetween(double min, double max) {
        return findEmployeesByPredicate((e) -> e.calcSalary() >= min && e.calcSalary() < max);
    }

    private Employee[] findEmployeesByPredicate(Predicate<Employee> predicate) {
        List<Employee> res = new ArrayList<>();
        for (Employee e : employees.values()) {
            if (predicate.test(e))
                res.add(e);
        }
        return res.toArray(new Employee[0]);
    }
}
