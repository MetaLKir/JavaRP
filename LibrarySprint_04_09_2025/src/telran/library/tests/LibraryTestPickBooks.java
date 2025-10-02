package telran.library.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.library.models.*;
import telran.library.entities.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static telran.library.entities.enums.BooksReturnCode.*;
import static org.junit.jupiter.api.Assertions.*;

class LibraryTestPickBooks {
    // SPRINT 2
    private ILibrary library;
    private Book[] books;
    private Reader[] readers;

    @BeforeEach
    void setUp() {
        library = new LibraryMaps();

        books = new Book[5];
        for (int i = 0; i < books.length; i++) {
            books[i] = new Book(1_000_000_000L + i, "book" + i, "author" + i / 2, 10, 3);
            library.addBookItem(books[i]);
        }

        readers = new Reader[3];
        for (int i = 0; i < readers.length; i++) {
            readers[i] = new Reader(1000 + i, "reader" + i, "011-00000" + i, LocalDate.of(2000 + i, 1 + i, 1 + i));
            library.addReader(readers[i]);
        }
    }

    @Test
    void pickBook() {
        assertEquals(NO_BOOK_ITEM, library.pickBook(6_666_666_666L, readers[0].getReaderId(), LocalDate.now()));

        books[4].setAmount(0);
        assertEquals(NO_BOOK_EXEMPLARS, library.pickBook(books[4].getIsbn(), readers[0].getReaderId(), LocalDate.now()));
        books[3].setAmountInUse(10);
        assertEquals(NO_BOOK_EXEMPLARS, library.pickBook(books[3].getIsbn(), readers[0].getReaderId(), LocalDate.now()));

        assertEquals(NO_READER, library.pickBook(books[0].getIsbn(), 666, LocalDate.now()));
        assertEquals(0, books[0].getAmountInUse());

        assertEquals(OK, library.pickBook(books[0].getIsbn(), readers[0].getReaderId(), LocalDate.now()));
        assertEquals(1, books[0].getAmountInUse());

        assertEquals(READER_READS_IT, library.pickBook(books[0].getIsbn(), readers[0].getReaderId(), LocalDate.now()));
        assertEquals(1, books[0].getAmountInUse());

        books[1].setRemoval(true);
        assertEquals(BOOK_SET_FOR_REMOVAL, library.pickBook(books[1].getIsbn(), readers[0].getReaderId(), LocalDate.now()));
    }

    @Test
    void getBooksPickedByReader() {
        library.pickBook(books[0].getIsbn(), readers[0].getReaderId(), LocalDate.now());
        library.pickBook(books[3].getIsbn(), readers[0].getReaderId(), LocalDate.now());

        List<Book> expected = Arrays.asList(books[0], books[3]);
        List<Book> actual = library.getBooksPickedByReader(readers[0].getReaderId());
        assertEquals(expected, actual);

        assertTrue(library.getBooksPickedByReader(readers[1].getReaderId()).isEmpty());
    }

    @Test
    void getReadersPickedBook() {
        library.pickBook(books[0].getIsbn(), readers[0].getReaderId(), LocalDate.now());
        library.pickBook(books[0].getIsbn(), readers[2].getReaderId(), LocalDate.now());

        List<Reader> expected = Arrays.asList(readers[0], readers[2]);
        List<Reader> actual = library.getReadersPickedBook(books[0].getIsbn());
        assertEquals(expected, actual);

        assertTrue(library.getReadersPickedBook(books[1].getIsbn()).isEmpty());
    }

    @Test
    void getBooksAuthor() {
        List<Book> actual = library.getBooksAuthor("author0");
        List<Book> expected = List.of(books[0], books[1]);
        assertEquals(expected, actual);

        books[2].setAmount(0);
        actual = library.getBooksAuthor("author1");
        expected = List.of(books[3]);
        assertEquals(expected, actual);

        actual = library.getBooksAuthor(null);
        assertTrue(actual.isEmpty());
    }

    @Test
    void getPickedRecordsAtDates() {
        library.pickBook(books[0].getIsbn(), readers[0].getReaderId(), LocalDate.of(2020, 10, 1));
        library.pickBook(books[1].getIsbn(), readers[0].getReaderId(), LocalDate.of(2022, 5, 25));
        library.pickBook(books[2].getIsbn(), readers[0].getReaderId(), LocalDate.of(2023, 1, 1));
        library.pickBook(books[3].getIsbn(), readers[0].getReaderId(), LocalDate.of(2024, 2, 13));

        LocalDate start = LocalDate.of(2020, 1, 1);
        LocalDate end = LocalDate.of(2023, 1, 1);
        List<PickRecord> actual = library.getPickedRecordsAtDates(start, end);
        List<PickRecord> expected = List.of(
                new PickRecord(books[0].getIsbn(), readers[0].getReaderId(), LocalDate.of(2020, 10, 1)),
                new PickRecord(books[1].getIsbn(), readers[0].getReaderId(), LocalDate.of(2022, 5, 25))
        );
        assertEquals(expected, actual);

        start = LocalDate.of(1995, 1, 1);
        end = LocalDate.of(1999, 1, 1);
        actual = library.getPickedRecordsAtDates(start, end);
        assertTrue(actual.isEmpty());
    }
}