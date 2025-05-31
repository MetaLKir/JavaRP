package telran.book.dao;

import telran.book.model.Book;

public class Library {
    Book[] books;
    int size;

    public Library(int capacity){
        books = new Book[capacity];
    }

    public Book findBookByTitle(String title) {
        for (int i = 0; i < size; i++) {
            if (books[i].getTitle().equals(title))
                return books[i];
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    public Book updateBook(String author, String newAuthor) {
        for (int i = 0; i < size; i++) {
            if(books[i].getAuthor().equals(author)) {
                books[i].setAuthor(newAuthor);
                return books[i];
            }
        }
        return null;
    }

    public boolean addBook(Book book) {
        if(books.length == size)
            return false;
        books[size] = book;
        size++;
        return true;
    }

    public Book removeBookByTitle(String title) {
        for (int i = 0; i < size; i++) {
            if (books[i].getTitle().equals(title)){
                Book victim = books[i];
                books[i] = books[size - 1];
                books[size - 1] = null;
                size--;
                return victim;
            }
        }
        return null;
    }

    public int findIndexOfBookByTitle(String title) {
        for (int i = 0; i < size; i++) {
            if(books[i].getTitle().equals(title)) {
                return i;
            }
        }
        return -1;
    }

    public void printBooks() {
        System.out.println("ALL BOOKS INFO" + "=".repeat(100));
        for (int i = 0; i < size; i++) {
            System.out.println(books[i]);
        }
    }

    public void printBooks(int fromYear) {
        System.out.println("BOOKS INFO FROM YEAR " + fromYear + "=".repeat(100));
        for (int i = 0; i < size; i++) {
            if (books[i].getYearOfPublishing() >= fromYear){
                System.out.println(books[i]);
            }
        }
    }

    public void printBooks(int fromYear, int toYear) {
        System.out.println("BOOKS INFO FROM YEAR " + fromYear + " TO YEAR " + toYear + "=".repeat(100));
        for (int i = 0; i < size; i++) {
            if (books[i].getYearOfPublishing() >= fromYear && books[i].getYearOfPublishing() <= toYear){
                System.out.println(books[i]);
            }
        }
    }
}
