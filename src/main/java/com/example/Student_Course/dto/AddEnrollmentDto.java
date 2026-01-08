package com.example.Student_Course.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddEnrollmentDto {
    @NotBlank
    private Long studentId;
    @NotBlank
    private Long courseId;
}
