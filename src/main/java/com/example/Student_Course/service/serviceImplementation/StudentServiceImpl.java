package com.example.Student_Course.service.serviceImplementation;

import com.example.Student_Course.dto.StudentEntitytoDto;
import com.example.Student_Course.dto.AddStudentDTO;
import com.example.Student_Course.dto.PatchUpdateStudentDTO;
import com.example.Student_Course.service.StudentService;
import com.example.Student_Course.entity.Student;
import com.example.Student_Course.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studenrepository;


    @Override
    public List<StudentEntitytoDto> getAllStudnent(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Student> studentPage = studenrepository.findAll(pageable);
        List<StudentEntitytoDto> studentDtoList = studentPage.getContent().stream()
                 .map(student -> {
                    StudentEntitytoDto s = new StudentEntitytoDto();
                    s.setId(student.getStudent_id());
                    s.setName(student.getName());
                    s.setEmail(student.getEmail());
                    return s;
                 }).toList();

        //BEFORE PAGINATION
        //List<Student> student =   studenrepository.findAll();
      //  List<StudentEntitytoDto> studentDtoList = student.stream().map(course->{
         //   StudentEntitytoDto sed = new StudentEntitytoDto();
         //   sed.setId(course.getStudent_id());
          //  sed.setName(course.getName());
           // sed.setEmail(course.getEmail());
           // return sed;
      ///  }).toList();
       return studentDtoList;
    }

    @Override
    public AddStudentDTO addStudent(AddStudentDTO student) {
        Student s= new Student();
        s.setEmail(student.getEmail());
        s.setName(student.getName());
        studenrepository.save(s);
        return student;
    }

    @Override
    public AddStudentDTO updateStudent(AddStudentDTO student, Long id){
        Student s = studenrepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Student not found"));
        s.setName(student.getName());
        s.setEmail(student.getEmail());
        studenrepository.save(s);
        return student;
    }

    @Override
    public PatchUpdateStudentDTO partialUpdate(PatchUpdateStudentDTO student, Long id){
        Student s = studenrepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Studnet not found with id: "+id));
        if(student.getName() != null){
            s.setName(student.getName());
        }
        if(student.getEmail()!= null){
            s.setEmail(student.getEmail());
        }
        studenrepository.save(s);
        return student;
    }

}
