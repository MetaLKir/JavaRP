package telran.library.models;

import telran.library.entities.*;
import telran.library.entities.enums.BooksReturnCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public interface ILibrary extends Serializable {
    // sprint 1
    BooksReturnCode addBookItem(Book book);

    BooksReturnCode addReader(Reader reader);

    BooksReturnCode addBookExemplars(long isbn, int amount);

    Reader getReader(int readerId);

    Book getBookItem(long isbn);

    // sprint 2
    BooksReturnCode pickBook(long isbn, int readerId, LocalDate pickDate);

    List<Book> getBooksPickedByReader(int readerId);

    List<Reader> getReadersPickedBook(long isbn);

    List<Book> getBooksAuthor(String authorName);

    List<PickRecord> getPickedRecordsAtDates(LocalDate from, LocalDate to);
}
