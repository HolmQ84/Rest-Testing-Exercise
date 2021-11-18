package si.assignment2.students.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import si.assignment2.students.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
