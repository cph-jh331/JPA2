package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
Kan ikke huske hvordan man få den til at lave den her klasse med det rigtige navn uden at den skriger af mig.
 */
@Entity
@Table(name = "ORDERS")
public class Ordre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false)
    private Customer customer;
    @OneToMany(mappedBy = "ordre")
    @JoinColumn(nullable = false)
    List<OrderLine> orderLines = new ArrayList<>();

    public Ordre()
    {
    }

    public Ordre(Customer customer)
    {
        this.customer = customer;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "entities.Ordre[ id=" + id + " ]";
    }

    public boolean addOrderLine(OrderLine orderLine)
    {
        return this.orderLines.add(orderLine);
    }

    public List<OrderLine> getOrderLines()
    {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines)
    {
        this.orderLines = orderLines;
    }

    public boolean doesOrderlineWithItemTypeExist(OrderLine orderLine)
    {
        for (OrderLine ol : orderLines)
        {
            if (ol.getItem().getName().equals(orderLine.getItem().getName()))
            {
                return true;
            }
        }
        return false;
    }

    public void findOrderLineAndUpdate(OrderLine orderLine)
    {
        for (OrderLine orderLine1 : orderLines)
        {
            if (orderLine.getId() == orderLine1.getId())
            {
                orderLine1 = orderLine;
            }
        }
    }

    public OrderLine findTypeInOrder(ItemType item)
    {
        for (OrderLine orderLine : orderLines)
        {
            if (orderLine.getItem().getName().equals(item.getName()))
            {
                return orderLine;
            }
        }
        return null;
    }

    public double getTotalPrice()
    {
        double sum = 0;
        for (OrderLine orderLine : orderLines)
        {
            sum += orderLine.getQuantity() * orderLine.getItem().getPrice();
        }
        return sum;
    }

}
