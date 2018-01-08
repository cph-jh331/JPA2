package facades;

import entities.Customer;
import entities.ItemType;
import entities.OrderLine;
import entities.Ordre;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class Facade implements IFacade {

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
    public Customer addCustomer(Customer cust)
    {
        EntityManager em = getEntityManager();
        try
        {
            if (getCustomer(cust.getEmail()) != null)
            {
                return null;
            }
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
            return cust;

        } finally
        {
            em.close();
        }
    }

    @Override
    public Customer getCustomer(String email)
    {
        EntityManager em = getEntityManager();
        try
        {
            Query q = em.createNamedQuery("Customer.findPersonEmail");
            q.setParameter("email", email);
            return (Customer) q.getSingleResult();

        } catch (NoResultException ex)
        {
            return null;
        } finally
        {
            em.close();
        }
    }

    @Override
    public Customer getCustomer(int id)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(Customer.class, id);
        } finally
        {
            em.close();
        }
    }

    @Override
    public List<Customer> getAllCustomers()
    {
        EntityManager em = getEntityManager();
        try
        {
            Query q = em.createQuery("SELECT c FROM Customer c");
            return q.getResultList();

        } finally
        {
            em.close();
        }
    }

    @Override
    public Ordre getOrder(int id)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(Ordre.class, id);
        } finally
        {
            em.close();
        }
    }

    // Den her giver IKKE ordre med et id tilbage...     
    public Ordre addOrderCustomer(Ordre order)
    {
        EntityManager em = getEntityManager();
        try
        {
            em.getTransaction().begin();
            Customer cust = em.find(Customer.class, order.getCustomer().getId());
            if (cust == null)
            {
                return null;
            }
            cust.addOrder(order);
            em.merge(cust);
            em.getTransaction().commit();
            return order;

        } finally
        {
            em.close();
        }
    }

    //opdaterer både cust orderlist og giver et order id tilbage lige når den er lavet.
    @Override
    public Ordre createOrder(Ordre order)
    {
        EntityManager em = getEntityManager();
        try
        {
            Customer cust = em.find(Customer.class, order.getCustomer().getId());
            if (cust == null)
            {
                return null;
            }
            cust.addOrder(order);
            em.getTransaction().begin();
            em.persist(order);
            em.getTransaction().commit();
            return order;

        } finally
        {
            em.close();
        }
    }

    @Override
    public ItemType addItem(ItemType itemType)
    {
        EntityManager em = getEntityManager();
        try
        {
            if (em.find(ItemType.class, itemType.getName()) != null)
            {
                return null;
            }

            em.getTransaction().begin();
            em.persist(itemType);
            em.getTransaction().commit();
            return itemType;
        } finally
        {
            em.close();
        }
    }

    @Override
    public OrderLine editOrderLine(OrderLine orderLine)
    {
        EntityManager em = getEntityManager();
        try
        {
            OrderLine oldOrderLine = em.find(OrderLine.class, orderLine.getId());
            if (oldOrderLine == null)
            {
                return null;
            }
            em.getTransaction().begin();
            em.merge(orderLine);
            em.getTransaction().commit();
            return orderLine;

        } finally
        {
            em.close();
        }
    }

    //lavet 
    private OrderLine updateOrderline(OrderLine oldOrderline, OrderLine orderLine)
    {
        EntityManager em = getEntityManager();
        try
        {
            Ordre order = em.find(Ordre.class, oldOrderline.getOrdre().getId());
            if (order == null)
            {
                return null;
            }
            orderLine.setId(oldOrderline.getId());
            order.findOrderLineAndUpdate(orderLine);
            em.getTransaction().begin();
            em.merge(orderLine);
            em.getTransaction().commit();
            return orderLine;

        } finally
        {
            em.close();
        }
    }

    @Override
    public OrderLine addOrderLine(OrderLine orderLine)
    {
        EntityManager em = getEntityManager();
        try
        {
            Ordre ord = em.find(Ordre.class, orderLine.getOrdre().getId());
            if (ord == null)
            {
                return null;
            }
            //behøves ikke, bare lavet for prøve at forstå, hvordan det virker.
            OrderLine ol = ord.findTypeInOrder(orderLine.getItem());
            if (ol != null)
            {
                System.out.println("Item already exist in orderlist");
                return updateOrderline(ol, orderLine);
            }

            ord.addOrderLine(orderLine);
            em.getTransaction().begin();
            em.persist(orderLine);
            em.getTransaction().commit();
            return orderLine;
        } finally
        {
            em.close();
        }
    }

}
