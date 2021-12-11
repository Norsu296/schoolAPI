package pl.kuba.school.service.serviceImpl;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kuba.school.dto.mapper.StudentMapper;
import pl.kuba.school.dto.mapper.TeacherMapper;
import pl.kuba.school.dto.model.StudentDTO;
import pl.kuba.school.exception.ControllerError;
import pl.kuba.school.exception.ControllerException;
import pl.kuba.school.repository.StudentRepository;
import pl.kuba.school.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;

    @Override
    public Page<StudentDTO> findAll(Pageable pageable) {
        return studentRepository.findAllByDeletedFalse(pageable)
                .map(studentMapper::toStudentDTO);
    }

    @Override
    public List<StudentDTO> findAllByTeacherSurname(String surname) {
        return studentRepository.findAllStudentsByTeachersSurname(surname)
                .stream()
                .map(studentMapper::toStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> findAllByStudentSurname(String surname) {
        return studentRepository.findAllBySurname(surname)
                .stream()
                .map(studentMapper::toStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO findById(Long id) {
        return studentMapper.toStudentDTO(studentRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ControllerException(ControllerError.NOT_FOUND)));
    }

    @Override
    public StudentDTO create(StudentDTO student) {
        validateStudentEmailExists(student);
        return studentMapper.toStudentDTO(studentRepository.save(studentMapper.toStudent(student)));
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDTO update(Long id, StudentDTO student) {
        return studentMapper.toStudentDTO(studentRepository.findByIdAndDeletedFalse(id)
                .map(studentFromDb -> {
                    if (!studentFromDb.getEmail().equals(student.getEmail()) && studentRepository.existsByEmail(student.getEmail())) {
                        throw new ControllerException(ControllerError.EMAIL_ALREADY_EXISTS);
                    }
                    studentFromDb.setName(student.getName());
                    studentFromDb.setSurname(student.getSurname());
                    studentFromDb.setAge(student.getAge());
                    studentFromDb.setEmail(student.getEmail());
                    studentFromDb.setMajor(student.getMajor());
                    studentFromDb.setTeachers(teacherMapper.toTeachers(student.getTeachers()));
                    return studentRepository.save(studentFromDb);
                }).orElseThrow(() -> new ControllerException(ControllerError.NOT_FOUND)));
    }


    private void validateStudentEmailExists(StudentDTO student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new ControllerException(ControllerError.EMAIL_ALREADY_EXISTS);
        }
    }
}
