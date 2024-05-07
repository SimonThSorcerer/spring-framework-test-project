package com.springbootproject.controller;


import com.springbootproject.dto.employee.EmployeeDto;
import com.springbootproject.object.Employee;
import com.springbootproject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


//    @GetMapping("/employees/{id}" //For resttempalte or synchronious webclient
//    public ResponseEntity<EmployeeDto> getEmployeeDetails(@PathVariable("id") int id) {
//        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeByIdUsingSpringReactiveWeb(id));
//    }

//    @GetMapping("/employees/{id}")  //for asynchronious webclient
//    public Mono<EmployeeDto> getEmployeeDetails(@PathVariable("id") int id) {
//        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeByIdUsingSpringReactiveWeb(id)).getBody();
//    }

    @GetMapping("/employeewithaddress/{id}")  //for asynchronious webclient
    public ResponseEntity<EmployeeDto> getEmployeeDetails(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeByIdUsingFeignClient(id)).getBody();
    }

    @GetMapping("/employees/{id}")
    public EmployeeDto getEmployeeDetailsOnlyWithoutAddress(@PathVariable("id") int id) {
        return employeeService.getEmployeeByIdWithoutAddress(id);
    }
}
