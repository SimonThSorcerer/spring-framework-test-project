package com.springbootproject.controller;

import com.springbootproject.dto.student.StudentDto;
import com.springbootproject.dto.student.StudentDtoListDto;
import com.springbootproject.dto.student.StudentIdDto;
import com.springbootproject.exception.student.StudentDtoNullException;
import com.springbootproject.exception.student.StudentWithSuchAnIdDoesNotExistException;
import com.springbootproject.object.Student;
import com.springbootproject.service.StudentServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class StudentController {

    @Autowired
    StudentServiceImpl studentServiceImpl;

    @GetMapping("/student/")
    public String addNewStudentFormDisplay(Model model) {
        log.debug("@Controller addNewStudentFormDisplay() was called");
        model.addAttribute("studentDto", new StudentDto());
        return "/student/";
    }

    @PostMapping("/student/addnewstudentsubmit")
    public String addNewStudentFormAction(@Valid @ModelAttribute("studentDto") StudentDto studentDto, BindingResult bindingResult, Model model) throws StudentDtoNullException {
        log.debug("@Controller addNewStudentFormAction() was called");

        if (!bindingResult.hasErrors()) {
            model.addAttribute("studentDto", studentDto);
            studentServiceImpl.saveStudent(studentDto);
            model.addAttribute("successfulRegistration", true);
            model.addAttribute("successfulRegistrationMessage", "Student registered successfully.");
            return "/student/register";
        } else {
            model.addAttribute("failureAtRegistration", true);
            model.addAttribute("failedRegistrationMessage", "Student did not register successfully. Make sure to type in the information correctly. If you still have issues contact admin.");
            return "/student/register";
        }
    }

    @GetMapping("/student/addnewstudentlist")
    public String addStudentDtoListPageWithForm(Model model) {
        log.debug("@Controller addStudentDtoListPageWithForm() was called");
        StudentDtoListDto studentDtoListDto = new StudentDtoListDto();
        for (int i = 1; i <= 3; i++) {
            studentDtoListDto.addStudentDto(new StudentDto());
        }
        model.addAttribute("studentDtoListDtoForm", studentDtoListDto);
        return "/student/addnewstudentlist";
    }

    @PostMapping("/student/addnewstudentlistform")
    public String addNewStudentListFormAction(@Valid @ModelAttribute("studentDtoListDtoForm") StudentDtoListDto studentDtoListDto, BindingResult bindingResult, Model model) {
        log.debug("@Controller addNewStudentListFormAction() was called");

        if (!bindingResult.hasErrors()) {
                studentServiceImpl.saveMultipleStudentsAtOnce(studentDtoListDto);
            return "/student/listallstudents";
        } else {
            model.addAttribute("studentDtoListDtoForm", studentDtoListDto);
            return "/student/addnewstudentlist";
        }
    }

    @GetMapping("/student/update/{courseDtoId}")
    public String updateExistingStudent(@PathVariable int id, Model model) throws StudentWithSuchAnIdDoesNotExistException {
        log.debug("@Controller updateExistingStudent() was called");

        Student student = studentServiceImpl.findStudentById(id);
        StudentDto studentDto = new StudentDto(student.getId(), student.getName(), student.getAge(), student.getEmail());
        model.addAttribute("studentDto", studentDto);
        return "/student/updateexistingstudent";
    }

    @PostMapping("/student/updatexistingstudentform")
    public String updateExistingStudentForm(@Valid @ModelAttribute("studentDto")StudentDto studentDto, BindingResult bindingResult) {
        log.debug("@Controller updateExistingStudentForm() was called");

        if (!bindingResult.hasErrors()) {
            studentServiceImpl.updateStudent(studentDto);
            return "redirect:/student/update/" + studentDto.getStudentDtoId();
        } else {
            return "/student/updateexistingstudent";
        }
    }

    @GetMapping("/student/{courseDtoId}")
    public String findStudentById(@PathVariable int id, Model model) throws StudentWithSuchAnIdDoesNotExistException {
        log.debug("findStudentById() was called");

        Student student = studentServiceImpl.findStudentById(id);
        StudentDto studentDto = new StudentDto(student.getId(), student.getName(), student.getAge(), student.getEmail());

        model.addAttribute("studentDto", studentDto);
        StudentIdDto idOfStudentToBeDeleted = new StudentIdDto();
        model.addAttribute("idOfStudentToBeDeleted", idOfStudentToBeDeleted);
        return "/student/studentinfo";
    }

    @PostMapping("/student/deletestudent")
    public String deleteStudentById(@Valid @ModelAttribute("idOfStudentToBeDeleted")StudentIdDto studentToBeDeleted, Model model) {
        log.debug("@Controller deleteStudentById() was called");
        studentServiceImpl.deleteStudentById(studentToBeDeleted.getStudentDtoId());
        model.addAttribute("studentDeleted", true);
        model.addAttribute("studentDeletedMessage", "Student with the courseDtoId: " + studentToBeDeleted.getStudentDtoId() + " was deleted.");
        model.addAttribute("idOfStudentToBeDeleted", new StudentIdDto());
        return "/student/studentinfo";
    }

    @GetMapping("/student/listallstudents")
    public String listAllStudents(Model model) {
        log.debug("@Controller listAllStudents() was called");
        model.addAttribute("listOfAllStudents", studentServiceImpl.findAllStudents());
        return "/student/listallstudents";
    }

    @GetMapping("/student/countallthestudenttablerows")
    public String countAllTheRowsInTheStudentTable(Model model) {
        log.debug("@Controller countAllTheRowsInTheStudentTable() was called");
        model.addAttribute("numberOfRowsInTheStudentTable", studentServiceImpl.countAllTheStudentTableRows());
        return "/student/countallthestudenttablerows";
    }
}
