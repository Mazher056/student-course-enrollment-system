package com.example.Student_Course.service;

import com.example.Student_Course.dto.CourseEntityToDto;
import com.example.Student_Course.dto.PatchUpdateCourseDTO;
import com.example.Student_Course.dto.AddCourseDto;

import java.util.*;


public interface CourseService {
    AddCourseDto addCourse(AddCourseDto course);
    PatchUpdateCourseDTO patchUpdate(PatchUpdateCourseDTO data, Long id);
    List<CourseEntityToDto> getAllCourses(int page, int size);
}
