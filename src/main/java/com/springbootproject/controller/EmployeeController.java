package com.springbootproject.controller;

import com.springbootproject.exception.ResourceNotFound;
import com.springbootproject.model.request.EmployeeRequest;
import com.springbootproject.model.request.UpdatedEmployee;
import com.springbootproject.model.response.*;
import com.springbootproject.repository.*;
import com.springbootproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;


    @PostMapping("/saveemployees")
    ResponseEntity<EmployeeResponse> saveEmployee(@RequestBody EmployeeRequest employeeRequest) throws Exception {
        return employeeService.saveEmployee(employeeRequest);
    }

    @GetMapping("/getAllEmployees")
    ResponseEntity<List<EmployeeAllResponse>> getAllEmplyess() {
        return employeeService.getEmployees();
    }

    @GetMapping("/getEmployees/{id}")
    ResponseEntity<EmployeeAllResponse> getEmployee(@PathVariable Integer id) throws ResourceNotFound {
        return employeeService.getEmployeById(id);
    }

    @PutMapping("/updateemployees/{id}")
    public ResponseEntity<EmployeeAllResponse> updateEmployee(@RequestBody UpdatedEmployee updatedEmployee, @PathVariable Integer id) {
        return employeeService.updatedEmployeeById(updatedEmployee, id);

    }

    @DeleteMapping("/deleteemployees/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Integer id) {
        return employeeService.deleteEmployee(id);
    }


}