package net.guides.springboot2.springboot2jpacrudexample.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import net.guides.springboot2.springboot2jpacrudexample.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2jpacrudexample.model.Employee;
import net.guides.springboot2.springboot2jpacrudexample.model.EmployeeDTO;
import net.guides.springboot2.springboot2jpacrudexample.repository.EmployeeRepository;


@Service
public class EmployeeService {
	@Autowired
	SalaryService salaryservice;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeService employeeService;
	
	
	public Employee employeeDTO2Entity(EmployeeDTO empdto) {

		Employee emp=new Employee();
		emp.setEmailId(empdto.getEmailId());
		emp.setFirstName(empdto.getFirstName());
		emp.setId(empdto.getId());
		emp.setLastName(empdto.getLastName());
	
		return emp;
	}
	
	public EmployeeDTO employeeEntity2DTO(Employee employee) {
		EmployeeDTO empdto=new EmployeeDTO();
		empdto.setEmailId(employee.getEmailId());
		empdto.setFirstName(employee.getFirstName());
		empdto.setId(employee.getId());
		empdto.setLastName(employee.getLastName());	
		return empdto;
		
	}
	
	public List<EmployeeDTO> salaryListEntity2SalaryListDTO(List<Employee> employeelist){	
		List<EmployeeDTO> employeedtolist=new ArrayList<EmployeeDTO>();
		
		for (Iterator<Employee> iterator = employeelist.iterator(); iterator.hasNext();) {
			Employee employee = (Employee) iterator.next();
			EmployeeDTO employeedto=new EmployeeDTO();
	
			employeedto.setId(employee.getId());
			employeedto.setEmailId(employee.getEmailId());
			employeedto.setFirstName(employee.getFirstName());
			employeedto.setLastName(employee.getLastName());
			
			employeedtolist.add(employeedto);					
		}
		return employeedtolist;
	}
	//==============================================================================================

	@Cacheable(cacheNames = { "userCache" })
	public List<EmployeeDTO> getAllEmployees() {
		List<Employee> employeelist= employeeRepository.findAll();
			List<EmployeeDTO> employeedto=employeeService.salaryListEntity2SalaryListDTO(employeelist);
			System.out.println("#################################### getAllEmployees() called #####################################");
		return employeedto;
	}
	


	public EmployeeDTO getEmployeeById(Long employeeId)
			throws ResourceNotFoundException {
		
		EmployeeDTO empdto;
		
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		
		empdto=employeeService.employeeEntity2DTO(employee);
		
		return empdto;
	}


	public Employee createEmployee(EmployeeDTO employeedto) {
		Employee employee=employeeService.employeeDTO2Entity(employeedto);
		return employeeRepository.save(employee);
	}


	public EmployeeDTO updateEmployee(Long employeeId,EmployeeDTO employeeDetails) throws ResourceNotFoundException {
		EmployeeDTO empdto;
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employee.setEmailId(employeeDetails.getEmailId());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		final Employee updatedEmployee = employeeRepository.save(employee);
		empdto=employeeService.employeeEntity2DTO(updatedEmployee);
		return empdto;
	}


	public Map<String, Boolean> deleteEmployee(Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
