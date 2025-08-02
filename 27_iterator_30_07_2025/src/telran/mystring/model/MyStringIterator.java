package telran.mystring.model;

import java.util.Iterator;

public class MyStringIterator implements Iterator<Character> {
    private StringBuilder builder;
    private int i;
    private int size;

    public MyStringIterator(StringBuilder builder) {
        this.builder = builder;
        size = builder.length();
        i = 0;
    }

    @Override
    public boolean hasNext() {
        return i < size;
    }

    @Override
    public Character next() {
        Character current = builder.charAt(i);
        i++;
        return current;

    }

    @Override
    public void remove() {
        builder.deleteCharAt(--i); // --i because of .next() logic
        size--;

    }
}
