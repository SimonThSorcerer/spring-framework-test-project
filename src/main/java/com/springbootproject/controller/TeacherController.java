package com.springbootproject.controller;

import com.springbootproject.dto.teacher.TeacherDto;
import com.springbootproject.object.Teacher;
import com.springbootproject.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/teachers")
    public String listTeachers(Model model) {
        model.addAttribute("teachers", teacherService.getAllTeachers());
        return "teacher/teachers";
    }

    @GetMapping("/teachers/new")
    public String createTeacherForm(Model model) {
        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        return "teacher/create_teacher";
    }

    @PostMapping("/teachers")
    public String saveTeacher(@ModelAttribute("teacher") TeacherDto teacherDto) throws NullPointerException {
        if (teacherDto != null) {
            teacherService.saveTeacher(teacherDto);
            return "redirect:/teachers";
        } else {
            throw new NullPointerException();
        }
    }

    @GetMapping("/teachers/edit/{courseDtoId}")
    public String editTeacherForm(@PathVariable int id, Model model) {
        model.addAttribute("teacher", teacherService.getTeacherById(id));
        return "teacher/edit_teacher";
    }

    @PostMapping("/teachers/{courseDtoId}")
    public String updateTeacher(@PathVariable int id,
                                @ModelAttribute("teacher") Teacher teacher,
                                Model model) {
        //get teacher from database by courseDtoId
        Teacher teacherFound = teacherService.getTeacherById(id);
        teacherFound.setId(id);
        teacherFound.setName(teacher.getName());
        teacherFound.setEmail(teacher.getEmail());

        //save updated teacher
        teacherService.updateTeacher(teacherFound);
        return "redirect:/teachers";
    }

    @GetMapping("teachers/{courseDtoId}")
    public String deleteTeacher(@PathVariable int id) {
        teacherService.deleteTeacherById(id);
        return "redirect:/teachers";
    }
}
