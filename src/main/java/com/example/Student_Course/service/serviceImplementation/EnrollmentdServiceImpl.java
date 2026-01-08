package com.example.Student_Course.service.serviceImplementation;

import com.example.Student_Course.dto.AddEnrollmentDto;
import com.example.Student_Course.dto.CourseEntityToDto;
import com.example.Student_Course.dto.EnrollmentToDto;
import com.example.Student_Course.dto.StudentEntitytoDto;
import com.example.Student_Course.entity.Course;
import com.example.Student_Course.entity.Enrollment;
import com.example.Student_Course.entity.Student;
import com.example.Student_Course.repository.CourseRepository;
import com.example.Student_Course.repository.EnrollmentRepository;
import com.example.Student_Course.repository.StudentRepository;
import com.example.Student_Course.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EnrollmentdServiceImpl implements EnrollmentService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public AddEnrollmentDto enrollEntry(AddEnrollmentDto enroll) {
        System.out.println("1");
        Student student = studentRepository.findById(enroll.getStudentId())
                .orElseThrow(()-> new IllegalArgumentException
                    ("Student not found in dataBase with id: "+enroll.getStudentId()+" UDATING DATABASE FAILED"));
        System.out.println("2");
        Course course = courseRepository.findById(enroll.getCourseId())
                .orElseThrow(()->
                        new IllegalArgumentException("Course not found in dataBase with id: "+enroll.getCourseId()+" UDATING DATABASE"));
        System.out.println("3");
        boolean exist = enrollmentRepository.existsByStudentAndCourse(student,course);
        System.out.println("4");
        if(exist) throw new RuntimeException("Enrollment Already Exists");
        Enrollment entry = new Enrollment();
        entry.setStudent(student);
        entry.setCourse(course);
        enrollmentRepository.save(entry);
        return enroll;
    }

    @Override
    public List<EnrollmentToDto> allEnrollments(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Enrollment> enrollList = enrollmentRepository.findAll(pageable);
       List<EnrollmentToDto> allenrollments= enrollList.getContent().stream().map(enrollment ->
        {
            EnrollmentToDto e = new EnrollmentToDto();
            e.setId(enrollment.getId());
            e.setCourseId(enrollment.getCourse().getCourse_id());
            e.setStudentId(enrollment.getStudent().getStudent_id());
            return e;
        }).toList();


        //List<Enrollment> enrollments = enrollmentRepository.findAll();
       // List<EnrollmentToDto> allenrollments =  enrollments.stream().map(enroll->{
       //     EnrollmentToDto dto = new EnrollmentToDto();
         //   dto.setId(enroll.getId());
        //    dto.setStudentId(enroll.getStudent().getStudent_id());
        //    dto.setCourseId(enroll.getCourse().getCourse_id());
        //    return dto;
      //  }).toList();
       return  allenrollments;
    }

    @Override
    public List<CourseEntityToDto> getCoursesofStudent(Long id) {
        Student s = studentRepository
                .findById(id)
                .orElseThrow(()->new IllegalArgumentException("Student not found with id: "+id ));
        List<Enrollment> enrolls= enrollmentRepository.findByStudent(s);
        return enrolls.stream().map(enrollment -> {
            CourseEntityToDto dto  = new CourseEntityToDto();
            dto.setCourse_id(enrollment.getCourse().getCourse_id());
            dto.setTitle(enrollment.getCourse().getTitle());
            dto.setDescription(enrollment.getCourse().getDescription());
            return dto;
        }).toList();
    }

    @Override
    public List<StudentEntitytoDto> getStudentsofCourse(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No course Found with id: "+id));
        List<Enrollment> courseList = enrollmentRepository.findByCourse(course);
        return courseList.stream().map(dto->{
            StudentEntitytoDto s =  new StudentEntitytoDto();
            s.setId(dto.getStudent().getStudent_id());
            s.setName(dto.getStudent().getName());
            s.setEmail(dto.getStudent().getEmail());
            return s;
        }).toList();
    }



}
