package telran.employee.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.employee.dao.Company;
import telran.employee.dao.CompanyImpl;
import telran.employee.model.Employee;
import telran.employee.model.Manager;
import telran.employee.model.SalesManager;
import telran.employee.model.WageEmployee;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {
    Company company;
    Employee[] firm;

    @BeforeEach
    void setUp() {
        company = new CompanyImpl(5);
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
        assertEquals(4, company.size());

        Employee employee = new SalesManager(6666, "John", "Doe", 66, 66_666, 0.6);
        assertTrue(company.addEmployee(employee));
        assertEquals(5, company.size());

        employee = new SalesManager(7777, "Johny", "Doe", 77, 77_777, 0.7);
        assertFalse(company.addEmployee(employee));
        assertEquals(5, company.size());
    }

    @Test
    void removeEmployee() {
        assertNull(company.removeEmployee(8888));
        assertEquals(4, company.size());

        assertEquals(firm[1], company.removeEmployee(firm[1].getId()));
        assertEquals(3, company.size());
    }

    @Test
    void findEmployee() {
        assertEquals(firm[1], company.findEmployee(firm[1].getId()));
        assertNull(company.removeEmployee(8888));
    }

    @Test
    void totalSalary() {
        double salary = 0;
        for (Employee employee : firm){
            salary += employee.calcSalary();
        }

        assertEquals(salary, company.totalSalary());
    }

    @Test
    void averageSalary() {
        double salary = 0;
        for (Employee employee : firm){
            salary += employee.calcSalary();
        }
        salary /= firm.length;

        assertEquals(salary, company.averageSalary());
    }

    @Test
    void size() {
        assertEquals(4, company.size());

        company.removeEmployee(firm[1].getId());
        assertEquals(3, company.size());

        Employee employee = new SalesManager(6666, "John", "Doe", 66, 66_666, 0.6);
        company.addEmployee(employee);
        assertEquals(4, company.size());
    }

    @Test
    void printEmployees() {
        PrintStream originalOut = System.out;

        ByteArrayOutputStream outExpected = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outExpected));
        for (Employee employee : firm)
            System.out.println(employee);

        ByteArrayOutputStream outActual = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outActual));
        company.printEmployees();

        System.setOut(originalOut);

        assertEquals(outActual.toString(), outExpected.toString());
    }

    @Test
    void totalSales() {
        assertEquals(600_000, company.totalSales());
    }
}