package com.djff.springbatchrestful.controllers;

import com.djff.springbatchrestful.models.Student;
import com.djff.springbatchrestful.services.StudentBatchService;
import com.djff.springbatchrestful.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/load")
@RolesAllowed("admin")
public class BatchController {

    final StudentBatchService studentBatchService;
    final StudentService studentService;

    public BatchController(StudentBatchService studentBatchService, StudentService studentService) {
        this.studentBatchService = studentBatchService;
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> load(){
        try{
            System.out.println("It is working here");
            studentBatchService.run();
            return ResponseEntity.ok(studentService.findAllStudents());
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return ResponseEntity.ok(new ArrayList<>());
        }
    }
}
