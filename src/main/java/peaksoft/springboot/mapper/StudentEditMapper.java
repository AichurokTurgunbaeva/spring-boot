package peaksoft.springboot.mapper;

import org.springframework.stereotype.Component;
import peaksoft.springboot.dto.StudentRequest;
import peaksoft.springboot.entity.Student;

import java.time.LocalDate;

@Component
public class StudentEditMapper {

    public Student create(StudentRequest request) {
        if(request==null) {
            return null;
        }
        Student student = new Student();
        student.setName(request.getName());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());
        student.setSurname(request.getSurname());
        student.setCreated(LocalDate.now());
        student.setActive(true);
        return student;
    }

    public void update(Student student, StudentRequest request) {
        student.setName(request.getName());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());
        student.setSurname(request.getSurname());
    }

}
