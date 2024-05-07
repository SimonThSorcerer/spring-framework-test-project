package com.springbootproject.controller;

import com.springbootproject.object.Student;
import com.springbootproject.service.StudentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/studentrest")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentRestController {
    @Autowired
    StudentServiceImpl studentServiceImpl;

    @GetMapping ("/{studentId}")
    public Student getStudentData(@PathVariable int studentId){
        return studentServiceImpl.findStudentById(studentId);
    }



}
