package com.springbootproject.service;

import com.springbootproject.dto.student.StudentDto;
import com.springbootproject.dto.student.StudentDtoListDto;
import com.springbootproject.exception.student.StudentDtoNullException;
import com.springbootproject.exception.student.StudentWithSuchAnIdDoesNotExistException;
import com.springbootproject.object.Student;
import org.springframework.validation.annotation.Validated;

import java.util.List;

public interface StudentService {
    Student mapStudentDtoToStudent(StudentDto studentDto);

    long countAllTheStudentTableRows();

    Student saveStudent(StudentDto studentDto);

    List<Student> saveMultipleStudentsAtOnce(StudentDtoListDto studentDtoListDto) throws StudentDtoNullException; // TODO @LeksUkr validation to be moved to Dto level. No need for custom exception

    Student updateStudent(StudentDto studentdto) throws StudentWithSuchAnIdDoesNotExistException, StudentDtoNullException; // TODO @LeksUkr: StudentWithSuchAnIdDoesNotExistException can be abstracted to ElementNotFoundException (and can be reused for similar purposes with Courses, Teachers, etc.). Validation to be moved to Dto Level with no need for StudentDtoNullException

    Student findStudentById(int id) throws StudentWithSuchAnIdDoesNotExistException; // TODO @LeksUkr: generalize the exception

    List<Student> findAllStudents(); // TODO @LeksUkr: do not need an exception. If there are no students, just return an empty list

    List<StudentDto> findAllStudentsButReturnAsStudentDtoList(); // TODO @LeksUkr I would re-write method as "mapStudentToStudentDto" where you accept Student and return Dto object. Do not need to repeat findAll logic which you have already implemented.

    void deleteStudentById(int id) throws StudentWithSuchAnIdDoesNotExistException; // TODO @LeksUkr use your findStudentById or checkIfStudentExistsById to check if Student exists. If you do, you won't need exception here, it will be thrown at the level of those methods.

}
