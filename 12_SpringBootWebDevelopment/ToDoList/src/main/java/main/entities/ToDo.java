package main.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "todolist")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "detailed_description")
    private String detailedDescription;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "deadline")
    private LocalDate deadline;

    public ToDo(Integer id, String shortDescription, String detailedDescription, LocalDate deadline) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.detailedDescription = detailedDescription;
        this.deadline = deadline;
    }

    public ToDo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return Objects.equals(id, toDo.id) &&
                Objects.equals(shortDescription, toDo.shortDescription) &&
                Objects.equals(detailedDescription, toDo.detailedDescription) &&
                Objects.equals(deadline, toDo.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shortDescription, detailedDescription, deadline);
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", shortDescription='" + shortDescription + '\'' +
                ", detailedDescription='" + detailedDescription + '\'' +
                ", deadline=" + deadline +
                '}';
    }

}