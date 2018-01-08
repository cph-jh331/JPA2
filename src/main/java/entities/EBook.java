package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author Bloch
 */
@Entity
public class EBook extends Book implements Serializable {

    @Column(nullable = false)
    private String downloadUrl;
    @Column(nullable = false)
    private double sizeMB;

    public EBook()
    {
    }

    public EBook(String downloadUrl, double sizeMB, String ISBN, String title, String author, double price)
    {
        super(ISBN, title, author, price);
        this.downloadUrl = downloadUrl;
        this.sizeMB = sizeMB;
    }

    public String getDownloadUrl()
    {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl)
    {
        this.downloadUrl = downloadUrl;
    }

    public double getSizeMB()
    {
        return sizeMB;
    }

    public void setSizeMB(double sizeMB)
    {
        this.sizeMB = sizeMB;
    }

    @Override
    public String toString()
    {
        return "EBook{" + "ISBN=" + super.getISBN() + ", Author=" + super.getAuthor() +", downloadUrl=" + downloadUrl + ", sizeMB=" + sizeMB + '}';
    }

}
