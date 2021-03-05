import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import su.entities.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {

            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Purchase> query = criteriaBuilder.createQuery(Purchase.class);
            Root<Purchase> root = query.from(Purchase.class);
            query.select(root);
            List<Purchase> purchaseList = session.createQuery(query).getResultList();
            for(Purchase purchase : purchaseList)   {

                Transaction transaction = session.beginTransaction();

                String currentStudentName = purchase.getPurchaseKey().getStudentName();
                CriteriaQuery<Student> currentStudentQuery = criteriaBuilder.createQuery(Student.class);
                Root<Student> currentStudentRoot = currentStudentQuery.from(Student.class);
                currentStudentQuery.select(currentStudentRoot).where(
                    criteriaBuilder.equal(currentStudentRoot.get("name"), currentStudentName)
                );
                Student currentStudent = session.createQuery(currentStudentQuery).getResultList().get(0);

                String currentCourseName = purchase.getPurchaseKey().getCourseName();
                CriteriaQuery<Course> currentCourseQuery = criteriaBuilder.createQuery(Course.class);
                Root<Course> currentCourseRoot = currentCourseQuery.from(Course.class);
                currentCourseQuery.select(currentCourseRoot).where(
                  criteriaBuilder.equal(currentCourseRoot.get("name"), currentCourseName)
                );
                Course currentCourse = session.createQuery(currentCourseQuery).getResultList().get(0);

                LinkedPurchase linkedPurchase = new LinkedPurchase();
                LinkedPurchaseKey linkedPurchaseKey = new LinkedPurchaseKey();
                linkedPurchaseKey.setStudentId(currentStudent.getId());
                linkedPurchaseKey.setCourseId(currentCourse.getId());
                linkedPurchase.setLinkedPurchaseKey(linkedPurchaseKey);
                linkedPurchase.setStudent(currentStudent);
                linkedPurchase.setCourse(currentCourse);
                linkedPurchase.setPrice(purchase.getPrice());
                linkedPurchase.setSubscriptionDate(purchase.getSubscriptionDate());

                session.save(linkedPurchase);
                session.flush();
                transaction.commit();

            }
            session.close();
            sessionFactory.close();

        }
        catch (Exception ex)    {
            ex.printStackTrace();
        }

    }

}