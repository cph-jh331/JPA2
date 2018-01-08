package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ItemType implements Serializable {

    @Id
    private String name;
    private String descript;
    @Column(nullable = false)
    private double price;
    @OneToMany(mappedBy = "item")
    private List<OrderLine> orderLines = new ArrayList<>();

    public ItemType()
    {
    }

    public ItemType(String name, String descript, double price)
    {
        this.name = name;
        this.descript = descript;
        this.price = price;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescript()
    {
        return descript;
    }

    public void setDescript(String descript)
    {
        this.descript = descript;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public List<OrderLine> getOrderLines()
    {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines)
    {
        this.orderLines = orderLines;
    }

    @Override
    public String toString()
    {
        return "ItemType{" + "name=" + name + ", descript=" + descript + ", price=" + price + '}';
    }
}
