package com.setec.springdb.controller;

import com.setec.springdb.model.Student;
import com.setec.springdb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping
    public ResponseEntity<Object> getAllStudents() {
        try {
            List<Student> students = service.getAllStudents();
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Failed to fetch students");
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable Long id) {
        try {
            Student student = service.getStudentById(id);
            if (student != null) {
                return ResponseEntity.ok(student);
            }
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Student not found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Failed to fetch student");
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createStudent(@RequestBody Student student) {
        Map<String, Object> response = new HashMap<>();
        try {
            Student saved = service.saveStudent(student);
            response.put("message", "Student created successfully");
            response.put("data", saved);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.put("message", "Failed to create student");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        try {
            Student student = service.getStudentById(id);
            if (student != null) {
                student.setName(studentDetails.getName());
                student.setEmail(studentDetails.getEmail());
                student.setGender(studentDetails.getGender());
                student.setAddress(studentDetails.getAddress());
                return ResponseEntity.ok(service.saveStudent(student));
            }
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Student not found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Failed to update student");
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Long id) {
        try {
            service.deleteStudent(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Failed to delete student");
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

}