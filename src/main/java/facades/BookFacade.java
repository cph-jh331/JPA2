package facades;

import entities.Book;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class BookFacade implements IBookFacade {

    private EntityManagerFactory emf;

    public BookFacade(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    private EntityManager getEntityManager()
    {
        return this.emf.createEntityManager();
    }

    @Override
    public Book addBook(Book book)
    {
        //har lavet book abstract, så du ikke kan instantiere den og fucke op.
        EntityManager em = getEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
            return book;
        } finally
        {
            em.close();
        }
    }

    @Override
    public Book getBook(String isbn)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(Book.class, isbn);
        } finally
        {
            em.close();
        }
    }

    @Override
    public Book editBook(Book book)
    {
        EntityManager em = getEntityManager();
        try
        {
            Book oldBook = em.find(Book.class, book.getISBN());
            if (oldBook == null)
            {
                return null;
            }
            em.getTransaction().begin();
            em.merge(book);
            em.getTransaction().commit();
            return book;
        } finally
        {
            em.close();
        }
    }

    @Override
    public Book deleteBook(String isbn)
    {
        EntityManager em = getEntityManager();
        try
        {
            Book b = em.find(Book.class, isbn);
            em.getTransaction().begin();
            em.remove(b);
            em.getTransaction().commit();
            return b;
        } finally
        {
            em.close();
        }
    }

    @Override
    public List<Book> getAllBooks()
    {
        EntityManager em = getEntityManager();
        try
        {
            //descriminator column autogeneres i database, og holder styr på hvilken
            //type bog det er.
            Query q = em.createQuery("SELECT b FROM Book b");
            return q.getResultList();
        } finally
        {
            em.close();
        }
    }

}
