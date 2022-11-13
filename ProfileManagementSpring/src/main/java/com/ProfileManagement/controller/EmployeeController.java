package com.ProfileManagement.controller;

import static com.ProfileManagement.constants.DesignationConstants.PM;
import static com.ProfileManagement.constants.DesignationConstants.SA;
import static com.ProfileManagement.constants.DesignationConstants.SE;
import static com.ProfileManagement.constants.DesignationConstants.SSE;
import static com.ProfileManagement.constants.DesignationConstants.TL;

import java.util.List;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProfileManagement.exception.DesignationInvalidException;
import com.ProfileManagement.exception.ExperienceExceed;
import com.ProfileManagement.exception.PhonenumberException;
import com.ProfileManagement.exception.ResourceNotFoundException;
import com.ProfileManagement.models.Employee;
import com.ProfileManagement.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") String employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) throws Exception {
		//MObile Number validation
		mobileNumberValidation(employee);
		//Experience and Designation Validation 
		experienceAndDesigantionValidation(employee);
		return employeeRepository.save(employee);
	}
	/*
	 * 
	 */
	private void experienceAndDesigantionValidation(Employee employee) throws Exception {
		if(employee.getExperience()>20)
			throw new ExperienceExceed("Experience sholud be less than 20 years");
		
		switch (employee.getDesignation()) {
		case SA:
				if(employee.getExperience() > 3.0)
					throw new DesignationInvalidException("Invalid Designation for given experience");
			break;
		case SE:
			if(!(employee.getExperience() > 3.0 && employee.getExperience() <=5.0))
				throw new DesignationInvalidException("Invalid Designation for given experience");
		break;
		case SSE:
			if(!(employee.getExperience() > 5.0 && employee.getExperience() <=10.0))
				throw new DesignationInvalidException("Invalid Designation for given experience");
		break;
		case TL:
			if(!(employee.getExperience() > 10.0 && employee.getExperience() <=15.0))
				throw new DesignationInvalidException("Invalid Designation for given experience");
		break;
		case PM:
			if(!(employee.getExperience() > 15.0 && employee.getExperience() <=20.0))
				throw new DesignationInvalidException("Invalid Designation for given experience");
		break;

		default:
			break;
		}
	}
	
	private void mobileNumberValidation(Employee employee) throws Exception {
		if(!(Pattern.matches("\\d{10}", employee.getMobile().toString()))) {
			 throw new PhonenumberException("The Mobile number must be 10 digits number");    
		}
	}
}