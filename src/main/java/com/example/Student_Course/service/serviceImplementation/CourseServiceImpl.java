package com.example.Student_Course.service.serviceImplementation;

import com.example.Student_Course.dto.CourseEntityToDto;
import com.example.Student_Course.dto.PatchUpdateCourseDTO;
import com.example.Student_Course.dto.AddCourseDto;
import com.example.Student_Course.entity.Course;
import com.example.Student_Course.repository.CourseRepository;
import com.example.Student_Course.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courserepository;

    @Override
    public AddCourseDto addCourse(AddCourseDto course) {
        Course c = new Course();
        c.setDescription(course.getDescription());
        c.setTitle(course.getTitle());
        courserepository.save(c);
        return course;
        }

    @Override
    public PatchUpdateCourseDTO patchUpdate(PatchUpdateCourseDTO data, Long id) {
        Course course = courserepository.findById(id)
        .orElseThrow(()-> new IllegalArgumentException("Course with id is not found in the database: id = "+id));
        if(data.getTitle() != null) course.setTitle((data.getTitle()));
        if(data.getDescription() != null) course.setDescription(data.getDescription());
        courserepository.save(course);
        return data;
    }

    @Override
    public List<CourseEntityToDto> getAllCourses(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Course> pageList = courserepository.findAll(pageable);
        List<CourseEntityToDto> courseDtolist = pageList.map(course ->
        {
            CourseEntityToDto c = new CourseEntityToDto();
            c.setCourse_id(course.getCourse_id());
            c.setTitle(course.getTitle());
            c.setDescription(course.getDescription());
            return c;
        }).toList();
       // List<Course> courses = courserepository.findAll();
       // List<CourseEntityToDto> courseDtolist = courses.stream().map(course->{
        //     CourseEntityToDto ced = new CourseEntityToDto();
       //      ced.setCourse_id(course.getCourse_id());
      //       ced.setTitle(course.getTitle());
        //     ced.setDescription(course.getDescription());
        //     return ced;
       // }).toList();
      return  courseDtolist;
    }
}
