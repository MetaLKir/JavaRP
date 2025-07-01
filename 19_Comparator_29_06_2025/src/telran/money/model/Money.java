package telran.money.model;

import java.util.Objects;

public class Money implements Comparable<Money>{
    double width;
    double length;
    int value;

    public Money(double width, double length, int value) {
        this.width = width;
        this.length = length;
        this.value = value;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public int getValue() {
        return value;
    }

    public double getArea(){
        return width * length;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Money money)) return false;
        return Double.compare(width, money.width) == 0 && Double.compare(length, money.length) == 0 && value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, length, value);
    }

    @Override
    public String toString() {
        return "Money{" +
                "width=" + width +
                ", length=" + length +
                ", value=" + value +
                ", area=" + getArea() +
                '}';
    }

    @Override
    public int compareTo(Money o) {
        return value - o.value;
    }
}
