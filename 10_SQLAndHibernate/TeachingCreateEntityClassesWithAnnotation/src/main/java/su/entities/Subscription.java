package su.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @EmbeddedId
    private SubscriptionKey subscriptionKey = new SubscriptionKey();

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("studentId")
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = true)
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("courseId")
    @JoinColumn(name = "course_id", referencedColumnName =  "id", nullable = true)
    private Course course;

    @Column(name = "subscription_date", nullable = true)
    Date subscriptionDate;

    public SubscriptionKey getSubscriptionKey() {
        return subscriptionKey;
    }

    public void setSubscriptionKey(SubscriptionKey subscriptionKey) {
        this.subscriptionKey = subscriptionKey;
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
        Subscription that = (Subscription) o;
        return Objects.equals(subscriptionKey, that.subscriptionKey) &&
                Objects.equals(student, that.student) &&
                Objects.equals(course, that.course) &&
                Objects.equals(subscriptionDate, that.subscriptionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subscriptionKey, student, course, subscriptionDate);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "subscriptionKey=" + subscriptionKey +
                ", student=" + student +
                ", course=" + course +
                ", subscriptionDate=" + subscriptionDate +
                '}';
    }

}