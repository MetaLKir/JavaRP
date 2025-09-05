package telran.library.models;

import telran.library.entities.Book;
import telran.library.entities.Reader;
import telran.library.entities.enums.BooksReturnCode;
import telran.util.Persistable;

import static telran.library.entities.enums.BooksReturnCode.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class LibraryMaps extends AbstractLibrary implements Persistable {
    Map<Long, Book> books = new HashMap<>(); // key isbn
    Map<Integer, Reader> readers = new HashMap<>(); // key readerId

    @Override
    public BooksReturnCode addBookItem(Book book) {
        if (book.getPickPeriod() < minPickPeriod) return PICK_PERIOD_LESS_MIN;
        if (book.getPickPeriod() > maxPickPeriod) return PICK_PERIOD_GREATER_MAX;
        BooksReturnCode res =
                books.putIfAbsent(book.getIsbn(), book) == null ?
                        OK : BOOK_ITEM_EXISTS;
        return res;
    }

    @Override
    public BooksReturnCode addReader(Reader reader) {
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
    public void save(String fileName) {
        try (ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oOut.writeObject(this);
        } catch (IOException e) {
            System.out.println("Error in method save: " + e.getMessage());
        }
    }
}
