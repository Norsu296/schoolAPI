package pl.kuba.school.dto.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import pl.kuba.school.dto.model.TeacherDTO;
import pl.kuba.school.dto.model.TeacherToStudentDTO;
import pl.kuba.school.entity.Teacher;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherDTO toTeacherDTO(Teacher teacher);

    List<TeacherDTO> toTeacherDTOs(Page<Teacher> teachers);

    List<Teacher> toTeachers(List<TeacherToStudentDTO> teacherToStudentDTOS);

    @InheritInverseConfiguration(name = "toTeacherDTO")
    Teacher toTeacher(TeacherDTO teacherDTO);


}
