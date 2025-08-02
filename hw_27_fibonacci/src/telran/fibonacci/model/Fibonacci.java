package telran.fibonacci.model;

import java.util.Iterator;

public class Fibonacci implements Iterable<Integer> {
    private int quantity;
    private int[] sequence;
    private final int firstNumber = 0;
    private final int secondNumber = 1;

    public Fibonacci(int quantity) {
        this.quantity = quantity;
        generateSequence();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        generateSequence();
    }

    private void generateSequence() {
        this.sequence = new int[quantity];
        if (quantity == 0) return;

        sequence[0] = secondNumber;
        if (quantity == 1) return;

        sequence[1] = secondNumber + firstNumber;
        if (quantity == 2) return;

        for (int i = 2; i < quantity; i++) {
            sequence[i] = sequence[i - 1] + sequence[i - 2];
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < quantity;
            }

            @Override
            public Integer next() {
                return sequence[i++];
            }
        };
    }
}
