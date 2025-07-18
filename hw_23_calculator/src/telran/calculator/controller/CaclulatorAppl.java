package telran.calculator.controller;

import telran.calculator.model.Operation;

import java.util.Scanner;

public class CaclulatorAppl {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please, enter first number: ");
        int a = scanner.nextInt();

        System.out.print("Please, enter second number: ");
        int b = scanner.nextInt();

        System.out.println("Please, choose operation: AND, OR, XOR, NOT, SHIFT_LEFT, SHIFT_RIGHT, UNSIGNED_SHIFT_RIGHT");
        String operation = scanner.next().toUpperCase();

        System.out.println("a : " + a + ", b : " + b + ", operation : " + operation);

        Operation oper = Operation.valueOf(operation);
        int result = oper.calculate(a, b);
        System.out.println("result : " + result);
        System.out.println("Binary: " + Integer.toBinaryString(result));
    }
}
