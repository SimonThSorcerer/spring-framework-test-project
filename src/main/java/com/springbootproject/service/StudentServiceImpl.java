package com.springbootproject.service;

import com.springbootproject.dto.student.StudentDto;
import com.springbootproject.dto.student.StudentDtoListDto;
import com.springbootproject.exception.student.StudentDtoNullException;
import com.springbootproject.exception.student.StudentNullException;
import com.springbootproject.exception.student.StudentWithSuchAnIdDoesNotExistException;
import com.springbootproject.object.Student;
import com.springbootproject.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student mapStudentDtoToStudent(@Validated StudentDto studentDto) {
        Student student = new Student(
                studentDto.getStudentDtoId(),
                studentDto.getStudentDtoName(),
                studentDto.getStudentDtoAge(),
                studentDto.getStudentDtoEmail()
        );
        return student;
    }

    public long countAllTheStudentTableRows() {
        log.info("counting all the rows in the Student table");
        log.debug("countAllTheRowsInTheStudentTable() was called");
        long numberOfRows = studentRepository.count();
        return numberOfRows;
    }

    public Student saveStudent(@Validated StudentDto studentDto){
        log.info("saving Student to the Student table (transform StudentDto to Student)");
        log.debug("saveStudent() was called");
        Student student = mapStudentDtoToStudent(studentDto);
            return studentRepository.save(student);
    }

    public List<Student> saveMultipleStudentsAtOnce(@Validated StudentDtoListDto studentDtoListDto) {
        log.info("saving multiple Students at once to the Student table (transforms StudentDto in StudentDtoListDto to Student)");
        log.debug("saveMultipleStudentsAtOnce() was called");
        List<Student> studentList = new ArrayList<>();

        studentDtoListDto.getStudentDtoList().forEach(
                dto -> {
                    Student student = (mapStudentDtoToStudent(dto));
                    studentList.add(student);
                });
        return studentRepository.saveAll(studentList);
    }

    public Student updateStudent(@Validated StudentDto studentDto) throws StudentWithSuchAnIdDoesNotExistException, StudentDtoNullException {
        log.debug("updateStudent() was called");
        Student existingStudent = findStudentById(studentDto.getStudentDtoId());
        existingStudent.setName(studentDto.getStudentDtoName());
        existingStudent.setAge(studentDto.getStudentDtoAge());
        existingStudent.setEmail(studentDto.getStudentDtoEmail());
        return studentRepository.save(existingStudent);
    }

    //this is not used currently, delete this comment if you start using it
    public boolean checkIfStudentExistsById(Student student) throws StudentWithSuchAnIdDoesNotExistException {
        log.debug("checkIfStudentExistsById() was called");
        if (student == null && student.getId() == 0) {
            throw new StudentWithSuchAnIdDoesNotExistException("Student with such an courseDtoId does not exist. Method: checkIfStudentExistsById() in StudentServiceImpl");
        }
        if (student != null && student.getId() != 0 && studentRepository.existsById(student.getId())) {
            return true;
        } else {
            return false;
        }
    }

    public Student findStudentById(int id) throws StudentWithSuchAnIdDoesNotExistException {
        log.debug("findStudentById() was called");
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent != null && optionalStudent.isPresent()) {
            return optionalStudent.get();
        } else {
            throw new StudentWithSuchAnIdDoesNotExistException("Student with such an courseDtoId does not exist. Method: findStudentById() in StudentServiceImpl");
        }
    }

    //this is not used currently, delete this comment if you start using it
    public List<StudentDto> findAllStudentsButReturnAsStudentDtoList() {
        List<StudentDto> listOfCurrentStudentsWithValuesButConvertedToStudentDTO = new ArrayList<>();
        log.info("findAllStudentsButReturnAsStudentDtoList() was called");
        List<Student> studentListWithActualValues = findAllStudents();
        for (int i = 1; i < studentListWithActualValues.size(); i++) {
            if (studentListWithActualValues.get(i) != null) {
                StudentDto studentDto = new StudentDto();
                studentDto.setStudentDtoId(studentListWithActualValues.get(i).getId());
                studentDto.setStudentDtoName(studentListWithActualValues.get(i).getName());
                studentDto.setStudentDtoAge(studentListWithActualValues.get(i).getAge());
                studentDto.setStudentDtoEmail(studentListWithActualValues.get(i).getEmail());
                listOfCurrentStudentsWithValuesButConvertedToStudentDTO.add(studentDto);
            } else {
                throw new StudentNullException("Student in studentTable is null and therefore invalid. Method: findAllStudentsButReturnAsStudentDtoList() in StudentServiceImpl");
            }
        }
        return listOfCurrentStudentsWithValuesButConvertedToStudentDTO;
    }

    public List<Student> findAllStudents() {
        log.info("findAll() was called");
        List<Student> allStudents = studentRepository.findAll();

        if (allStudents.isEmpty()) {
            return null;
        } else {
            return allStudents;
        }
    }

    public void deleteStudentById(int id) throws StudentWithSuchAnIdDoesNotExistException {
        log.info("deleteStudentById() was called");
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new StudentWithSuchAnIdDoesNotExistException("Student with such an courseDtoId does not exist and therefore can not be deleted. Method: deleteStudentById() in StudentServiceImpl");
        }
    }
}
