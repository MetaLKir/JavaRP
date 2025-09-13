package telran.library.entities;

import java.io.Serializable;

public class Book implements Serializable {
    private long isbn;
    private String title;
    private String author;
    private int amount;
    private int amountInUse;
    private int pickPeriod;

    public Book() {
    }

    public Book(long isbn, String title, String author, int amount, int pickPeriod) {
        if (isbn > 0) this.isbn = isbn;
        this.title = title;
        this.author = author;
        if (amount >= 0) this.amount = amount;
        if (pickPeriod > 0) this.pickPeriod = pickPeriod;
    }

    public long getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getAmount() {
        return amount;
    }

    public int getAmountInUse() {
        return amountInUse;
    }

    public int getPickPeriod() {
        return pickPeriod;
    }

    public void setAmount(int amount) {
        if (amount >= 0) this.amount = amount;
    }

    public void setAmountInUse(int amountInUse) {
        this.amountInUse = amountInUse;
    }

    public void setPickPeriod(int pickPeriod) {
        if (pickPeriod > 0) this.pickPeriod = pickPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;
        return isbn == book.isbn;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(isbn);
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + getIsbn() +
                ", title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", amount=" + getAmount() +
                ", amountInUse=" + getAmountInUse() +
                ", pickPeriod=" + getPickPeriod() +
                '}';
    }

    public static boolean checkIsbnLength(long isbn){
        isbn = Math.abs(isbn);
        int length = Long.toString(isbn).length();
        return length == 10 || length == 13;
    }
}
