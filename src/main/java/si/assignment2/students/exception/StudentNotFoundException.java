package si.assignment2.students.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class StudentNotFoundException extends ResponseStatusException {

    public StudentNotFoundException(HttpStatus notFound, String exception) {
        super(notFound, exception);
    }
}
