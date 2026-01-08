package com.example.Student_Course.service;

import com.example.Student_Course.dto.AddEnrollmentDto;
import com.example.Student_Course.dto.CourseEntityToDto;
import com.example.Student_Course.dto.EnrollmentToDto;
import com.example.Student_Course.dto.StudentEntitytoDto;
import com.example.Student_Course.entity.Enrollment;

import java.util.List;

public interface EnrollmentService {
   AddEnrollmentDto enrollEntry(AddEnrollmentDto enroll);
   List<EnrollmentToDto> allEnrollments(int page,int size);
   List<CourseEntityToDto> getCoursesofStudent(Long id);
   List<StudentEntitytoDto> getStudentsofCourse(Long id);

}
