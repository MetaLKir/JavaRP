package telran.computer.model;

public class Laptop extends Computer {
    private double hours;
    private double weight;

    public Laptop(String cpu, int ram, int ssd, String brand, double hours, double weight) {
        super(cpu, ram, ssd, brand);
        this.hours = hours;
        this.weight = weight;
    }

    public double getHours() {
        return hours;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public void display(){
        super.display();
        System.out.print(" ; Battery = " + hours + " ; Weight = " + weight);
    }

    @Override
    public String toString() {
        return super.toString() + ", Battery: " + hours + ", Weight: " + weight;
    }
}
