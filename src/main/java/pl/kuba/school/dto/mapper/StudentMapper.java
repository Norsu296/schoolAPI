package pl.kuba.school.dto.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import pl.kuba.school.dto.model.StudentDTO;
import pl.kuba.school.dto.model.StudentToTeacherDTO;
import pl.kuba.school.entity.Student;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TeacherMapper.class})
public interface StudentMapper {

    StudentDTO toStudentDTO(Student student);

    List<StudentDTO> toStudentDTOs(Page<Student> students);


    List<Student> toStudents(List<StudentToTeacherDTO> studentToTeacherDTOS);

    @InheritInverseConfiguration(name = "toStudentDTO")
    Student toStudent(StudentDTO studentDTO);


}
