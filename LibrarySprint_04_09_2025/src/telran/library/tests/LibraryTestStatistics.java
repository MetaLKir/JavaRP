package telran.library.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.library.models.*;
import telran.library.entities.*;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTestStatistics {
    // SPRINT 4
    private ILibrary library;
    private Book[] books;
    private Reader[] readers;
    private final LocalDate PICK_DATE = LocalDate.of(2021, 1, 1);

    @BeforeEach
    void setUp() {
        library = new LibraryMaps();

        books = new Book[5];
        for (int i = 0; i < books.length; i++) {
            books[i] = new Book(1_000_000_000L + i, "book" + i, "author" + i / 2, 10, 30);
            library.addBookItem(books[i]);
        }

        readers = new Reader[5];
        for (int i = 0; i < readers.length; i++) {
            readers[i] = new Reader(1000 + i, "reader" + i, "011-00000" + i, LocalDate.of(1980 + i * 5, 1 + i, 1 + i));
            library.addReader(readers[i]);
        }
    }

    @Test
    void getReadersDelayingBooks() {
        library.pickBook(books[0].getIsbn(), readers[0].getReaderId(), PICK_DATE); // delaying
        library.pickBook(books[0].getIsbn(), readers[1].getReaderId(), PICK_DATE.plusDays(5)); // delaying
        library.pickBook(books[0].getIsbn(), readers[2].getReaderId(), PICK_DATE.plusDays(30)); // not delaying

        library.pickBook(books[0].getIsbn(), readers[3].getReaderId(), PICK_DATE); // returned in time
        library.returnBook(books[0].getIsbn(), readers[3].getReaderId(), PICK_DATE.plusDays(20));

        library.pickBook(books[0].getIsbn(), readers[4].getReaderId(), PICK_DATE); // returned with delay
        library.returnBook(books[0].getIsbn(), readers[4].getReaderId(), PICK_DATE.plusDays(35));

        LocalDate currentDate = PICK_DATE.plusDays(40);
        List<ReaderDelay> actual = library.getReadersDelayingBooks(currentDate);
        List<ReaderDelay> expected = List.of(
                new ReaderDelay(readers[0], 10),
                new ReaderDelay(readers[1], 5)
        );
        assertEquals(expected, actual);
    }

    @Test
    void getReadersDelayedBooks() {
        library.pickBook(books[0].getIsbn(), readers[0].getReaderId(), PICK_DATE); // returned with delay
        library.returnBook(books[0].getIsbn(), readers[0].getReaderId(), PICK_DATE.plusDays(33));
        library.pickBook(books[0].getIsbn(), readers[1].getReaderId(), PICK_DATE); // returned with delay
        library.returnBook(books[0].getIsbn(), readers[1].getReaderId(), PICK_DATE.plusDays(50));
        library.pickBook(books[0].getIsbn(), readers[2].getReaderId(), PICK_DATE); // returned in time
        library.returnBook(books[0].getIsbn(), readers[2].getReaderId(), PICK_DATE.plusDays(20));

        library.pickBook(books[0].getIsbn(), readers[3].getReaderId(), PICK_DATE.plusDays(5)); // book on hands

        List<ReaderDelay> actual = library.getReadersDelayedBooks();
        List<ReaderDelay> expected = List.of(
                new ReaderDelay(readers[0], 3),
                new ReaderDelay(readers[1], 20)
        );
        assertEquals(expected, actual);
    }

    @Test
    void getMostPopularBooks() {
        library.pickBook(books[0].getIsbn(), readers[0].getReaderId(), PICK_DATE);
        library.pickBook(books[0].getIsbn(), readers[1].getReaderId(), PICK_DATE.plusYears(1));
        library.pickBook(books[1].getIsbn(), readers[2].getReaderId(), PICK_DATE.plusYears(1));
        library.pickBook(books[1].getIsbn(), readers[3].getReaderId(), PICK_DATE.plusYears(2));
        library.pickBook(books[2].getIsbn(), readers[4].getReaderId(), PICK_DATE.plusYears(2));
        library.pickBook(books[3].getIsbn(), readers[0].getReaderId(), PICK_DATE.plusYears(3));
        library.pickBook(books[3].getIsbn(), readers[1].getReaderId(), PICK_DATE.plusYears(3));
        library.pickBook(books[3].getIsbn(), readers[2].getReaderId(), PICK_DATE.plusYears(3));

        List<Book> actual = library.getMostPopularBooks(LocalDate.of(1900, 1, 1), LocalDate.now(), 1, 120);
        List<Book> expected = List.of(books[3]);
        assertEquals(expected, actual);
        // most popular of all time
        actual = library.getMostPopularBooks(PICK_DATE, PICK_DATE.plusMonths(25), 1, 120);
        expected = List.of(books[0], books[1]);
        assertEquals(expected, actual);
        // most popular between 01-01-2020 and 01-02-2022
        actual = library.getMostPopularBooks(PICK_DATE, PICK_DATE.plusMonths(25), 1, 120);
        expected = List.of(books[0], books[1]);
        assertEquals(expected, actual);
        // most popular for age 20-25
        actual = library.getMostPopularBooks(LocalDate.of(1900, 1, 1), LocalDate.now(), 20, 25);
        expected = List.of(books[2]);
        assertEquals(expected, actual);
    }

    @Test
    void getMostPopularAuthors() {
        for (int i = 0; i < books.length; i++) {
            library.pickBook(books[i].getIsbn(), readers[i].getReaderId(), PICK_DATE);
            library.pickBook(books[i].getIsbn(), readers[i].getReaderId(), PICK_DATE);
        }

        List<String> actual = library.getMostPopularAuthors();
        Collections.sort(actual);
        List<String> expected = Arrays.asList("author0", "author1");
        Collections.sort(expected);
        assertEquals(expected, actual);
    }

    @Test
    void getMostActiveReaders() {
        library.pickBook(books[0].getIsbn(), readers[0].getReaderId(), PICK_DATE);
        library.pickBook(books[0].getIsbn(), readers[1].getReaderId(), PICK_DATE.plusYears(1));
        library.pickBook(books[1].getIsbn(), readers[2].getReaderId(), PICK_DATE.plusYears(1));
        library.pickBook(books[1].getIsbn(), readers[3].getReaderId(), PICK_DATE.plusYears(2));
        library.pickBook(books[2].getIsbn(), readers[4].getReaderId(), PICK_DATE.plusYears(2));
        library.pickBook(books[3].getIsbn(), readers[0].getReaderId(), PICK_DATE.plusYears(3));
        library.pickBook(books[3].getIsbn(), readers[1].getReaderId(), PICK_DATE.plusYears(3));
        library.pickBook(books[3].getIsbn(), readers[2].getReaderId(), PICK_DATE.plusYears(3));

        List<Reader> actual = library.getMostActiveReaders(LocalDate.of(1900, 1, 1), LocalDate.now());
        List<Reader> expected = List.of(readers[0], readers[1], readers[2]);
        assertEquals(expected, actual);

        actual = library.getMostActiveReaders(PICK_DATE.plusYears(2), PICK_DATE.plusMonths(25));
        expected = List.of(readers[3], readers[4]);
        assertEquals(expected, actual);
    }
}