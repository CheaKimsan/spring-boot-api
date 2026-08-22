package com.setec.springdb.service;

import com.setec.springdb.model.Student;
import com.setec.springdb.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<Student> getAllStudents() { return repository.findAll(); }

    public Student getStudentById(Long id) { return repository.findById(id).orElse(null); }

    public Student saveStudent(Student student) { return repository.save(student); }

    public void deleteStudent(Long id) { repository.deleteById(id); }
}