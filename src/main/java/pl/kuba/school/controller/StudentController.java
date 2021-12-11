package pl.kuba.school.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Helper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.kuba.school.dto.model.StudentDTO;
import pl.kuba.school.entity.Student;
import pl.kuba.school.repository.StudentRepository;
import pl.kuba.school.service.StudentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public Page<StudentDTO> findAll(Pageable pageable) {
        return studentService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public StudentDTO findById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @GetMapping("/filter/teacher")
    public List<StudentDTO> findAllByTeacherSurname(@RequestParam String surname) {
        return studentService.findAllByTeacherSurname(surname);
    }

    @GetMapping("/search")
    public List<StudentDTO> findAllByStudentSurname(@RequestParam String surname) {
        return studentService.findAllByStudentSurname(surname);
    }

    @PostMapping
    public StudentDTO create(@Valid @RequestBody StudentDTO student) {
        return studentService.create(student);
    }

    @PutMapping("/{id}")
    public StudentDTO update(@PathVariable Long id, @Valid @RequestBody StudentDTO student) {
        return studentService.update(id, student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }

}
