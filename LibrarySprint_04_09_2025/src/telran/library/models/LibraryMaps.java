package telran.library.models;

import telran.library.entities.Book;
import telran.library.entities.Reader;
import telran.library.entities.enums.BooksReturnCode;

import static telran.library.entities.enums.BooksReturnCode.*;

import java.util.HashMap;
import java.util.Map;

public class LibraryMaps extends AbstractLibrary {
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
        return null;
    }

    @Override
    public BooksReturnCode addBookExemplars(long isbn, int amount) {
        return null;
    }

    @Override
    public Reader getReader(int readerId) {
        return readers.get(readerId);
    }

    @Override
    public Book getBookItem(long isbn) {
        return books.get(isbn);
    }
}
