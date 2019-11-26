package com.societegenerale.katatest.controller;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.societegenerale.katatest.exception.ResourceNotFoundException;
import com.societegenerale.katatest.model.Employee;
import com.societegenerale.katatest.repository.EmployeeRepository;

/**
 * This is controller .Used  for request mapping .
 * @author Kalpesh Kotkar
 *
 */
@RestController
@RequestMapping("/api/version")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	

	/**
	 * This method is used to retrieve the all employee details by ascending order by first name.
	 * @return 
	 */
	@GetMapping("/employee")
	public List<Employee> getAllEmployees() {
		List<Employee> empList=employeeRepository.findAll();
		List<Employee> sortedList = empList.stream()
				.sorted(Comparator.comparing(Employee::getFirstName))
				.collect(Collectors.toList());
		return sortedList;
	}
	
	/**
	 * This method is used to retrieve employee details by id ;
	 * @param employeeId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(
			@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	/**
	 * This method is used to create the employee .
	 * @param employee
	 * @return
	 */
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	/**
	 * This method is used to update the employee details.
	 * @param empId
	 * @param employeeDetails
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(
			@PathVariable(value = "id") Long empId,
			@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(empId)
		        .orElseThrow(() -> new ResourceNotFoundException("Employee not found :: " + empId));
		employee.setEmailId(employeeDetails.getEmailId());
		employee.setDepartment(employeeDetails.getDepartment());
		employee.setDob(employeeDetails.getDob());
		employee.setGender(employeeDetails.getGender());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		final Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	/**
	 * This method is used to delete the employee details by id .
	 * @param empId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(
			@PathVariable(value = "id") Long empId) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(empId)
		        .orElseThrow(() -> new ResourceNotFoundException("Employee not found :: " + empId));
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
