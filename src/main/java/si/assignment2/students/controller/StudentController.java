package si.assignment2.students.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import si.assignment2.students.exception.StudentNotFoundException;
import si.assignment2.students.model.Student;
import si.assignment2.students.repository.StudentRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentRepository repository;

    @GetMapping("/")
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<Student> retrieveStudent(@PathVariable long id)
    {
        Optional<Student> student = repository.findById(id);
        if (student.isEmpty()) {
            throw new StudentNotFoundException("id: " + id);
        }
        EntityModel<Student> resource = EntityModel.of(student.get());
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllStudents());
        resource.add(linkTo.withRel("all-students"));

        Link selfLink = linkTo(methodOn(this.getClass()).retrieveStudent(id)).withSelfRel();
        resource.add(selfLink);
        return resource;
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable long id) {
        repository.deleteById(id);
    }

    // Create a new resource and remember its unique location in the hypermedia
    @PostMapping("/")
    public ResponseEntity<Object> createStudent(@RequestBody Student student)
    {
        Student savedStudent = repository.save(student);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedStudent.getStudentId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id)
    {
        Optional<Student> studentOptional = repository.findById(id);
        if (studentOptional.isEmpty())
            return ResponseEntity.notFound().build();
        student.setStudentId(id);
        repository.save(student);
        return ResponseEntity.noContent().build();
    }
}
