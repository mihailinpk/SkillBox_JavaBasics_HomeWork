import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import su.entities.Course;
import su.entities.Student;

import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        try {

            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

            Session session = sessionFactory.openSession();

            List<Course> courses = session.createQuery("FROM Course").list();

            System.out.println("Связи courses-teachers (many-to-one)");
            for (Course course : courses)   {
                System.out.println(stringAligning(course.getName(), 34) + " | " +
                        course.getStudentsCount());
            }
            System.out.println("\n=== Связи students-courses (many-to-many): ===");
            for (Course course : courses)   {
                Set<Student> students = course.getStudents();
                System.out.println("---=== " + course.getName() + " ===---");
                for (Student student : students)    {
                    System.out.println(student.getName());
                }
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