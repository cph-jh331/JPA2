package mains;

import entities.Customer;
import entities.ItemType;
import entities.OrderLine;
import entities.Ordre;
import facades.Facade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class testing {

    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("devpu");
        Facade f = new Facade(emf);
        Customer cust = new Customer("Jens", "enEmail@for.dk");
        cust = f.addCustomer(cust);
        System.out.println(cust);
        for (int i = 0; i < 2; i++)
        {
            Ordre order = new Ordre(cust);
            System.out.println(f.createOrder(order));
        }
        cust = f.getCustomer(1);
        System.out.println("okså");
        for (Ordre order : cust.getOrders())
        {
            System.out.println("custlist:" + order);
        }

        Ordre order = f.getOrder(1);
        ItemType item = new ItemType("Ged", "Geden er glad", 50);
        item = f.addItem(item);
        OrderLine ol = new OrderLine(10, order, item);
        f.addOrderLine(ol);
        ol = new OrderLine(15, order, item);
        ol = f.addOrderLine(ol);
        System.out.println(ol);
        System.out.println("hvad");
        order = f.getOrder(1);
        for (OrderLine orderLine : order.getOrderLines())
        {
            System.out.println("order:" + orderLine);
        }

        System.out.println("Total price:" + order.getTotalPrice());

    }

}
