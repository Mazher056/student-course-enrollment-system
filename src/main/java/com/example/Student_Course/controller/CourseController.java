package com.example.Student_Course.controller;

import com.example.Student_Course.dto.CourseEntityToDto;
import com.example.Student_Course.dto.PatchUpdateCourseDTO;
import com.example.Student_Course.dto.AddCourseDto;
import com.example.Student_Course.service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@AllArgsConstructor
@RestController
public class CourseController {

   private final CourseService courseService;

    @GetMapping("/courses")
    public ResponseEntity<?> getAllCourses(
            @RequestParam int page,
            @RequestParam int size
    ){
        return ResponseEntity.ok(courseService.getAllCourses(page, size));
    }

    @PostMapping("/addCourse")
    public ResponseEntity<AddCourseDto> addCourse(@RequestBody @Valid AddCourseDto course){
      return ResponseEntity
              .status(HttpStatus.CREATED)
              .body(courseService.addCourse(course));
    }

    @PatchMapping("/partialUpdateCourse/{id}")
    public ResponseEntity<PatchUpdateCourseDTO> patchUpdate(@RequestBody PatchUpdateCourseDTO data,@PathVariable Long id){
        return ResponseEntity
                .ok(courseService.patchUpdate(data,id));
    }

}
