package com.spring.demo.controller;

import com.spring.demo.exceptions.CustomException;
import com.spring.demo.model.Employee;
import com.spring.demo.model.ResponseModel;
import com.spring.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public ResponseModel getAllEmployees(){
        ResponseModel responseModel = new ResponseModel();
        responseModel.data = Collections.singletonList(employeeRepository.getAllEmployees(Sort.by("firstName")));
        responseModel.message = "";
        return responseModel;
    }

    @GetMapping("/employeesPageable")
    public ResponseModel getAllEmployeesPageable(@RequestParam int pageNo) {
        ResponseModel responseModel = new ResponseModel();
        Page<Employee> employeePage = employeeRepository
                .findEmployeeswithPagination(PageRequest.of(pageNo,2));
        responseModel.data = Collections.singletonList(employeePage.getContent());
        responseModel.message = "";
        return responseModel;
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
            throws CustomException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new CustomException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }
    @GetMapping("/employeeByfirstNameOrlastName")
    public ResponseModel getEmployeeByfirstNameOrlastName(@RequestParam(value="firstName") String firstName,
                                                          @RequestParam(value="lastName") String lastName) {
        ResponseModel responseModel = new ResponseModel();
        responseModel.data = Collections.singletonList(employeeRepository.findByFirstNameOrLastName(firstName,lastName));
        responseModel.message = "";
        return responseModel;
    }


    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }


    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                   @Valid @RequestBody Employee employeeDetails) throws CustomException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new CustomException("Employee not found for this id :: " + employeeId));

        employee.emailId = employeeDetails.emailId;
        employee.lastName = employeeDetails.lastName;
        employee.firstName = employeeDetails.firstName;
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
            throws CustomException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new CustomException("Employee not found for this id :: " + employeeId));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

