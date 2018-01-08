/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderLine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int quantity;

    @ManyToOne    
    private ItemType item;
    @ManyToOne
    private Ordre ordre;

    public OrderLine()
    {
    }

    public OrderLine(int quantity, Ordre orderId, ItemType item)
    {
        this.quantity = quantity;
        this.ordre = orderId;
        this.item = item;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public Ordre getOrdre()
    {
        return ordre;
    }

    public void setOrdre(Ordre ordre)
    {
        this.ordre = ordre;
    }

    public ItemType getItem()
    {
        return item;
    }

    public void setItem(ItemType item)
    {
        this.item = item;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "OrderLine{" + "id=" + id + ", quantity=" + quantity + ", orderId=" + ordre + ", item=" + item + '}';
    }

}
