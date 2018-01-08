/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Book;
import entities.PaperBook;
import java.util.HashMap;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Bloch
 */
public class IBookFacadeTest {

    private EntityManagerFactory emf;
    private IBookFacade bf;
    private String PU = "devpu";

    public IBookFacadeTest()
    {
    }

    @Before
    public void setUp()
    {
        System.out.println("Set up");
        emf = Persistence.createEntityManagerFactory(PU);
        bf = new BookFacade(emf);
        bf.addBook(new PaperBook(100, 100, "100", "100", "100", 100));
        bf.addBook(new PaperBook(200, 200, "200", "200", "200", 200));
        bf.addBook(new PaperBook(300, 300, "300", "300", "300", 300));
    }

    @After
    public void tearDown()
    {
        System.out.println("TearDown");
        emf.close();
        Persistence.generateSchema(PU, new HashMap());

    }

    /**
     * Test of addBook method, of class IBookFacade.
     */
    @Test
    public void testAddBook()
    {
        System.out.println("addBook");
        Book expResultBook = new PaperBook(400, 400, "400", "400", "400", 400);
        Book resultBook = bf.addBook(expResultBook);
        assertEquals(expResultBook.getAuthor(), resultBook.getAuthor());
    }

    /**
     * Test of getBook method, of class IBookFacade.
     */
    @Test
    public void testGetBook()
    {
        System.out.println("getBook");
        String isbn = "100";
        Book expResult = new PaperBook(100, 100, isbn, "100", "100", 100);
        Book result = bf.getBook(isbn);
        assertEquals(expResult.getAuthor(), result.getAuthor());
    }

    /**
     * Test of editBook method, of class IBookFacade.
     */
    @Test
    public void testEditBook()
    {
        System.out.println("editBook");
        Book book = bf.getBook("100");
        String author = "900";
        book.setAuthor(author);
        Book result = bf.editBook(book);
        assertEquals(author, result.getAuthor());
    }

    /**
     * Test of deleteBook method, of class IBookFacade.
     */
    @Test
    public void testDeleteBook()
    {
        System.out.println("deleteBook");
        String isbn = "100";
        Book expResult = new PaperBook(100, 100, "100", "100", "100", 100);
        Book result = bf.deleteBook(isbn);
        assertEquals(expResult.getAuthor(), result.getAuthor());
    }

    /**
     * Test of getAllBooks method, of class IBookFacade.
     */
    @Test
    public void testGetAllBooks()
    {
        System.out.println("getAllBooks");
        int expResult = 3;
        int result = bf.getAllBooks().size();
        assertEquals(expResult, result);
    }
}
