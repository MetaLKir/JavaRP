package telran.employee.dao;

import telran.employee.model.Employee;
import telran.employee.model.SalesManager;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class CompanyImplStream implements Company {
    Map<Integer, Employee> employees;
    int capacity;// max employee quantity

    public CompanyImplStream(int capacity) {
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
        return employees.values().stream().
                mapToDouble(Employee::calcSalary).
                sum();
    }

    @Override
    public int quantity() {
        return employees.size();
    }

    @Override
    public void printEmployees() {
        employees.values().stream().
                forEach(System.out::println);
    }

    @Override
    public double totalSales() {
        return employees.values().stream().
                filter(e -> e instanceof SalesManager).
                map(e -> (SalesManager) e).
                mapToDouble(SalesManager::getSalesValue).
                sum();
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
        return employees.values().stream().
                filter(predicate).toArray(Employee[]::new);

    }
}
