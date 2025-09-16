package telran.library.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class PickRecord implements Serializable {
    private long isbn;
    private int readerId;
    private LocalDate pickDate;
    private LocalDate returnDate;
    private int delayDays;

    public PickRecord() {
    }

    public PickRecord(long isbn, int readerId, LocalDate pickDate) {
        this.isbn = isbn;
        this.readerId = readerId;
        this.pickDate = pickDate;
    }

    public long getIsbn() {
        return isbn;
    }

    public int getReaderId() {
        return readerId;
    }

    public LocalDate getPickDate() {
        return pickDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public int getDelayDays() {
        return delayDays;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setDelayDays(int delayDays) {
        this.delayDays = delayDays;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        PickRecord record = (PickRecord) o;
        return isbn == record.isbn && readerId == record.readerId && Objects.equals(pickDate, record.pickDate);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(isbn);
        result = 31 * result + readerId;
        result = 31 * result + Objects.hashCode(pickDate);
        return result;
    }

    @Override
    public String toString() {
        return "PickRecord{" +
                "isbn=" + getIsbn() +
                ", readerId=" + getReaderId() +
                ", pickDate=" + getPickDate() +
                ", returnDate=" + getReturnDate() +
                ", delayDays=" + getDelayDays() +
                '}';
    }
}
