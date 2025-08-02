package telran.fibonacci.model;

import java.util.Iterator;

public class FibonacciIterator implements Iterator<Integer> {
    private int quantity;
    private int[] sequence;
    private int i;

    public FibonacciIterator(int quantity) {
        this.quantity = quantity;
        this.sequence = new int[quantity];
        if (quantity == 0) return;

        sequence[0] = 1;
        if (quantity == 1) return;

        sequence[1] = 1;
        if (quantity == 2) return;

        for (int i = 2; i < quantity; i++) {
            sequence[i] = sequence[i - 1] + sequence[i - 2];
        }
    }

    @Override
    public boolean hasNext() {
        return i < quantity;
    }

    @Override
    public Integer next() {
        return sequence[i++];
    }
}
