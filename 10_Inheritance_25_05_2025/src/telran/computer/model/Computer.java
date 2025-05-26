package telran.computer.model;

import java.util.Objects;

public class Computer {
    protected String cpu;
    protected int ram;
    protected int ssd;
    protected String brand;

    public Computer(String cpu, int ram, int ssd, String brand) {
        this.cpu = cpu;
        this.ram = ram;
        this.ssd = ssd;
        this.brand = brand;
    }

    public Computer() {
    }

    // GETTERS============================
    public String getCpu() {
        return cpu;
    }

    public int getRam() {
        return ram;
    }

    public int getSsd() {
        return ssd;
    }

    public String getBrand() {
        return brand;
    }
    // SETTERS==============================

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public void setSsd(int ssd) {
        this.ssd = ssd;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    // UTILITY============================
    public void display(){
        System.out.print("Brand = " + brand +
                " ; CPU = " + cpu +
                " ; Ram = " + ram +
                " ; SSD = " + ssd);
    }

    @Override
    public String toString() {
        return "Brand = " + brand +
                " ; CPU = " + cpu +
                " ; Ram = " + ram +
                " ; SSD = " + ssd;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Computer computer = (Computer) o;
        return ram == computer.ram && ssd == computer.ssd && Objects.equals(cpu, computer.cpu) && Objects.equals(brand, computer.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpu, ram, ssd, brand);
    }
}
