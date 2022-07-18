package peaksoft.springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.springboot.dto.StudentRequest;
import peaksoft.springboot.dto.StudentResponse;
import peaksoft.springboot.dto.StudentResponseView;
import peaksoft.springboot.service.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/student")
@Tag(name = "Student API", description = "User with role admin can add, update, delete or get all students")
public class StudentController {

    private final StudentService service;
    @PostMapping
    @Operation(summary = "create student", description = "we can create student")
    public StudentResponse create(@RequestBody StudentRequest request) {
        return service.create(request);
    }
    @PostMapping("{id}")
    @Operation(summary = "update student", description = "we can update student")
    public StudentResponse update(@PathVariable long id, @RequestBody StudentRequest request) {
        return service.update(id, request);
    }

    @GetMapping("{id}")
    @Operation(summary = "find student", description = "we can find student by id")
    public StudentResponse findById(@PathVariable long id) {
        return service.findById(id);
    }
    @DeleteMapping("{id}")
    @Operation(summary = "delete student", description = "we can delete student by id")
    public StudentResponse delete(@PathVariable long id) {
        return service.deleteById(id);
    }

    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return service.getAllStudents();
    }

    @GetMapping
    @Operation(summary = "get all students and seacrh", description = "we can get all students and search")
    public StudentResponseView getAllStudents(@RequestParam(name = "text", required = false) String text,
                                              @RequestParam int page,
                                              @RequestParam int size) {
        return service.getAllStudentsPagination(text, page, size);
    }

}
