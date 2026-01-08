package com.example.Student_Course.controller;

import com.example.Student_Course.dto.AddEnrollmentDto;
import com.example.Student_Course.dto.CourseEntityToDto;
import com.example.Student_Course.dto.StudentEntitytoDto;
import com.example.Student_Course.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EnrollController {
    private final EnrollmentService enrollmentService;


    @GetMapping("/allEnrollment")
    public ResponseEntity<?> allEnrollment(
            @RequestParam int page,
            @RequestParam int size
    ){
        return ResponseEntity.ok(enrollmentService.allEnrollments(page,size));
    }


    @PostMapping("/addEnroll")
    public ResponseEntity<AddEnrollmentDto> addEnrollment(@RequestBody AddEnrollmentDto dto){
            return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.enrollEntry(dto));
    }


    @GetMapping("/student/{id}/courses")
    public ResponseEntity<List<CourseEntityToDto>> getStudentcourses(@PathVariable Long id){
        return ResponseEntity.ok(enrollmentService.getCoursesofStudent(id));
    }


    @GetMapping("/course/{id}/students")
    public ResponseEntity<List<StudentEntitytoDto>> getStudentsofCourse(@PathVariable Long id){
        return ResponseEntity.ok(enrollmentService.getStudentsofCourse(id));
    }


}
