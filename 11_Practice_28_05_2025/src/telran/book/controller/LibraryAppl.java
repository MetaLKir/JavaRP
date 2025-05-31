package telran.book.controller;

import telran.book.dao.Library;
import telran.book.model.Book;

public class LibraryAppl {
    public static void main(String[] args) {
//        Book book1 = new Book(1000_2000_3000_4L, "Bible", "God", 0);
//        Library library = new Library(7);
//        library.addBook(book1); // 1
//        library.addBook(book1); // 2
//        library.addBook(book1); // 3
//        library.addBook(book1); // 4
//        library.addBook(book1); // 5
//        library.addBook(book1); // 6
//        library.addBook(book1); // 7
//        library.printBooks();
//        System.out.println("Size of library = " + library.getSize());

        Library library = new Library(8);
        System.out.println(library.addBook(new Book(1_0000_0000_0001L, "Book1", 1977)));
        System.out.println(library.addBook(new Book(1_0000_0000_0002L, "Book2", "Author1",  1998)));
        System.out.println(library.addBook(new Book(1_0000_0000_0003L, "Book3", "Author2",  2012)));
        System.out.println(library.addBook(new Book(1_0000_0000_0004L, "Book4", "Author1",  1980)));
        System.out.println(library.addBook(new Book(1_0000_0000_0005L, "Book5", "Author3",  1995)));
        library.printBooks();
        System.out.println("=".repeat(100));
        System.out.println("Find book" + library.findBookByTitle("Book3"));
        System.out.println("Remove book: " + library.removeBookByTitle("Book3"));
        System.out.println("=".repeat(100));
        library.printBooks();
        System.out.println("=".repeat(100));
        System.out.println(library.updateBook("unknown", "John Doe"));
        System.out.println("=".repeat(100));
        library.printBooks();
    }
}
