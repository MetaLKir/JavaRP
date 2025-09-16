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
    private NavigableMap<LocalDate, List<PickRecord>> records = new TreeMap<>();         // дата выдачи → выдачи
    private Map<String, List<Book>> authorBooks = new HashMap();


    @Override
    public BooksReturnCode addBookItem(Book book) {
        if (book == null) return INVALID_BOOK;
        if (book.getPickPeriod() < minPickPeriod) return PICK_PERIOD_LESS_MIN;
        if (book.getPickPeriod() > maxPickPeriod) return PICK_PERIOD_GREATER_MAX;
//        BooksReturnCode res =
//                books.putIfAbsent(book.getIsbn(), book) == null ?
//                        OK : BOOK_ITEM_EXISTS;
        if (books.putIfAbsent(book.getIsbn(), book) != null) return BOOK_ITEM_EXISTS;
        addAuthorBooks(book);
        return OK;
    }

    private void addAuthorBooks(Book book) {
        String key = book.getAuthor();
        List<Book> list = authorBooks.computeIfAbsent(key, k -> new ArrayList<>());
        boolean exists = list.stream().anyMatch(b -> b.getIsbn() == book.getIsbn());
        if (!exists) list.add(book);
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
//        if(!Book.checkIsbnLength(isbn)) return WRONG_ISBN;
        Book book = getBookItem(isbn);
        if (book == null || book.getAmount() < 0) return NO_BOOK_ITEM;
        if (book.getAmount() <= book.getAmountInUse()) return NO_BOOK_EXEMPLARS;

        Reader reader = getReader(readerId);
        if (reader == null) return NO_READER;

        // do not give the book if the same book is already picked by this reader (1 exemplar in one hands at a time)
        List<PickRecord> pickRecords = readersRecords.get(readerId);
        if (pickRecords != null
                && pickRecords.stream().anyMatch(r -> r.getIsbn() == isbn
                && r.getReturnDate() == null))
            return READER_READS_IT;

        PickRecord record = new PickRecord(isbn, readerId, pickDate);
        book.setAmountInUse(book.getAmountInUse() + 1);
        addToMap(booksRecords, record.getIsbn(), record);
        addToMap(readersRecords, record.getReaderId(), record);
        addToMap(records, record.getPickDate(), record);

        return OK;
    }

    private <K, V> void addToMap(Map<K, List<V>> map, K key, V value) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
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

        List<Book> list = authorBooks.getOrDefault(authorName, new ArrayList<>());
        return list.stream().
                filter(b -> b.getAmount() < b.getAmountInUse()). // only available books
                        toList();
    }

    @Override
    public List<PickRecord> getPickedRecordsAtDates(LocalDate from, LocalDate to) {
        Collection<List<PickRecord>> colRecords = records.subMap(from, to).values();
        return colRecords == null ? new ArrayList<>() :
                colRecords.stream().
                        flatMap(List::stream).
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
