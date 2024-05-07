package com.springbootproject.dto.student;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StudentDtoListDto {
    private final List<StudentDto> studentDtoList = new ArrayList<>();

    public void addStudentDto(@Valid StudentDto studentDto) {
        this.studentDtoList.add(studentDto);
    }
}
