package telran.equation.controller;

import telran.equation.model.QuadraticEquation;

public class EquationAppl {
    public static void main(String[] args) {
        QuadraticEquation eq = new QuadraticEquation(2,-4, -6);
        // Descriminant = b^2 - 4ac = 16 + 48 = 64
        // x1 = (-b + koren'Desc) / 2a = (4+8) / 4 = 3
        // x2 = (4-8) / 4 = -1
        eq.display();
        System.out.println("Delta = " +eq.delta());
        System.out.println("Number of roots: " + eq.quantityRoots());

        double[] roots = eq.getRoots();
        for (int i = 0; i < roots.length; i++) {

        }
    }

}
