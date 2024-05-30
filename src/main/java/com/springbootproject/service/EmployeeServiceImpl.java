package com.springbootproject.service;

import com.springbootproject.entity.*;
import com.springbootproject.exception.ResourceNotFound;
import com.springbootproject.model.request.*;
import com.springbootproject.model.response.*;
import com.springbootproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
   
    @Override
    public ResponseEntity<EmployeeResponse> saveEmployee(EmployeeRequest employeeRequest) throws Exception {
        try {
            EmployeeResponse employeeResponse = new EmployeeResponse();

            // Create and set the employee
            Employee employee = new Employee();
            employee.setFirstname(employeeRequest.getFirstname());
            employee.setLastname(employeeRequest.getLastname());
            employee.setEmail(employeeRequest.getEmail());

            // Save the employee
            employeeRepository.save(employee);
            employeeResponse.setMessage("Saved Successfully");
            return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }
    }

    //---------------------------------------------------------------------------------------------------------------

    @Override
    public ResponseEntity<List<EmployeeAllResponse>> getEmployees() {
        try {
            List<EmployeeAllResponse> employeeAllResponselist = new ArrayList<>();

            List<Employee> employee = employeeRepository.getAllEmplyees();
            for (Employee employeedata : employee) {
                EmployeeAllResponse employeeAllResponse = new EmployeeAllResponse();
                employeeAllResponse.setId(employeedata.getId());
                employeeAllResponse.setFirstname(employeedata.getFirstname());
                employeeAllResponse.setEmail(employeedata.getEmail());

                employeeAllResponselist.add(employeeAllResponse);
            }

            return new ResponseEntity<>(employeeAllResponselist, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Invalid Request");
        }
        return null;
    }


    //--------------------------------------------------------------------------------------------------------------

    @Override
    public ResponseEntity<EmployeeAllResponse> getEmployeById(Integer id) {
        try {

            Optional<Employee> employeeOptional = employeeRepository.findById(id);

            if (employeeOptional.isPresent()) {
                Employee employee = employeeOptional.get();
                EmployeeAllResponse employeeAllResponse = new EmployeeAllResponse();
                employeeAllResponse.setId(employee.getId());
                employeeAllResponse.setFirstname(employee.getFirstname());
                employeeAllResponse.setLastname(employee.getLastname());
                employeeAllResponse.setEmail(employee.getEmail());

                return ResponseEntity.ok(employeeAllResponse);

            }
        } catch (ResourceNotFound resourceNotFound) {
            throw resourceNotFound;
        }
        return null;
    }

    //---------------------------------------------------------------------------------------------------------

    @Override
    public ResponseEntity<EmployeeAllResponse> updatedEmployeeById(UpdatedEmployee updatedEmployee, Integer id) {
        try {
            EmployeeAllResponse employeeAllResponse = new EmployeeAllResponse();

            EmployeeResponse employeeResponse = new EmployeeResponse();

            Optional<Employee> optionalEmployee = employeeRepository.findById(id);


            if (optionalEmployee.isPresent()) {

                optionalEmployee.get().setFirstname(updatedEmployee.getFirstname());
                optionalEmployee.get().setLastname(updatedEmployee.getLastname());

                employeeAllResponse.setId(updatedEmployee.getId());
                employeeAllResponse.setFirstname(updatedEmployee.getFirstname());
                employeeAllResponse.setLastname(updatedEmployee.getLastname());
                employeeAllResponse.setEmail(updatedEmployee.getEmail());

                employeeRepository.save(optionalEmployee.get());

                employeeResponse.setMessage("updated Successfully");
                return new ResponseEntity<>(employeeAllResponse, HttpStatus.OK);

            } else {
                employeeResponse.setMessage("not saved");
                return new ResponseEntity<>(employeeAllResponse, HttpStatus.NOT_FOUND);
            }
        } catch (NullPointerException npe) {
            System.out.println("Invalid Request ");
        }
        return null;
    }


    //------------------------------------------------------------------------------------------------------------

    @Override
    public ResponseEntity<Object> deleteEmployee(Integer id) {
        try {
            Optional<Employee> optionalEmployee = employeeRepository.findById(id);

            if (optionalEmployee.isPresent()) {
                Employee employee = optionalEmployee.get();
                employeeRepository.deleteById(id);

            } else {
                return ResponseEntity.notFound().build();
            }
            return null;
        } catch (ResourceNotFound rnf) {
            System.out.println("Invalid Credentials");
        }
        return null;
    }


    //-------------------------------------------------------------------------------------------------------------


}