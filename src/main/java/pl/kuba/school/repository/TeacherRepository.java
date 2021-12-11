package pl.kuba.school.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.kuba.school.entity.Student;
import pl.kuba.school.entity.Teacher;


import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    boolean existsByEmail(String email);

    Page<Teacher> findAllByDeletedFalse(Pageable pageable);

    Optional<Teacher> findByIdAndDeletedFalse(Long id);

    @Query("SELECT t FROM Teacher t JOIN t.students s WHERE s.surname = :surname AND t.deleted = false")
    List<Teacher> findAllStudentsByStudentSurname(String surname);

    List<Teacher> findAllBySurname(String surname);
}
