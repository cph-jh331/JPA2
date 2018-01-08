/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author Bloch
 */
@Entity
public class PaperBook extends Book implements Serializable {

    @Column(nullable = false)
    private double ShippingWeight;
    @Column(nullable = false)
    private int inStock;

    public PaperBook()
    {
    }

    public PaperBook(double ShippingWeight, int inStock, String ISBN, String title, String author, double price)
    {
        super(ISBN, title, author, price);
        this.ShippingWeight = ShippingWeight;
        this.inStock = inStock;
    }

    public double getShippingWeight()
    {
        return ShippingWeight;
    }

    public void setShippingWeight(double ShippingWeight)
    {
        this.ShippingWeight = ShippingWeight;
    }

    public int getInStock()
    {
        return inStock;
    }

    public void setInStock(int inStock)
    {
        this.inStock = inStock;
    }

    @Override
    public String toString()
    {
        return "PaperBook{" + "ISBN=" + super.getISBN() + ", Author=" + super.getAuthor() + ", ShippingWeight=" + ShippingWeight + ", inStock=" + inStock + '}';
    }

}
