package com.springbootproject.service;

//import com.springbootproject.entity.CustomEntityList;
//import com.springbootproject.entity.EmployeeSPAllData;
import com.springbootproject.model.request.EmployeeRequest;
import com.springbootproject.model.request.UpdatedEmployee;
import com.springbootproject.model.response.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {

    ResponseEntity<EmployeeResponse> saveEmployee(EmployeeRequest employeeRequest)throws Exception;

    ResponseEntity<List<EmployeeAllResponse>>getEmployees();

    ResponseEntity<EmployeeAllResponse> getEmployeById(Integer id);

    ResponseEntity<EmployeeAllResponse> updatedEmployeeById(UpdatedEmployee updatedEmployee, Integer id);

    ResponseEntity<Object> deleteEmployee(Integer id);



}
