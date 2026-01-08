package com.example.Student_Course.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class AddStudentDTO {
    @NotBlank(message = "Enter a valid name")
    private String name;
    @Email(message = "Enter a valid Email")
    private String email;
}
