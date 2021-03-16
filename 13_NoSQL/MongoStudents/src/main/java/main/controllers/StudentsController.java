package main.controllers;

import com.mongodb.client.FindIterable;
import main.entities.Student;
import main.repositories.StudentsRepository;
import main.services.StudentService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentsController {

    StudentsRepository repository;

    StudentService service;

    @Autowired
    public void setRepository(StudentsRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setService(StudentService service) {
        this.service = service;
    }

    public void setLinesFromSourceFile(List<String> linesFromFile)  {
        for(String line : linesFromFile)    {
            Student currentStudent = service.parseLineToStudent(line);
            repository.insertDocument(service.parseStudentToDocument(currentStudent));
        }
    }

    public long getTotalNumberOfStudents()  {
        return repository.getTotalNumberOfDocuments();
    }

    public List<Student> getStudentsOverForty() {
        FindIterable<Document> listOfFound = repository.getFindingResultByConditionalOperator("age", "$gt", 40);
        List<Student> resultList = new ArrayList<>();
        for(Document document: listOfFound) {
            resultList.add(service.parseDocumentToStudent(document));
        }
        return resultList;
    }

    public long getNumberOfStudentsOverForty()  {
        return repository.getCountResultsByConditionalOperator("age", "$gt", 40);
    }

    public String getNameOfYoungestStudent()    {
        Document document = repository.getSingleDocumentBySort("name", "age", 1);
        return document.getString("name");
    }

    public List<String> getCoursesOfOldestStudent() {
        Document document = repository.getSingleDocumentBySort("courses", "age", -1);
        return (List<String>) document.get("courses");
    }

    public void dropCollection()    {
        repository.dropCollection();
    }

}
