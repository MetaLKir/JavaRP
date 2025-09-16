package telran.library.entities.enums;

public enum BooksReturnCode {
    OK,
    BOOK_ITEM_EXISTS,                 // addBookItem: книга с таким ISBN уже есть
    READER_EXISTS,                    // addReader: читатель с таким id уже есть
    NO_BOOK_ITEM,                     // addBookExemplars: книги с ISBN нет
    WRONG_BOOK_PICK_PERIOD,            // addBookItem: pickPeriod вне [min,max]
    PICK_PERIOD_LESS_MIN,
    PICK_PERIOD_GREATER_MAX,
    NO_READER,
    NO_BOOK_EXEMPLARS,
    WRONG_ISBN,
    INVALID_BOOK,
    INVALID_DRIVER,
    READER_READS_IT
}
