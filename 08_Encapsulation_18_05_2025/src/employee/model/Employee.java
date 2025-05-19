package employee.model;

public class Employee {
    private int id;
    private String name;
    private double salary;
    private double taxAllowance;
    private String gender;

    public Employee(int id, String name, double salary, double taxAllowance, String gender){
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.taxAllowance = taxAllowance;
        this.gender = gender;
    }

    public int getId(){
        return id;
    }
    public double getSalary(){
        return salary;
    }

    public void setSalary(double salary){
        if(this.salary < salary)
            this.salary = salary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTaxAllowance() {
        return taxAllowance;
    }

    public void setTaxAllowance(double taxAllowance) {
        this.taxAllowance = taxAllowance;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void display(){
        System.out.println("ID: " + id + ", Name: " + name +
                ", Salary: " + salary + ", Tax: " + taxAllowance +
                ", Gender: " + gender);
    }

    public double calcTaxIsrael(){
        double[] levels = {0, 7_010, 10_060, 16_150, 22_440,  46_690, 60_130};
        double[] rates = {0.1, 0.14, 0.2, 0.31, 0.35, 0.47, 0.5};
        double res = -(taxAllowance * 223);
        for (int i = 0; i < levels.length - 1; i++) {
            if(salary > levels[i] && salary <= levels[i+1]){
                res = res + (salary - levels[i]) * rates[i];
                return res < 0 ? 0 : res;
            }
            else
                res = res + (levels[i + 1] - levels[i]) * rates[i];
        }
        res = res + (salary - levels[levels.length - 1]) * rates[rates.length - 1];
        return res < 0 ? 0 : res;
    }

    public static double salaryNetto(double salary, double tax){
        return salary - tax;
    }
}
