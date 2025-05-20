package telran.equation.controller;

import telran.equation.model.QuadraticEquation;

public class EquationAppl {
    public static void main(String[] args) {
        QuadraticEquation eq = new QuadraticEquation(2, -4, -6);
        eq.displayEquation();
        System.out.println("Delta = " + eq.getDelta());
        System.out.println("Number of roots = " + eq.getQuantityRoots());

        double[] roots = eq.getRoots();
        for (int i = 0; i < roots.length; i++) {
            System.out.println("root[" + (i + 1) + "] = " + roots[i]);
        }
    }
}
