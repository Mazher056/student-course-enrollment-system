package com.example.Student_Course.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseEntityToDto {
    private Long course_id;
    private String title;
    private String description;
}
