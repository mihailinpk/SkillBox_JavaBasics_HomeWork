package main.services;

import main.entities.Student;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    public Student parseLineToStudent(String line) {
        String[] splittedLine = line.split(",", 3);
        List<String> coursesList = Arrays.asList(splittedLine[2].split(","));
        return new Student(splittedLine[0], Integer.parseInt(splittedLine[1]), coursesList);
    }

    public Document parseStudentToDocument(Student student) {
        Document document = new Document(
                Map.of(
                    "name", student.getName(),
                    "age", student.getAge(),
                    "courses", student.getCourses()
                )
        );
        return document;
    }

    public Student parseDocumentToStudent(Document document)    {
        return new Student(
            (String) document.get("name"),
            (Integer) document.get("age"),
            (ArrayList<String>) document.get("courses")
        );
    }

}