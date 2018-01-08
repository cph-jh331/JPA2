package mains;

import entities.Customer;
import entities.OrderLine;
import entities.Ordre;
import facades.Facade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class test2 {

    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("devpu");
        Facade f = new Facade(emf);
        Customer cust = f.getCustomer(1);
        for (Ordre order : cust.getOrders())
        {
            System.out.println(order);
        }

        Ordre order = f.getOrder(1);
        for (OrderLine orderLine : order.getOrderLines())
        {
            System.out.println(orderLine);
        }
    }

}
