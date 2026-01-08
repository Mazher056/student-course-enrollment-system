package com.example.Student_Course.repository;

import com.example.Student_Course.entity.Course;
import com.example.Student_Course.entity.Enrollment;
import com.example.Student_Course.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {
    boolean existsByStudentAndCourse(Student student, Course course);
    List<Enrollment> findByStudent(Student s);
    List<Enrollment> findByCourse(Course c);
}

