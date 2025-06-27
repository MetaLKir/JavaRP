package telran.employee.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.employee.dao.Company;
import telran.employee.dao.CompanyImpl;
import telran.employee.model.Employee;
import telran.employee.model.Manager;
import telran.employee.model.SalesManager;
import telran.employee.model.WageEmployee;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {
    Company company;
    Employee[] firm;

    @BeforeEach
    void setUp() {
        company = new CompanyImpl(5);
        firm = new Employee[4];
        firm[0] = new Manager(1000, "John", "Smith", 180, 20_000, 20); //23.600
        firm[1] = new WageEmployee(2000, "Mary", "Smith", 180, 50); //9000
        firm[2] = new SalesManager(3000, "Peter", "Jackson", 180, 100_000, 0.05);  // 6480
        firm[3] = new SalesManager(4000, "Tigran", "Petrosian", 90, 500_000, 0.1); // 50000
        for (int i = 0; i < firm.length; i++) {
            company.addEmployee(firm[i]);
        }
    }

    @Test
    void addEmployee() {
        assertFalse(company.addEmployee(firm[3]));
        Employee employee = new SalesManager(5000, "Andy", "Jackson", 90, 40_000, 0.1);
        assertTrue(company.addEmployee(employee));
        assertEquals(5, company.size());
        employee = new SalesManager(6000, "Andy", "Jackson", 90, 40_000, 0.1);
        assertFalse(company.addEmployee(employee));
    }

    @Test
    void removeEmployee() {
        Employee actual = company.removeEmployee(3000);
        assertEquals(firm[2], actual);
        assertEquals(3, company.size());
        assertNull(company.removeEmployee(3000));
        assertEquals(3, company.size());
    }

    @Test
    void findEmployee() {
        assertEquals(firm[2], company.findEmployee(3000));
        assertNull(company.findEmployee(7000));
    }

    private double calcTotalSalaryForFirm() {
        double sum = 0;
        for (int i = 0; i < firm.length; i++) {
            sum += firm[i].calcSalary();
        }
        return sum;
    }

    @Test
    void totalSalary() {
        assertEquals(calcTotalSalaryForFirm(), company.totalSalary());
    }

    @Test
    void averageSalary() {
        assertEquals(89080.001 / 4, company.averageSalary(), 0.01);
    }

    @Test
    void totalSales() {
        assertEquals(600_000, company.totalSales());
    }

    @Test
    void size() {
        assertEquals(4, company.size());
    }

    @Test
    void printEmployees() {
        company.printEmployees();
    }

    @Test
    void findEmployeesHoursGreaterThan() {
        Employee[] actual = company.findEmployeesHoursGreaterThan(100);
        Employee[] expected = {firm[0], firm[1], firm[2]};
        assertArrayEquals(expected, actual);
    }

    @Test
    void findEmployeesSalaryBetween() {
        Employee[] actual = company.findEmployeesSalaryBetween(5000, 10000);
        Employee[] expected = {firm[1], firm[2]};
        assertArrayEquals(expected, actual);
    }
}