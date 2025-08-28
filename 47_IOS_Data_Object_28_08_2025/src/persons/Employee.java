package persons;

import java.time.LocalDate;

public class Employee extends Person {
    private String company;
//    transient private int salary; // won't be serialized
    private int salary;
    private String title;

    public Employee(int id, String name, Address address, LocalDate birthDate, String title, int salary, String company) {
        super(id, name, address, birthDate);
        this.title = title;
        this.salary = salary;
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Employee :: " +
                "company='" + company + '\'' +
                "; salary=" + salary +
                "; title='" + title + '\'' +
                "; " + super.toString();
    }
}
