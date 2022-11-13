package com.ProfileManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProfileManagement.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{

}