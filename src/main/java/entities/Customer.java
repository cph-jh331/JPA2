package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMERS")
@NamedQueries(
        {
            @NamedQuery(name = "Customer.findPersonEmail", query = "SELECT DISTINCT c FROM Customer c WHERE c.email =:email")
        })
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @OneToMany(mappedBy = "customer")
    List<Ordre> orders = new ArrayList<>();

    public Customer()
    {
    }

    public Customer(String name, String email)
    {
        this.name = name;
        this.email = email;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public List<Ordre> getOrders()
    {
        return orders;
    }

    public boolean addOrder(Ordre order)
    {
        return this.orders.add(order);
    }

    public void setOrders(List<Ordre> orders)
    {
        this.orders = orders;
    }

    @Override
    public String toString()
    {
        return "entities.Customer[ id=" + id + " ]";
    }

}
