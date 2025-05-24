package telran.book.controller;

import telran.book.model.Book;
import telran.book.model.Comics;

public class BookAppl {
    public static void main(String[] args) {
        Book[] library = new Book[5];

        library[0] = new Book("Clean Code", "Robert Martin",2008,9783826655487L);
        library[1] = new Book("Necronomicon");
        library[2] = new Book("De rerum natura", "Titus Lucretius Carus", 9780198150978L);
        library[3] = new Comics("Batman: Year One", "Frank Miller", "David Mazzucchelli", 1987, 9781401207526L, "DC Comics");
        library[4] = new Comics("Maus", "Art Spiegelman", "Art Spiegelman", 1991, 9780394747231L);

        print(library);
        System.out.println("=================================");
        print(library, 1987);
        System.out.println("=================================");
        print(library, 1900, 2010);

    }

    // PRINT_METHODS==========================================
    public static void print(Book[] booksToPrint){
        for(Book book : booksToPrint){
            if(book != null)
                System.out.println(book);
        }
    }

    public static void print(Book[] booksToPrint, int year){
        for(Book book: booksToPrint){
           if (book == null)
               continue;
           if(book.getYear() == year){
               System.out.println(book);
           }
        }
    }

    public static void print(Book[] booksToPrint, int fromYear, int toYear){
        int bookYear;
        for(Book book: booksToPrint){
            if (book == null)
               continue;
            bookYear = book.getYear();
            if(bookYear >= fromYear && bookYear <= toYear){
                System.out.println(book);
            }
        }
    }
}
