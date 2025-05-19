package telran.equation.model;

public class QuadraticEquation {
    private double a;
    private double b;
    private double c;

    // ax^2 + bx + c = 0
    // b^2 - 4ac = 16


    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void display(){
        // TODO: ax^2 + bx + c = 0
    }

    public String delta() {
        return "";
    }

    public String quantityRoots() {
        // TODO: if delta < 0 -> 0 roots
        return "";
    }

    public double[] getRoots() {
        return new double[]{0.};
    }
}
