package telran.library.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.library.models.*;
import telran.library.entities.*;
import java.time.LocalDate;

import static telran.library.entities.enums.BooksReturnCode.*;
import static org.junit.jupiter.api.Assertions.*;

class LibraryTestAddGetBookReader {
    // SPRINT 1
    private ILibrary library;
    private Book book;
    private Reader reader;

    @BeforeEach
    void setUp() {
        library = new LibraryMaps();
        book = new Book(1_234_567_890L, "book1", "author1", 10, 3);
        reader = new Reader(1001, "reader1", "1112222222", LocalDate.of(2000, 1, 1));
    }

    @Test
    void addBookItem() {
        assertEquals(OK, library.addBookItem(book));
        assertEquals(BOOK_ITEM_EXISTS, library.addBookItem(book));
        assertEquals(INVALID_BOOK, library.addBookItem(null));

        Book bookTooLowPickPeriod = new Book(2_234_567_890L, "book1", "author1", 10, 1);
        Book bookTooHighPickPeriod = new Book(3_234_567_890L, "book1", "author1", 10, 100);
        assertEquals(PICK_PERIOD_LESS_MIN, library.addBookItem(bookTooLowPickPeriod));
        assertEquals(PICK_PERIOD_GREATER_MAX, library.addBookItem(bookTooHighPickPeriod));
    }

    @Test
    void addReader() {
        assertEquals(OK, library.addReader(reader));
        assertEquals(READER_EXISTS, library.addReader(reader));
        assertEquals(INVALID_READER, library.addReader(null));
    }

    @Test
    void addBookExemplars() {
        library.addBookItem(book);
        assertEquals(NO_BOOK_ITEM, library.addBookExemplars(6_666_666_666L, 20));
        assertEquals(OK, library.addBookExemplars(book.getIsbn(), 20));
        assertEquals(30, book.getAmount());

        // can subtract amount, but books amount can't be < 0
        assertEquals(OK, library.addBookExemplars(book.getIsbn(), -666));
        assertEquals(0, book.getAmount());
    }

    @Test
    void getReader() {
        library.addReader(reader);
        assertEquals(reader, library.getReader(reader.getReaderId()));
        assertNull(library.getReader(666));
    }

    @Test
    void getBookItem() {
        library.addBookItem(book);
        assertEquals(book, library.getBookItem(book.getIsbn()));
        assertNull(library.getBookItem(6_666_666_666L));
    }
}