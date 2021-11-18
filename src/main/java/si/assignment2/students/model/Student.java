package si.assignment2.students.model;

import lombok.Data;

import javax.persistence.*;

// Calls Lombok to make getters and setters for the entity
@Data
// Tells JPA that it is a entity, so it builds it.
@Entity
public class Student {

    @Id
    private Long studentId;
    private String studentName;
    private int studentAge;
    private String studentMail;
    private String studentAddress;


    public Student() {
        super();
    }
}