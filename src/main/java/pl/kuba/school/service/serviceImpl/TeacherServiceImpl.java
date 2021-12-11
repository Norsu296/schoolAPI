package pl.kuba.school.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kuba.school.dto.mapper.StudentMapper;
import pl.kuba.school.dto.mapper.TeacherMapper;
import pl.kuba.school.dto.model.TeacherDTO;
import pl.kuba.school.exception.ControllerError;
import pl.kuba.school.exception.ControllerException;
import pl.kuba.school.repository.TeacherRepository;
import pl.kuba.school.service.TeacherService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final StudentMapper studentMapper;

    @Override
    public Page<TeacherDTO> findAll(Pageable pageable) {
        return teacherRepository.findAllByDeletedFalse(pageable)
                .map(teacherMapper::toTeacherDTO);
    }

    @Override
    public List<TeacherDTO> findAllByStudentSurname(String surname) {
        return teacherRepository.findAllStudentsByStudentSurname(surname)
                .stream()
                .map(teacherMapper::toTeacherDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TeacherDTO> findAllByTeacherSurname(String surname) {
        return teacherRepository.findAllBySurname(surname)
                .stream()
                .map(teacherMapper::toTeacherDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDTO findById(Long id) {
        return teacherMapper.toTeacherDTO(teacherRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ControllerException(ControllerError.NOT_FOUND)));
    }

    @Override
    public TeacherDTO create(TeacherDTO teacher) {
        validateTeacherEmailExists(teacher);
        return teacherMapper.toTeacherDTO(teacherRepository.save(teacherMapper.toTeacher(teacher)));
    }

    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public TeacherDTO update(Long id, TeacherDTO teacher) {
        return teacherMapper.toTeacherDTO(teacherRepository.findByIdAndDeletedFalse(id)
                .map(teacherFromDb -> {
                    if (!teacherFromDb.getEmail().equals(teacher.getEmail()) && teacherRepository.existsByEmail(teacher.getEmail())) {
                        throw new ControllerException(ControllerError.EMAIL_ALREADY_EXISTS);
                    }
                    teacherFromDb.setName(teacher.getName());
                    teacherFromDb.setSurname(teacher.getSurname());
                    teacherFromDb.setAge(teacher.getAge());
                    teacherFromDb.setEmail(teacher.getEmail());
                    teacherFromDb.setSubject(teacher.getSubject());
                    teacherFromDb.setStudents(studentMapper.toStudents(teacher.getStudents()));
                    return teacherRepository.save(teacherFromDb);
                }).orElseThrow(() -> new ControllerException(ControllerError.NOT_FOUND)));
    }


    private void validateTeacherEmailExists(TeacherDTO teacher) {
        if (teacherRepository.existsByEmail(teacher.getEmail())) {
            throw new ControllerException(ControllerError.EMAIL_ALREADY_EXISTS);
        }
    }
}
