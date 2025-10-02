package telran.library.models;

import telran.library.entities.*;
import telran.library.entities.enums.BooksReturnCode;
import telran.util.Persistable;

import static telran.library.entities.enums.BooksReturnCode.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class LibraryMaps extends AbstractLibrary implements Persistable {
    private Map<Long, Book> books = new HashMap<>();            // key isbn
    private Map<Integer, Reader> readers = new HashMap<>();     // key readerId

    private Map<Integer, List<PickRecord>> readersRecords = new HashMap<>();    // readerId → выдачи
    private Map<Long, List<PickRecord>> booksRecords = new HashMap<>();         // isbn → выдачи
    private NavigableMap<LocalDate, List<PickRecord>> records = new TreeMap<>();         // дата выдачи → выдачи
    private Map<String, List<Book>> authorBooks = new HashMap<>();


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
        if (reader == null) return INVALID_READER;
        boolean readerIsAbsent = readers.putIfAbsent(reader.getReaderId(), reader) == null;
        return readerIsAbsent ? OK : READER_EXISTS;
    }

    @Override
    public BooksReturnCode addBookExemplars(long isbn, int amount) {
        Book book = getBookItem(isbn);
        if (book == null) return NO_BOOK_ITEM;
        int newAmount = book.getAmount() + amount;
        book.setAmount(Math.max(newAmount, 0));
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
        if (book.isRemoval()) return BOOK_SET_FOR_REMOVAL;

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
                filter(b -> b.getAmount() > b.getAmountInUse()). // only available books
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
    public RemovedBookData removeBook(long isbn) {
//        if (!Book.checkIsbnLength(isbn)) return null;
        Book bookToRemove = getBookItem(isbn);
        if (bookToRemove == null) return null;

        bookToRemove.setRemoval(true);
        boolean bookInUse = bookToRemove.getAmountInUse() > 0;
        return bookInUse ? new RemovedBookData(bookToRemove, null) : actualBookRemoval(bookToRemove);
    }

    private RemovedBookData actualBookRemoval(Book bookToRemove){
        long isbn = bookToRemove.getIsbn();
        String author = bookToRemove.getAuthor();
        books.remove(isbn);
        authorBooks.get(author).remove(bookToRemove);

        List<PickRecord> bookToRemoveRecords = booksRecords.getOrDefault(isbn, new ArrayList<>());
        RemovedBookData res = new RemovedBookData(bookToRemove, bookToRemoveRecords);
        if (bookToRemoveRecords.isEmpty()) return res;

        booksRecords.remove(isbn);
        bookToRemoveRecords.forEach(r -> {
            List<PickRecord> readerRecs = readersRecords.get(r.getReaderId());
            if (readerRecs != null) readerRecs.remove(r);
            List<PickRecord> dateRecs = records.get(r.getPickDate());
            if (dateRecs != null) dateRecs.remove(r);
        });
        return res;
    }

    @Override
    public List<RemovedBookData> removeAuthor(String author) {
        List<Book> bookToRemove = authorBooks.get(author).stream().
                filter(Objects::nonNull).
                toList();

        return bookToRemove.stream().
                map(b -> removeBook(b.getIsbn())).
                toList();
    }

    @Override
    public RemovedBookData returnBook(long isbn, int readerId, LocalDate returnDate) {
        PickRecord pickRec = getPickRecord(isbn, readerId);
        if (pickRec == null) return new RemovedBookData(null, null);

        Book returnedBook = getBookItem(isbn);
        returnedBook.setAmountInUse(returnedBook.getAmountInUse() - 1);

        pickRec.setReturnDate(returnDate);
        int pickDays = (int) ChronoUnit.DAYS.between(pickRec.getPickDate(), returnDate);
        int delay = pickDays - returnedBook.getPickPeriod();
        pickRec.setDelayDays(Math.max(delay, 0));

        if (returnedBook.isRemoval()) return removeBook(isbn);

        return new RemovedBookData(returnedBook, null);
    }

    @Override
    public List<ReaderDelay> getReadersDelayingBooks(LocalDate currentDate) {
        /*  Вернуть ReaderDelay с читателями,
            которые книгу ещё не сдали, но уже точно её задержали?
         */
        return readersRecords.values().stream().
                flatMap(Collection::stream).
                filter(r -> {
                    int bookPickPeriod = books.get(r.getIsbn()).getPickPeriod();
                    int period = (int) ChronoUnit.DAYS.between(r.getPickDate(), currentDate);
                    return period > bookPickPeriod && r.getReturnDate() == null;
                }).
                map(r -> {
                    int bookPickPeriod = books.get(r.getIsbn()).getPickPeriod();
                    int period = (int) ChronoUnit.DAYS.between(r.getPickDate(), currentDate);
                    int delay = period - bookPickPeriod;
                    return new ReaderDelay(readers.get(r.getReaderId()), delay);
                }).
                toList();
    }

    @Override
    public List<ReaderDelay> getReadersDelayedBooks() {
        // Вернуть все случаи, когда книга задерживалась читателем?
        return readersRecords.values().stream().
                flatMap(Collection::stream).
                filter(r -> r.getDelayDays() > 0).
                map(r -> new ReaderDelay(readers.get(r.getReaderId()), r.getDelayDays())).
                toList();
    }

    @Override
    public List<Book> getMostPopularBooks(LocalDate fromDate, LocalDate toDate, int fromAge, int toAge) {
        List<PickRecord> recordsFromTo = getPickedRecordsAtDates(fromDate, toDate);
        if (recordsFromTo.isEmpty()) return new ArrayList<>();
        // isbn, count
        Map<Long, Long> booksPopularity = recordsFromTo.stream().
                filter(r -> {
                    Reader reader = readers.get(r.getReaderId());
                    int readerAge = (int) ChronoUnit.YEARS.between(reader.getBirthDay(), r.getPickDate());
                    return readerAge >= fromAge && readerAge < toAge;
                }).
                collect(Collectors.groupingBy(PickRecord::getIsbn, Collectors.counting()));
        if (booksPopularity.isEmpty()) return new ArrayList<>();

        List<Book> res = new ArrayList<>();
        long highestPopularity = booksPopularity.values().stream().mapToLong(v -> v).max().getAsLong();
        booksPopularity.forEach((k, v) -> {
            if (v == highestPopularity) res.add(books.get(k));
        });
        return res;
    }

    @Override
    public List<String> getMostPopularAuthors() {
        if (booksRecords.isEmpty()) return new ArrayList<>();
        // isbn, count
        Map<String, Long> authorsPopularity = booksRecords.values().stream().
                flatMap(Collection::stream).
                collect(Collectors.groupingBy(r -> books.get(r.getIsbn()).getAuthor(), Collectors.counting()));

        List<String> res = new ArrayList<>();
        long highestPopularity = authorsPopularity.values().stream().mapToLong(v -> v).max().getAsLong();
        authorsPopularity.forEach((k, v) -> {
            if (v == highestPopularity) res.add(k);
        });
        return res;
    }

    @Override
    public List<Reader> getMostActiveReaders(LocalDate fromDate, LocalDate toDate) {
        List<PickRecord> recordsFromTo = getPickedRecordsAtDates(fromDate, toDate);
        if (recordsFromTo.isEmpty()) return new ArrayList<>();
        // readerId, count
        Map<Integer, Long> readersByActivity = recordsFromTo.stream().
                collect(Collectors.groupingBy(PickRecord::getReaderId, Collectors.counting()));

        List<Reader> res = new ArrayList<>();
        long highestActivity = readersByActivity.values().stream().mapToLong(v -> v).max().getAsLong();
        readersByActivity.forEach((k, v) -> {
            if (v == highestActivity) res.add(readers.get(k));
        });
        return res;
    }

    private PickRecord getPickRecord(long isbn, int readerId) {
        List<PickRecord> readerRecs = readersRecords.get(readerId);
        if (readerRecs == null) return null;
        return readerRecs.stream().
                filter(r -> r.getIsbn() == isbn && r.getReturnDate() == null).
                findFirst().orElse(null);
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
