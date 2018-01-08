package facades;

import entities.Customer;
import entities.ItemType;
import entities.OrderLine;
import entities.Ordre;
import java.util.List;

public interface IFacade {

    Customer addCustomer(Customer cust);

    Customer getCustomer(String email);

    Customer getCustomer(int id);

    List<Customer> getAllCustomers();

    Ordre getOrder(int id);

    Ordre createOrder(Ordre order);

    ItemType addItem(ItemType itemType);

    OrderLine addOrderLine(OrderLine orderLine);

    OrderLine editOrderLine(OrderLine orderLine);

}
