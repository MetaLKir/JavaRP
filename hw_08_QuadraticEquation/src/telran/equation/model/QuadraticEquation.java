package telran.equation.model;

public class QuadraticEquation {
    private double a;
    private double b;
    private double c;
    private double delta;
    private double[] roots;

    // CONSTRUCTOR
    public QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        solveEquation();
    }

    // GETTERS==========================================
    public double getA() { return a; }
    public double getB() { return b; }
    public double getC() { return c; }
    public double getDelta() { return delta; }
    public double[] getRoots() { return roots; }

    public int getQuantityRoots() {
        if(delta >= 0)
            return roots.length;
        else
            return 0;
    }

    // SETTERS==========================================
    public void setA(double a) {
        this.a = a;
        solveEquation();
    }

    public void setB(double b) {
        this.b = b;
        solveEquation();
    }

    public void setC(double c) {
        this.c = c;
        solveEquation();
    }

    // MATH STUFF==========================================
    private void solveEquation(){
        calculateDelta();
        calculateRoots();
    }

    private void calculateDelta(){
        delta = (b * b) - (4 * a * c);
    }

    private double calculateRootFirst() {
        return (-b + Math.sqrt(delta)) / (2 * a);
    }

    private double calculateRootSecond() {
        return (-b - Math.sqrt(delta)) / (2 * a);
    }

    private void calculateRoots(){
        if (delta < 0)
            roots = new double[]{0.};
        else if (delta > 0)
            roots = new double[]{calculateRootFirst(), calculateRootSecond()};
        else if (delta == 0)
            roots = new double[]{calculateRootFirst()};
    }

    // UTILITY==========================================
    public void displayEquation(){
        //TODO ax^2+bx+c=0
        String toDisplay = "(" + a + " * x^2) + (" + b + " * x) + " + c + " = 0";
        System.out.println(toDisplay);
    }






}
