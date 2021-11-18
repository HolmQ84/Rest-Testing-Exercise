package si.assignment2.students.exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String exception) {
        super("The student with the following ID couldn't be found: "+exception);
    }
}
