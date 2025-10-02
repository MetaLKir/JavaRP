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
        library.pickBook(books[1].getIsbn(), readers[1].getReaderId(), LocalDate.of(2021, 1, 1));
        library.pickBook(books[1].getIsbn(), readers[2].getReaderId(), LocalDate.of(2022, 2, 2));
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
        LocalDate pickDate1 = LocalDate.of(2021, 1, 1);
        LocalDate pickDate2 = LocalDate.of(2023, 3, 3);

        library.pickBook(books[1].getIsbn(), readers[0].getReaderId(), pickDate1);
        List<PickRecord> pickRecords1 = library.getPickedRecordsAtDates(pickDate1, pickDate1.plusYears(1));

        library.pickBook(books[2].getIsbn(), readers[0].getReaderId(), pickDate2);
        library.returnBook(books[2].getIsbn(), readers[0].getReaderId(), pickDate2.plusDays(3));
        List<PickRecord> pickRecords2 = library.getPickedRecordsAtDates(pickDate2, pickDate2.plusYears(1));

        List<RemovedBookData> expected = List.of(
                new RemovedBookData(books[0], new ArrayList<>()),
                new RemovedBookData(books[1], pickRecords1),
                new RemovedBookData(books[2], pickRecords2)
        );
        List<RemovedBookData> actual = library.removeAuthor("author0");
        assertEquals(expected, actual);

        assertTrue(library.getBooksAuthor("author0").isEmpty());
        assertNull(library.getBookItem(books[0].getIsbn()));
        assertNull(library.getBookItem(books[1].getIsbn()));
        assertNull(library.getBookItem(books[2].getIsbn()));

    }

    @Test
    void returnBook_withoutDelay() {
        LocalDate pickDate = LocalDate.of(2021, 1, 1);
        library.pickBook(books[0].getIsbn(), readers[0].getReaderId(), pickDate);

        RemovedBookData expected = new RemovedBookData(books[0], null);
        RemovedBookData actual = library.returnBook(books[0].getIsbn(), readers[0].getReaderId(), pickDate.plusDays(10));
        assertEquals(expected, actual);
        assertEquals(10, books[0].getAmount());
        assertEquals(0, books[0].getAmountInUse());
        // check delay
        List<PickRecord> records = library.getPickedRecordsAtDates(pickDate, pickDate.plusYears(1));
        assertEquals(0, records.getFirst().getDelayDays());
    }

    @Test
    void returnBook_withDelay() {
        LocalDate pickDate = LocalDate.of(2021, 1, 1);
        library.pickBook(books[0].getIsbn(), readers[0].getReaderId(), pickDate);

        RemovedBookData expected = new RemovedBookData(books[0], null);
        RemovedBookData actual = library.returnBook(books[0].getIsbn(), readers[0].getReaderId(), pickDate.plusDays(100));
        assertEquals(expected, actual);
        assertEquals(10, books[0].getAmount());
        assertEquals(0, books[0].getAmountInUse());
        // check delay
        List<PickRecord> records = library.getPickedRecordsAtDates(pickDate, pickDate.plusYears(1));
        assertEquals(70, records.getFirst().getDelayDays());
    }

    @Test
    void returnBook_bookRemovedBeforeReturn() {
        LocalDate pickDate = LocalDate.of(2021, 1, 1);
        library.pickBook(books[0].getIsbn(), readers[0].getReaderId(), pickDate);
        library.removeBook(books[0].getIsbn());

        RemovedBookData expected = new RemovedBookData(null, null);
        RemovedBookData actual = library.returnBook(books[0].getIsbn(), readers[0].getReaderId(), pickDate.plusDays(10));
        assertEquals(expected, actual);
    }
}