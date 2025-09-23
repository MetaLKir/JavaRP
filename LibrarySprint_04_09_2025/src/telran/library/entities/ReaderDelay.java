package telran.library.entities;

import java.io.Serializable;
import java.util.Objects;

public class ReaderDelay implements Serializable {
    private Reader reader;
    private int delay;

    public ReaderDelay() {
    }

    public ReaderDelay(Reader reader, int delay) {
        this.reader = reader;
        this.delay = delay;
    }

    public Reader getReader() {
        return reader;
    }

    public int getDelay() {
        return delay;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        ReaderDelay that = (ReaderDelay) o;
        return delay == that.delay && Objects.equals(reader, that.reader);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(reader);
        result = 31 * result + delay;
        return result;
    }

    @Override
    public String toString() {
        return "ReaderDelay{" +
                "reader=" + getReader() +
                ", delay=" + getDelay() +
                '}';
    }
}
