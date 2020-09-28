package net.guides.springboot2.springboot2jpacrudexample.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.guides.springboot2.springboot2jpacrudexample.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2jpacrudexample.model.Employee;
import net.guides.springboot2.springboot2jpacrudexample.model.EmployeeDTO;
import net.guides.springboot2.springboot2jpacrudexample.repository.EmployeeRepository;
import net.guides.springboot2.springboot2jpacrudexample.service.EmployeeService;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")

public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeService employeeService;
	
	

	@GetMapping("/employees")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<EmployeeDTO> getAllEmployees() {	
		List<EmployeeDTO> employeedto=employeeService.getAllEmployees();
		return employeedto;
	}
	

	@GetMapping("/employees/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		
		EmployeeDTO empdto;
		empdto=employeeService.getEmployeeById(employeeId);		
		return ResponseEntity.ok().body(empdto);
	}

	@PostMapping("/employees")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public Employee createEmployee(@Valid @RequestBody EmployeeDTO employeedto) {
		Employee employee=employeeService.createEmployee(employeedto);
		return employeeRepository.save(employee);
	}

	@PutMapping("/employees/{id}")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody EmployeeDTO employeeDetails) throws ResourceNotFoundException {
		EmployeeDTO empdto;		
		empdto=employeeService.updateEmployee(employeeId,employeeDetails);
		return ResponseEntity.ok(empdto);
	}

	@DeleteMapping("/employees/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Map<String, Boolean> response = new HashMap<>();
		response=employeeService.deleteEmployee(employeeId);	
		return response;
	}
}
