package telran.library.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.library.models.*;
import telran.library.entities.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTestReturnRemoveBook {
    // SPRINT 3
    private ILibrary library;
    private Book[] books;
    private Reader[] readers;
    private final LocalDate PICK_DATE = LocalDate.of(2021, 1, 1);

    @BeforeEach
    void setUp() {
        library = new LibraryMaps();

        books = new Book[5];
        for (int i = 0; i < books.length; i++) {
            books[i] = new Book(1_000_000_000L + i, "book" + i, "author" + i / 3, 10, 30);
            library.addBookItem(books[i]);
        }

        readers = new Reader[3];
        for (int i = 0; i < readers.length; i++) {
            readers[i] = new Reader(1000 + i, "reader" + i, "011-00000" + i, LocalDate.of(2000 + i, 1 + i, 1 + i));
            library.addReader(readers[i]);
        }
    }

    @Test
    void removeBook_neverPickedBook() {
        RemovedBookData expected = new RemovedBookData(books[0], new ArrayList<>());
        RemovedBookData actual = library.removeBook(books[0].getIsbn());
        assertEquals(expected, actual);
        assertNull(library.getBookItem(books[0].getIsbn()));
    }

    @Test
    void removeBook_bookInUse() {
        library.pickBook(books[1].getIsbn(), readers[1].getReaderId(), PICK_DATE);
        RemovedBookData expected = new RemovedBookData(books[1], null);
        RemovedBookData actual = library.removeBook(books[1].getIsbn());
        assertEquals(expected, actual);
        assertEquals(books[1], library.getBookItem(books[1].getIsbn())); // book is still there
        assertTrue(books[1].isRemoval());
    }

    @Test
    void removeBook_bookReturned() {
        library.pickBook(books[1].getIsbn(), readers[1].getReaderId(), LocalDate.of(2021, 1, 1));
        library.pickBook(books[1].getIsbn(), readers[2].getReaderId(), LocalDate.of(2022, 2, 2));
        library.returnBook(books[1].getIsbn(), readers[1].getReaderId(), LocalDate.of(2021, 1, 5));
        library.returnBook(books[1].getIsbn(), readers[2].getReaderId(), LocalDate.of(2022, 2, 25));

        List<PickRecord> recordsForExpected = library.getPickedRecordsAtDates(LocalDate.of(2020, 1, 1), LocalDate.of(2023, 1, 1));
        RemovedBookData expected = new RemovedBookData(books[1], recordsForExpected);
        RemovedBookData actual = library.removeBook(books[1].getIsbn());
        assertEquals(expected, actual);

        // no book and records after removal
        assertNull(library.getBookItem(books[1].getIsbn()));
        List<PickRecord> recordsAfterRemove = library.getPickedRecordsAtDates(LocalDate.of(2020, 1, 1), LocalDate.of(2023, 1, 1));
        assertTrue(recordsAfterRemove.isEmpty());
    }


    @Test
    void removeAuthor() {
        library.pickBook(books[1].getIsbn(), readers[0].getReaderId(), PICK_DATE);
        library.returnBook(books[1].getIsbn(), readers[0].getReaderId(), PICK_DATE.plusDays(10));
        List<PickRecord> pickRecords = library.getPickedRecordsAtDates(PICK_DATE, PICK_DATE.plusYears(1));

        library.pickBook(books[2].getIsbn(), readers[0].getReaderId(), PICK_DATE);


        List<RemovedBookData> expected = List.of(
                new RemovedBookData(books[0], new ArrayList<>()), // no picks
                new RemovedBookData(books[1], pickRecords),      // picked and returned
                new RemovedBookData(books[2], null)       // book still in use
        );
        List<RemovedBookData> actual = library.removeAuthor("author0");
        assertEquals(expected, actual);

        assertNull(library.getBookItem(books[0].getIsbn()));
        assertNull(library.getBookItem(books[1].getIsbn()));
        assertEquals(books[2], library.getBookItem(books[2].getIsbn()));

        List<Book> actualBooksLeft = library.getBooksAuthor("author0");
        List<Book> expectedBooksLeft = List.of(books[2]);
        assertEquals(expectedBooksLeft, actualBooksLeft);
    }

    @Test
    void returnBook_withoutDelay() {
        library.pickBook(books[0].getIsbn(), readers[0].getReaderId(), PICK_DATE);

        RemovedBookData expected = new RemovedBookData(books[0], null);
        RemovedBookData actual = library.returnBook(books[0].getIsbn(), readers[0].getReaderId(), PICK_DATE.plusDays(10));
        assertEquals(expected, actual);
        assertEquals(10, books[0].getAmount());
        assertEquals(0, books[0].getAmountInUse());
        // check delay
        List<PickRecord> records = library.getPickedRecordsAtDates(PICK_DATE, PICK_DATE.plusYears(1));
        assertEquals(0, records.getFirst().getDelayDays());
    }

    @Test
    void returnBook_withDelay() {
        library.pickBook(books[0].getIsbn(), readers[0].getReaderId(), PICK_DATE);

        RemovedBookData expected = new RemovedBookData(books[0], null);
        RemovedBookData actual = library.returnBook(books[0].getIsbn(), readers[0].getReaderId(), PICK_DATE.plusDays(100));
        assertEquals(expected, actual);
        assertEquals(10, books[0].getAmount());
        assertEquals(0, books[0].getAmountInUse());
        // check delay
        List<PickRecord> records = library.getPickedRecordsAtDates(PICK_DATE, PICK_DATE.plusYears(1));
        assertEquals(70, records.getFirst().getDelayDays());
    }

    @Test
    void returnBook_bookForRemoval() {
        library.pickBook(books[0].getIsbn(), readers[0].getReaderId(), PICK_DATE);
        library.pickBook(books[0].getIsbn(), readers[1].getReaderId(), PICK_DATE);
        library.removeBook(books[0].getIsbn());

        RemovedBookData expected = new RemovedBookData(books[0], null);
        RemovedBookData actual = library.returnBook(books[0].getIsbn(), readers[0].getReaderId(), PICK_DATE.plusDays(10));
        assertEquals(expected, actual);

        List<PickRecord> records = library.getPickedRecordsAtDates(PICK_DATE, PICK_DATE.plusYears(1));
        expected = new RemovedBookData(books[0], records);
        actual = library.returnBook(books[0].getIsbn(), readers[1].getReaderId(), PICK_DATE.plusDays(10));
        assertEquals(expected, actual);

        assertNull(library.getBookItem(books[0].getIsbn()));
        assertTrue(library.getPickedRecordsAtDates(PICK_DATE, PICK_DATE.plusYears(1)).isEmpty());
    }
}