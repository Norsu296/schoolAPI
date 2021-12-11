package pl.kuba.school.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "students")
@SQLDelete(sql = "UPDATE students SET deleted = true WHERE id = ?")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 3, message = "Name must have at least 3 characters")
    private String name;
    @NotBlank
    @Size(min = 3, message = "Surname must have at least 3 characters")
    private String surname;
    @NotNull
    @Min(value = 18, message = "Age must be above 18 years")
    private int age;
    @NotBlank(message = "Email can not be empty")
    @Email
    private String email;
    @NotBlank(message = "Major can not be empty")
    private String major;

    private boolean deleted;

    @ManyToMany(mappedBy = "students")
    private List<Teacher> teachers;

    @PrePersist
    public void deleted() {
        deleted = false;
    }


}
