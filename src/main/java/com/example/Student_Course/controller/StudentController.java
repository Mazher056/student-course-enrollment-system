package com.example.Student_Course.controller;
import com.example.Student_Course.dto.AddStudentDTO;
import com.example.Student_Course.dto.PatchUpdateStudentDTO;
import com.example.Student_Course.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@AllArgsConstructor
@RestController
public class StudentController {
   private final StudentService studentservice;

    @GetMapping("/student")
    public ResponseEntity<?> getallstudent(
            @RequestParam int page,
            @RequestParam int size
    ){
      return ResponseEntity.ok(studentservice.getAllStudnent(page,size));
    }


    @PostMapping("/addStudent" )
    public ResponseEntity<AddStudentDTO> addStudent(@RequestBody @Valid AddStudentDTO s){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentservice.addStudent(s));
    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<AddStudentDTO> updateStudent(@RequestBody AddStudentDTO s, @PathVariable Long id){
        return ResponseEntity.ok(studentservice.updateStudent(s, id));
    }

    @PatchMapping("/partialUpdateStudent/{id}")
    public ResponseEntity<PatchUpdateStudentDTO> partialUpdate(@RequestBody @Valid PatchUpdateStudentDTO s, @PathVariable Long id){
      return ResponseEntity.ok(studentservice.partialUpdate(s,id));
    }

}
