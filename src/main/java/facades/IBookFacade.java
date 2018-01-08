package facades;

import entities.Book;
import java.util.List;

public interface IBookFacade {
    
    Book addBook(Book book);
    Book getBook(String isbn);
    Book editBook(Book book);
    Book deleteBook(String isbn);
    List<Book> getAllBooks();

}
