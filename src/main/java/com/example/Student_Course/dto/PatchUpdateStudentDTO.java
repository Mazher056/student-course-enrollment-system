package com.example.Student_Course.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PatchUpdateStudentDTO {
    @Email(message = "Enter a valid Email")
    private String email;
    private String name;
}
