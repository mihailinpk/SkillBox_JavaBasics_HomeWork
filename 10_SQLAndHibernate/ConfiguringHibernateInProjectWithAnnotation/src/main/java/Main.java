import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import su.entities.Course;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {

            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

            Session session = sessionFactory.openSession();

            List<Course> courses = session.createQuery("FROM Course").list();

            for (Course course : courses)   {
                System.out.println(
                    stringAligning(course.getName(), 34) + " | " +
                    stringAligning(String.valueOf(course.getStudentsCount()), 5) + " | " +
                    course.getType()
                );
            }

            session.close();

        }
        catch (Exception ex)    {
            ex.printStackTrace();
        }

    }

    private static String stringAligning(String stringToAlign, int lengthAlignment) {
        while(stringToAlign.length() < lengthAlignment)  {
            stringToAlign = stringToAlign + " ";
        }
        return stringToAlign;
    }

}