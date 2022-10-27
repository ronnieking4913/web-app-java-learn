package com.web_app.controllers;

import com.web_app.exceptions.ResourceNotFoundException;
import com.web_app.models.Employee;
import com.web_app.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.web_app.repositories.EmployeeRepository;

import java.util.Arrays;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    // build create employee REST API
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @GetMapping("/roles")
    public List<Role> getRoles(){

        return Arrays.asList(Role.values());

    }

    // build get employee by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeByID(@PathVariable long id){

        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return ResponseEntity.ok(employee);

    }

    // build update employee REST API
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee updateEmployeeDetails){

        Employee updateEmployee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        updateEmployee.setRole(updateEmployeeDetails.getRole());
        updateEmployee.setFirstName(updateEmployeeDetails.getFirstName());
        updateEmployee.setLastName(updateEmployeeDetails.getLastName());
        updateEmployee.setEmailId(updateEmployeeDetails.getEmailId());

        employeeRepository.save(updateEmployee);

        return ResponseEntity.ok(updateEmployee);
    }

    // build delete employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employeeRepository.delete(employee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
