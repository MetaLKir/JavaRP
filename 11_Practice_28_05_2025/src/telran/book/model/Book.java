package telran.book.model;

public class Book {
    // (long) isbn (checkIsbn() - 13 digits), title, author - (unknown), (int) yearOfPublishing

    private long isbn;
    private String title;
    private String author = "unknown";
    private int yearOfPublishing;

    public Book(long isbn, String title, String author, int yearOfPublishing) {
        this.isbn = checkIsbn(isbn);
        this.title = title;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
    }

    public Book(long isbn, String title, int yearOfPublishing) {
        this.isbn = checkIsbn(isbn);
        this.title = title;
        this.yearOfPublishing = yearOfPublishing;
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

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    private long checkIsbn(long isbn) {
        if (isbn >= 1_000_000_000_000l && isbn < 9_999_999_999_999l) {
            return isbn;
        }
        return -1;
    }

    public void display() {
        System.out.println("ISBN: " + isbn + ", Title: " + title + ", Author: " + author + ", Year: " + yearOfPublishing);
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearOfPublishing=" + yearOfPublishing +
                '}';
    }
}
