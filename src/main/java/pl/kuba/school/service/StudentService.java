package pl.kuba.school.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.kuba.school.dto.model.StudentDTO;

import java.util.List;


public interface StudentService {

    Page<StudentDTO> findAll(Pageable pageable);

    List<StudentDTO> findAllByTeacherSurname(String surname);

    List<StudentDTO> findAllByStudentSurname(String surname);

    StudentDTO findById(Long id);

    StudentDTO create(StudentDTO student);

    void delete(Long id);

    StudentDTO update(Long id, StudentDTO student);
}
