package com.djff.springbatchrestful.services;

import com.djff.springbatchrestful.models.Student;
import com.djff.springbatchrestful.repositories.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {
    final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }
}
