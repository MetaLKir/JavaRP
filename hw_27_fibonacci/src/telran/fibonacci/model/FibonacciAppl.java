package telran.fibonacci.model;

public class FibonacciAppl {
    public static void main(String[] args) {
        System.out.println("===== FIBONACCI =====");
        Fibonacci fibonacci = new Fibonacci(8);
        printFibonacciSequence(fibonacci);

        System.out.println();

        System.out.println("===== FIBONACCI custom Iterator =====");
        FibonacciWithCustomIterator fib = new FibonacciWithCustomIterator(8);
        printFibonacciSequence(fib);
    }


    public static void printFibonacciSequence(Fibonacci fibonacci) {
        int sum = 0;
        System.out.print("Fibonacci sequence of length " + fibonacci.getQuantity() + " is:\t");
        for (int number : fibonacci) {
            System.out.print(number + " ");
            sum += number;
        }
        System.out.println();
        System.out.println("Sum of numbers in sequence: " + sum);
    }

    public static void printFibonacciSequence(FibonacciWithCustomIterator fibonacci) {
        int sum = 0;
        System.out.print("Fibonacci sequence of length " + fibonacci.getQuantity() + " is:\t");
        for (int number : fibonacci) {
            System.out.print(number + " ");
            sum += number;
        }
        System.out.println();
        System.out.println("Sum of numbers in sequence: " + sum);
    }
}
