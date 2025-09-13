package telran.library.models;

import telran.library.entities.*;
import telran.library.entities.enums.BooksReturnCode;
import telran.util.Persistable;

import static telran.library.entities.enums.BooksReturnCode.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.*;

public class LibraryMaps extends AbstractLibrary implements Persistable {
    private Map<Long, Book> books = new HashMap<>();            // key isbn
    private Map<Integer, Reader> readers = new HashMap<>();     // key readerId

    private Map<Integer, List<PickRecord>> readersRecords = new HashMap<>();    // readerId → выдачи
    private Map<Long, List<PickRecord>> booksRecords = new HashMap<>();         // isbn → выдачи
    private Map<LocalDate, List<PickRecord>> records = new TreeMap<>();         // дата выдачи → выдачи


    @Override
    public BooksReturnCode addBookItem(Book book) {
        if (book == null) return INVALID_BOOK;
        if (book.getPickPeriod() < minPickPeriod) return PICK_PERIOD_LESS_MIN;
        if (book.getPickPeriod() > maxPickPeriod) return PICK_PERIOD_GREATER_MAX;
        BooksReturnCode res =
                books.putIfAbsent(book.getIsbn(), book) == null ?
                        OK : BOOK_ITEM_EXISTS;
        return res;
    }

    @Override
    public BooksReturnCode addReader(Reader reader) {
        if (reader == null) return INVALID_DRIVER;
        boolean readerIsAbsent = readers.putIfAbsent(reader.getReaderId(), reader) == null;
        return readerIsAbsent ? OK : READER_EXISTS;
    }

    @Override
    public BooksReturnCode addBookExemplars(long isbn, int amount) {
        Book book = getBookItem(isbn);
        if (book == null) return NO_BOOK_ITEM;
        book.setAmount(book.getAmount() + amount);
        return OK;
    }

    @Override
    public Reader getReader(int readerId) {
        return readers.get(readerId);
    }

    @Override
    public Book getBookItem(long isbn) {
        return books.get(isbn);
    }

    @Override
    public BooksReturnCode pickBook(long isbn, int readerId, LocalDate pickDate) {
        Reader reader = getReader(readerId);
        if (reader == null) return NO_READER;

//        if(!Book.checkIsbnLength(isbn)) return WRONG_ISBN;

        Book book = getBookItem(isbn);
        if (book == null) return NO_BOOK_ITEM;
        if (book.getAmountInUse() == book.getAmount()) return ALL_BOOKS_IN_USE;

        PickRecord record = new PickRecord(isbn, readerId, pickDate);
        readersRecords.computeIfAbsent(record.getReaderId(), r -> new ArrayList<>()).add(record);
        booksRecords.computeIfAbsent(record.getIsbn(), i -> new ArrayList<>()).add(record);
        records.computeIfAbsent(record.getPickDate(), d -> new ArrayList<>()).add(record);

        book.setAmountInUse(book.getAmountInUse() + 1);
        return OK;
    }

    @Override
    public List<Book> getBooksPickedByReader(int readerId) {
        List<PickRecord> records = readersRecords.getOrDefault(readerId, new ArrayList<>());
        return records.stream().
                map(r -> getBookItem(r.getIsbn())).
                distinct().
                toList();
    }

    @Override
    public List<Reader> getReadersPickedBook(long isbn) {
        List<PickRecord> records = booksRecords.getOrDefault(isbn, new ArrayList<>());
        return records.stream().
                map(r -> getReader(r.getReaderId())).
                distinct().
                toList();
    }

    @Override
    public List<Book> getBooksAuthor(String authorName) {
        if (authorName == null || authorName.isBlank())
            return new ArrayList<>();

        return books.values().stream().
                filter(b -> b.getAuthor().equalsIgnoreCase(authorName.trim())).
                toList();
    }

    @Override
    public List<PickRecord> getPickedRecordsAtDates(LocalDate from, LocalDate to) {
        Map<LocalDate, List<PickRecord>> recordsInRange = ((TreeMap<LocalDate, List<PickRecord>>) records).subMap(from, to);
        return recordsInRange.values().stream().
                flatMap(Collection::stream).
                toList();
    }

    @Override
    public void save(String fileName) {
        try (ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oOut.writeObject(this);
        } catch (IOException e) {
            System.out.println("Error in method save: " + e.getMessage());
        }
    }
}
