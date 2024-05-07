package com.springbootproject.controller;


import com.springbootproject.dto.student.StudentIdDto;
import com.springbootproject.object.Student;
import com.springbootproject.service.StudentServiceManualSqlService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class StudentServiceManualSqlController {

    @Autowired
    StudentServiceManualSqlService studentServiceManualSqlService;

    @GetMapping("/student/manualsql/retrivestudentsql")
    public String displayStudentBasedOnIdFormDisplay(Model model) {
        log.debug("displayStudent() method was called");
        StudentIdDto studentIdDto = new StudentIdDto();
        model.addAttribute("studentId",studentIdDto);
        return "/student/manualsql/retrivestudentsql";
    }

    @PostMapping("/student/manualsql/retrivestudentsqlformaction")
    public String displayStudentBasedOnIdFormAction(@Valid @ModelAttribute("studentId") StudentIdDto studentIdDto, Model model, BindingResult bindingResult) {
        log.debug("displayStudentBasedOnIdFormAction() method was called");
        Student student = studentServiceManualSqlService.geStudentByIdWithSqlQuery(studentIdDto.getStudentDtoId());
        model.addAttribute("bindingResult", bindingResult);
        model.addAttribute("studentFoundById", student);
        return "/student/manualsql/retrivestudentsql";
    }

    @GetMapping("/student/manualsql/retrivestudentsqlresult")
    public String displayStudentBasedOnIdFormDisplayResult(@Valid @ModelAttribute("studentFoundById") Student student) {
        log.debug("displayStudent() method was called");
        return "/student/manualsql/retrivestudentsqlresult";
    }
}
