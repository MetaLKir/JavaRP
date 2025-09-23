package telran.library.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class RemovedBookData implements Serializable {
    private Book book;
    private List<PickRecord> records;

    public RemovedBookData() {
    }

    public RemovedBookData(Book book, List<PickRecord> records) {
        this.book = book;
        this.records = records;
    }

    public List<PickRecord> getRecords() {
        return records;
    }

    public Book getBook() {
        return book;
    }

    @Override
    public String toString() {
        return "RemovedBookData{" +
                "book=" + getBook() +
                ", records=" + getRecords() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        RemovedBookData that = (RemovedBookData) o;
        return Objects.equals(book, that.book) && Objects.equals(records, that.records);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(book);
        result = 31 * result + Objects.hashCode(records);
        return result;
    }
}
