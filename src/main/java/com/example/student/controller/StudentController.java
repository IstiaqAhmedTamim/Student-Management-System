package com.example.student.controller;

import com.example.student.model.Student;
import com.example.student.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET /students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // GET /students/sort/cgpa
    @GetMapping("/sort/cgpa")
    public List<Student> getStudentsSortedByCgpa() {
        return studentService.getStudentsSortedByCgpa();
    }

    // DELETE /students/{id}
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        boolean removed = studentService.deleteStudentById(id);
        return removed ? "Student removed" : "Student not found";
    }
}
