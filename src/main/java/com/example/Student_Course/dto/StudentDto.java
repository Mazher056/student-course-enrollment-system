package com.example.Student_Course.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StudentDto {

    @Email(message = "Please Enter a valid Email")
    private String email;

    @NotBlank(message = "Please Enter a valid Name")
    private String name;

}
