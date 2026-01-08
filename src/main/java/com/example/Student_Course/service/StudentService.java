package com.example.Student_Course.service;

import com.example.Student_Course.dto.StudentEntitytoDto;
import com.example.Student_Course.dto.AddStudentDTO;
import com.example.Student_Course.dto.PatchUpdateStudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentEntitytoDto> getAllStudnent(int page, int size);
    AddStudentDTO addStudent(AddStudentDTO student);
    AddStudentDTO updateStudent(AddStudentDTO student, Long id);
    PatchUpdateStudentDTO partialUpdate(PatchUpdateStudentDTO student, Long id);
}
