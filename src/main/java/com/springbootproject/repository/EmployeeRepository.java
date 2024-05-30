package com.springbootproject.repository;

import com.springbootproject.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Query(value = "Select * from employee where IsActive = true",nativeQuery = true)
    List<Employee> getAllEmplyees();
}
