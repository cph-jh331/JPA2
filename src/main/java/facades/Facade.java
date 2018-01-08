package facades;

import entities.Book;
import entities.EBook;
import entities.PaperBook;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class Facade implements IPaperBookFacade, IEBookFacade, IBookFacade {

    private EntityManagerFactory emf;

    public Facade(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    private EntityManager getEntityManager()
    {
        return this.emf.createEntityManager();
    }

    @Override
    public PaperBook addPaperBook(PaperBook pb)
    {
        EntityManager em = getEntityManager();
        try
        {
            if (em.find(PaperBook.class, pb.getISBN()) != null)
            {
                return null;
            }
            em.getTransaction().begin();
            em.persist(pb);
            em.getTransaction().commit();
            return pb;
        } finally
        {
            em.close();
        }
    }

    @Override
    public PaperBook getPaperBook(String isbn)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(PaperBook.class, isbn);
        } finally
        {
            em.close();
        }
    }

    @Override
    public PaperBook editPaperBook(PaperBook pb)
    {
        EntityManager em = getEntityManager();
        try
        {
            PaperBook oldBook = em.find(PaperBook.class, pb.getISBN());
            if (oldBook == null)
            {
                return null;
            }
            em.getTransaction().begin();
            em.merge(pb);
            em.getTransaction().commit();
            return pb;
        } finally
        {
            em.close();
        }
    }

    @Override
    public PaperBook deletePaperBook(PaperBook pb)
    {
        EntityManager em = getEntityManager();
        try
        {
            //skal være aktiv
            PaperBook bookDelete = em.find(PaperBook.class, pb.getISBN());
            if (bookDelete == null)
            {
                return null;
            }
            em.getTransaction().begin();
            em.remove(bookDelete);
            em.getTransaction().commit();
            return bookDelete;
        } finally
        {
            em.close();
        }
    }

    @Override
    public PaperBook deletePaperBook(String isbn)
    {
        EntityManager em = getEntityManager();
        try
        {
            PaperBook b = em.find(PaperBook.class, isbn);
            if (b == null)
            {
                return null;
            }
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
    public EBook addEBook(EBook eb)
    {
        EntityManager em = getEntityManager();
        try
        {
            if (em.find(EBook.class, eb.getISBN()) != null)
            {
                return null;
            }
            em.getTransaction().begin();
            em.persist(eb);
            em.getTransaction().commit();
            return eb;
        } finally
        {
            em.close();
        }
    }

    @Override
    public EBook GetEBook(String isbn)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(EBook.class, isbn);
        } finally
        {
            em.close();
        }
    }

    @Override
    public EBook editEBook(EBook eb)
    {
        EntityManager em = getEntityManager();
        try
        {
            if (em.find(EBook.class, eb.getISBN()) == null)
            {
                return null;
            }
            em.getTransaction().begin();
            em.merge(eb);
            em.getTransaction().commit();
            return eb;
        } finally
        {
            em.close();
        }
    }

    @Override
    public EBook deleteEBook(EBook eb)
    {
        EntityManager em = getEntityManager();
        try
        {
            EBook deb = em.find(EBook.class, eb.getISBN());
            if (deb == null)
            {
                return null;
            }
            em.getTransaction().begin();
            em.remove(deb);
            em.getTransaction().commit();
            return deb;
        } finally
        {
            em.close();
        }
    }

    @Override
    public EBook deleteEBook(String isbn)
    {
        EntityManager em = getEntityManager();
        try
        {
            EBook b = em.find(EBook.class, isbn);
            if (b == null)
            {
                return null;
            }
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

}
