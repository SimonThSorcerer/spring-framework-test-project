package com.springbootproject.controller;

import com.springbootproject.object.Student;
import com.springbootproject.service.StudentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/studentrest")
public class StudentRestController {
    @Autowired
    StudentServiceImpl studentServiceImpl;

    @GetMapping ("/{studentId}")
    public Student getStudentData(@PathVariable int studentId){
        return studentServiceImpl.findStudentById(studentId);
    }



}
