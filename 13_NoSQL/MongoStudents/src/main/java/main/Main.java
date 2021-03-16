package main;

import main.controllers.StudentsController;
import main.controllers.SourceFileController;
import main.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private SourceFileController sourceFileController;
    private StudentsController studentsController;

    @Autowired
    public void setSourceFileController(SourceFileController sourceFileController) {
        this.sourceFileController = sourceFileController;
    }

    @Autowired
    public void setStudentsController(StudentsController studentsController) {
        this.studentsController = studentsController;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        studentsController.setLinesFromSourceFile(sourceFileController.getLinesFromFile());

        System.out.println("-----------------------==============================-----------------------");

        System.out.println("1) Общее количество студентов в базе: " + studentsController.getTotalNumberOfStudents());

        System.out.println("\n2) Количество студентов старше 40-ка лет: " + studentsController.getNumberOfStudentsOverForty());
        System.out.println("Вот эти студенты:");
        List<Student> studentList = studentsController.getStudentsOverForty();
        studentList.forEach(System.out::println);

        System.out.println("\n3) Самый молодой студент: " + studentsController.getNameOfYoungestStudent());

        System.out.println("\n4) Список курсов самого старого студента:");
        for(String course: studentsController.getCoursesOfOldestStudent())  {
            System.out.print(course + " ");
        }
        System.out.println("\n-----------------------==============================-----------------------");

        studentsController.dropCollection();

    }

}