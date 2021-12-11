package pl.kuba.school.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.kuba.school.dto.model.TeacherDTO;

import java.util.List;

public interface TeacherService {

    Page<TeacherDTO> findAll(Pageable pageable);

    List<TeacherDTO> findAllByStudentSurname(String surname);

    List<TeacherDTO> findAllByTeacherSurname(String surname);

    TeacherDTO findById(Long id);

    TeacherDTO create(TeacherDTO teacher);

    void delete(Long id);

    TeacherDTO update(Long id, TeacherDTO teacher);


}
