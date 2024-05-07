package com.springbootproject.service;

import com.springbootproject.dto.address.AddressDto;
import com.springbootproject.dto.employee.EmployeeDto;
import com.springbootproject.feignclient.AddressClient;
import com.springbootproject.object.Employee;
import com.springbootproject.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ModelMapper modelMapper;
    //    @Autowired
//    RestTemplate restTemplate;

    @Autowired
    WebClient webClient;

    @Autowired
    AddressClient addressClient;

    RestTemplate restTemplate;

    @Value("${addressservice.base.url}")
    private String addressBaseURL;

//    public EmployeeService(@Value("${addressservice.base.url}") String addressBaseURL, RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplate = restTemplateBuilder
//                .rootUri(addressBaseURL)
//                .build();
//    }

    public ResponseEntity<EmployeeDto> getResponseEntityEmployeeDtoWithoutAddress(int id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeToEmployeeDtoMapper(employeeRepository.findById(id).orElse(null)));
    }

    public EmployeeDto getEmployeeByIdUsingRestTemplate(int id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        EmployeeDto employeeDto = employeeToEmployeeDtoMapper(employee);

//        AddressDto addressDto = restTemplate.getForObject("http://localhost:8081/address-app/api/address/{id}", AddressDto.class, id);
//        AddressDto addressDto = restTemplate.getForObject( addressBaseURL + "/address/{id}", AddressDto.class, id);  //application properties + @Value("${addressservice.base.url}")
        AddressDto addressDto = restTemplate.getForObject("/address/{id}", AddressDto.class, id); //This one is professional, but use Reactive Web services, below
        employeeDto.setAddressDto(addressDto);


        return employeeDto;
    }

    public EmployeeDto employeeToEmployeeDtoMapper(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public Mono<EmployeeDto> getEmployeeByIdUsingSpringReactiveWeb(int id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return Mono.empty();
        }

        EmployeeDto employeeDto = employeeToEmployeeDtoMapper(employee);
        return webClient.get()
                .uri("address/" + id)
                .retrieve()
                .bodyToMono(AddressDto.class)
                .map(addressDto -> {
                    employeeDto.setAddressDto(addressDto);
                    return employeeDto;
                });
    }

//    @Autowired
//    WebClient.Builder webClientBuilder;
//
//
//    public Mono<EmployeeDto> getEmployeeById(int id) {
//        return Mono.justOrEmpty(employeeRepository.findById(id))
//                .flatMap(employee -> {
//                    EmployeeDto employeeDto = employeeToEmployeeDtoMapper(employee);
//                    return webClientBuilder.build()
//                            .get()
//                            .uri(addressBaseURL + "/address/" + id)
//                            .retrieve()
//                            .bodyToMono(AddressDto.class)
//                            .map(addressDto -> {
//                                employeeDto.setAddressDto(addressDto);
//                                return employeeDto;
//                            });
//                });
//    }



    public ResponseEntity<EmployeeDto> getEmployeeByIdUsingFeignClient(int id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional == null) {
            return  null;
        } else {
            ResponseEntity<AddressDto> addressDto = addressClient.getAddressByEmployeeId(id);
            EmployeeDto employeeDto = employeeToEmployeeDtoMapper(employeeOptional.get());
            employeeDto.setAddressDto(addressDto.getBody());
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeDto);
        }
    }

    public EmployeeDto getEmployeeByIdWithoutAddress(int id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return null;
        } else {
            EmployeeDto employeeDto = employeeToEmployeeDtoMapper(employee);
            return employeeDto;
        }
    }
}
