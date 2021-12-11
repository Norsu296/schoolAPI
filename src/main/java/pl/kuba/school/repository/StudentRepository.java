package pl.kuba.school.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.kuba.school.entity.Student;
import pl.kuba.school.entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);

    Page<Student> findAllByDeletedFalse(Pageable pageable);

    Optional<Student> findByIdAndDeletedFalse(Long id);

    @Query("SELECT s FROM Student s JOIN s.teachers t WHERE t.surname = :surname AND t.deleted = false")
    List<Student> findAllStudentsByTeachersSurname(String surname);

    List<Student> findAllBySurname(String surname);

}
