package pl.kuba.school.dto.model;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class StudentToTeacherDTO {

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

}
