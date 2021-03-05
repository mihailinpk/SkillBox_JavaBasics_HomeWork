package su.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "linked_purchaselist")
public class LinkedPurchase {

    @EmbeddedId
    private LinkedPurchaseKey linkedPurchaseKey = new LinkedPurchaseKey();

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("studentId")
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = true, columnDefinition = "INT(10) UNSIGNED")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("courseId")
    @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = true, columnDefinition = "INT(10) UNSIGNED")
    private Course course;

    @Column(name = "price", nullable = true)
    private Integer price;

    @Column(name = "subscription_date", nullable = true)
    private Date subscriptionDate;

    public LinkedPurchaseKey getLinkedPurchaseKey() {
        return linkedPurchaseKey;
    }

    public void setLinkedPurchaseKey(LinkedPurchaseKey linkedPurchaseKey) {
        this.linkedPurchaseKey = linkedPurchaseKey;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
        LinkedPurchase that = (LinkedPurchase) o;
        return Objects.equals(linkedPurchaseKey, that.linkedPurchaseKey) &&
                Objects.equals(student, that.student) &&
                Objects.equals(course, that.course) &&
                Objects.equals(price, that.price) &&
                Objects.equals(subscriptionDate, that.subscriptionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linkedPurchaseKey, student, course, price, subscriptionDate);
    }

    @Override
    public String toString() {
        return "LinkedPurchase{" +
                "linkedPurchaseKey=" + linkedPurchaseKey +
                ", student=" + student +
                ", course=" + course +
                ", price=" + price +
                ", subscriptionDate=" + subscriptionDate +
                '}';
    }

}