package pl.kuba.school.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "teachers")
@SQLDelete(sql = "UPDATE teachers SET deleted = true WHERE id = ?")
@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 3, message = "Name must have at least 3 characters")
    private String name;
    @NotBlank
    @Size(min = 3, message = "Surname must have at least 3 characters")
    private String surname;
    @NotNull
    @Min(value = 18, message = "Age must be above 18 years")
    private int age;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String subject;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "teachers_students",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    private boolean deleted;

    @PrePersist
    public void deleted() {
        deleted = false;
    }

}
