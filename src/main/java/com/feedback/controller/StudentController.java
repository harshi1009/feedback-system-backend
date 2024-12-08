//package com.example.studentfeedback.controllers;
//
//import com.example.studentfeedback.models.Student;
//import com.example.studentfeedback.repositories.StudentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/students")
//public class StudentController {
//
//    @Autowired
//    private StudentRepository studentRepository;
//
//    @PostMapping
//    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
//        Student savedStudent = studentRepository.save(student);
//        return ResponseEntity.ok(savedStudent);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Student>> getAllStudents() {
//        List<Student> students = studentRepository.findAll();
//        return ResponseEntity.ok(students);
//    }
//}
