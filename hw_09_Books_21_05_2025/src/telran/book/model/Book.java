package telran.book.model;

public class Book {
    private String title;
    private String author;
    private int year;
    private long isbn;
    // checkIsbn() - 13 digits


    public Book(String title) {
        this.title = title;
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book(String title, String author, int year, long isbn) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
    }

    private boolean checkIsbn(long isbn){
        boolean isValid = false;
       
        return isValid;
    }
}
