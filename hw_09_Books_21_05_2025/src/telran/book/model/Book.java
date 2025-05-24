package telran.book.model;

public class Book {
    private String title;
    private String author = "Unknown";
    private int year;
    private long isbn;

    // CONSTRUCTORS==================================
    public Book(String title) {
        this.title = title;
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Book(String title, String author, long isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public Book(String title, String author, int year, long isbn) {
        this.title = title;
        this.author = author;
        this.year = year;
        setIsbn(isbn);
    }

    // GETTERS=======================================
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public long getIsbn() { return isbn; }

    // SETTERS========================================
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setYear(int year) { this.year = year; }

    public void setIsbn(long isbn) {
        boolean isbnValid = checkIsbn(isbn);
        if (isbnValid)
            this.isbn = isbn;
        else
            System.out.println("Incorrect ISBN, must contain 13 digits.");
    }

    // UTILITY========================================
    private boolean checkIsbn(long isbn){
       int isbnLength = String.valueOf(isbn).length();
       return isbnLength == 13;
    }

    @Override
    public String toString() {
        return  " | title = " + title +
                " | author = " + author +
                " | year = " + year +
                " | isbn = " + isbn;
    }
}
