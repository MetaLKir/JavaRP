package telran.fibonacci.model;

// 0, 1, 1, 2, 3, 5, 8, 13, 21...
// element is sum of 2 previous elements

import java.util.Iterator;

// Fibonacci fibonacci = new Fibonacci(8); -> 8 elements in sequence
// 1, 1, 2, 3, 5, 8, 13, 21
// start from 1 = 0 + 1;
// we always know first 2 elements to calc sequence: 0 and 1
// TODO: need to generate numbers through Iterator

// print all numbers and reduce sum
// if 8 elements, print all 8 elements, print sum 54
public class Fibonacci implements Iterable<Integer> {
    private int quantity;

    public Fibonacci(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public Iterator<Integer> iterator() {
        // TODO
        return null;
    }
}
