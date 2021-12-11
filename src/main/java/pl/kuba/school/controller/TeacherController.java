package pl.kuba.school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.kuba.school.dto.model.TeacherDTO;
import pl.kuba.school.service.TeacherService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public Page<TeacherDTO> findAll(Pageable pageable) {
        return teacherService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public TeacherDTO findById(@PathVariable Long id) {
        return teacherService.findById(id);
    }

    @GetMapping("/filter/student")
    public List<TeacherDTO> findAllByStudentSurname(@RequestParam String surname) {
        return teacherService.findAllByStudentSurname(surname);
    }

    @GetMapping("/search")
    public List<TeacherDTO> findAllByTeacherSurname(@RequestParam String surname) {
        return teacherService.findAllByTeacherSurname(surname);
    }

    @PostMapping
    public TeacherDTO create(@RequestBody @Valid TeacherDTO teacher) {
        return teacherService.create(teacher);
    }

    @PutMapping("/{id}")
    public TeacherDTO update(@PathVariable Long id, @RequestBody @Valid TeacherDTO teacher) {
        return teacherService.update(id, teacher);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        teacherService.delete(id);
    }


}
