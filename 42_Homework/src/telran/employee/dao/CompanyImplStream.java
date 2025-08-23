package telran.employee.dao;

import telran.employee.model.Employee;
import telran.employee.model.SalesManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class CompanyImplStream implements Company {
    Stream<Employee> employees;
    int capacity;// max telran.employee quantity

    public CompanyImplStream(int capacity) {
        this.capacity = capacity;
        employees = Stream.empty();
    }

    @Override
    public boolean addEmployee(Employee employee) {
        if (employee == null || employees.count() == capacity || findEmployee(employee.getId()) != null) {
            return false;
        }
        Stream<Employee> toAdd = Stream.of(employee);
        employees = Stream.concat(employees, toAdd);
        return true;
    }

    @Override
    public Employee removeEmployee(int id) {
        Employee victim = findEmployee(id);
        employees.filter(e->e.equals(victim));
        return victim;
    }

    @Override
    public Employee findEmployee(int id) {
        Employee[] res = new Employee[1];
        employees.peek(e-> {
            if(e.getId() == id)
                res[0] = e;
        });
        return res[0];
    }

    @Override
    public double totalSalary() {
        double[] sum = new double[1];
        employees.peek(e-> sum[0] += e.calcSalary());
        return sum[0];
    }

    @Override
    public int quantity() {
        return (int) employees.count();
    }

    @Override
    public void printEmployees() {
//        for (Employee e : employees.values()) {
//            System.out.println(e);
//        }
    }

    @Override
    public double totalSales() {
        double sum = 0;
//        for (Employee e : employees.values()) {
//            if (e instanceof SalesManager sm) {
//                sum += sm.getSalesValue();
//            }
//        }
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
//        for (Employee e : employees.values()) {
//            if (predicate.test(e))
//                res.add(e);
//        }
        return res.toArray(new Employee[0]);
    }
}
