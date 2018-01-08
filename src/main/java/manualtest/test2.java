package manualtest;

import entities.Book;
import entities.PaperBook;
import facades.Facade;
import java.util.List;
import javax.persistence.Persistence;

public class test2 {

    public static void main(String[] args)
    {
        //BookFacade test
        Facade f = new Facade(Persistence.createEntityManagerFactory("devpu"));
        //create paperbook:
        f.addBook(new PaperBook(403, 93, "2938489234", "Bornholmsk Vin", "Jens Knudsen", 200));
        Book b1 = new PaperBook(80, 20, "34239423493", "Franskmandens Vin", "Prinsgemalen", 349);
        b1 = f.addBook(b1);
        System.out.println("added book:" + b1);
        b1.setAuthor("Olga Olgasen");
        b1 = f.editBook(b1);
        PaperBook pb = (PaperBook) f.getBook("34239423493");
        pb.setInStock(999);
        System.out.println("edited book: " + b1);
        System.out.println("edited paperbook:" + f.editBook(pb));
        System.out.println("deletede book:" + f.deleteBook("34239423493"));
        List<Book> books = f.getAllBooks();
        for (Book book : books)
        {
            System.out.println(book);
        }
    }

}
