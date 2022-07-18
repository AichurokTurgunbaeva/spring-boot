package peaksoft.springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import peaksoft.springboot.dto.StudentRequest;
import peaksoft.springboot.dto.StudentResponse;
import peaksoft.springboot.dto.StudentResponseView;
import peaksoft.springboot.entity.Student;
import peaksoft.springboot.mapper.StudentEditMapper;
import peaksoft.springboot.mapper.StudentViewMapper;
import peaksoft.springboot.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final StudentEditMapper editMapper;
    private final StudentViewMapper viewMapper;

    public StudentResponse create (StudentRequest studentRequest) {
        Student student = editMapper.create(studentRequest);
        repository.save(student);
        return viewMapper.viewStudent(student);
    }
    public StudentResponse update(Long id, StudentRequest studentRequest) {
        Student student = repository.findById(id).get();
        editMapper.update(student, studentRequest);
        return viewMapper.viewStudent(repository.save(student));
    }

    public StudentResponse findById(Long id) {
        Student student = repository.findById(id).get();
        return viewMapper.viewStudent(student);
    }
    public StudentResponse deleteById(Long id) {
        Student student = repository.getById(id);
        repository.delete(student);
        return viewMapper.viewStudent(student);
    }

    public List<StudentResponse> getAllStudents() {
      return viewMapper.view(repository.findAll());
    }

    public StudentResponseView getAllStudentsPagination(String text, int page, int size) {
        StudentResponseView responseView = new StudentResponseView();
        Pageable pageable = PageRequest.of(page-1, size);
        responseView.setResponses(view(search(text, pageable)));
        return responseView;

    }

    public List<StudentResponse> view(List<Student> students) {
        List<StudentResponse> responses = new ArrayList<>();
        for (Student student:  students ) {
            responses.add(viewMapper.viewStudent(student));

        }
        return responses;
    }

    private List<Student> search(String name, Pageable pageable) {
        String text = name==null? "": name;
        return repository.searchAndPagination(text.toUpperCase(), pageable);
    }
}
