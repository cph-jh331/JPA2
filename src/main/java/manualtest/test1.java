package manualtest;

import entities.Book;
import entities.EBook;
import entities.PaperBook;
import facades.Facade;
import java.util.List;
import javax.persistence.Persistence;

public class test1 {

    public static void main(String[] args)
    {
        Facade f = new Facade(Persistence.createEntityManagerFactory("devpu"));
        PaperBook pb1 = new PaperBook(50, 20, "12908310283", "Den Gale Fynbo", "Jens Jensen", 250);
        f.addPaperBook(pb1);
        pb1 = f.addPaperBook(new PaperBook(20, 50, "2034290340923", "DødensFisk", "Peter Bjarnesen", 340));
        System.out.println("added paper book" + pb1);
        pb1.setAuthor("Knud Jensen");
        pb1 = f.editPaperBook(pb1);
        System.out.println("edited paperbook" + pb1);
        System.out.println("deleted paperbook:" + f.deletePaperBook(pb1));
        System.out.println("EBOOK######################");
        EBook eb1 = new EBook("https://lala/bog.pdf", 140, "423423423", "Den Gamle Jyde", "Keld Keldsen", 200);
        f.addEBook(eb1);
        eb1 = f.addEBook(new EBook("https://lala2/bog2.pdf", 323, "938429384", "Jamen det er jo dejligt", "Harald Haraldsen", 300));
        System.out.println("added ebook: " + eb1);
        eb1.setAuthor("Frans Fransen");
        System.out.println("edited ebook: " + f.editEBook(eb1));
        System.out.println("deleted ebook: " + f.deleteEBook(eb1));
        List<Book> books = f.getAllBooks();
        for (Book book : books)
        {
            System.out.println(book);
        }

    }

}
