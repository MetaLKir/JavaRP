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
        company = new CompanyImpl(4);
        firm = new Employee[4]; // to check CompanyImpl

        firm[0] = new Manager(1000, "John", "Smith", 180, 20_000, 20);
        firm[1] = new WageEmployee(2000, "Mary", "Smith", 180, 50);
        firm[2] = new SalesManager(3000, "Peter", "Jackson", 180, 100_000, 0.05);
        firm[3] = new SalesManager(4000, "Tigran", "Petrosian", 90, 500_000, 0.1);

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
    }

    @Test
    void findEmployee() {
    }

    @Test
    void totalSalary() {
    }

    @Test
    void averageSalary() {
    }

    @Test
    void size() {
        assertEquals(4, company.size());
    }

    @Test
    void printEmployees() {
    }

    @Test
    void totalSales() {
    }
}