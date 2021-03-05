package su.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "purchaselist")
public class Purchase {

    @AttributeOverride(name = "studentName", column = @Column(name = "student_name"))
    @AttributeOverride(name="courseName", column = @Column(name = "course_name"))
    @EmbeddedId
    private PurchaseKey purchaseKey = new PurchaseKey();

    @Column(name = "price", nullable = true)
    Integer price;

    @Column(name = "subscription_date", nullable = true)
    Date subscriptionDate;

    public PurchaseKey getPurchaseKey() {
        return purchaseKey;
    }

    public void setPurchaseKey(PurchaseKey purchaseKey) {
        this.purchaseKey = purchaseKey;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(purchaseKey, purchase.purchaseKey) &&
                Objects.equals(price, purchase.price) &&
                Objects.equals(subscriptionDate, purchase.subscriptionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseKey, price, subscriptionDate);
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "studentName=" + purchaseKey.getStudentName() +
                ", courseName=" + purchaseKey.getCourseName() +
                ", price=" + price +
                ", subscriptionDate=" + subscriptionDate +
                '}';
    }

}