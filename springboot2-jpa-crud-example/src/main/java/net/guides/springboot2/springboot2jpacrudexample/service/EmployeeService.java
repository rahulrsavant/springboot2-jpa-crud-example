package net.guides.springboot2.springboot2jpacrudexample.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.guides.springboot2.springboot2jpacrudexample.model.Employee;
import net.guides.springboot2.springboot2jpacrudexample.model.EmployeeDTO;
import net.guides.springboot2.springboot2jpacrudexample.model.Salary;
import net.guides.springboot2.springboot2jpacrudexample.model.SalaryDTO;


@Service
public class EmployeeService {
	@Autowired
	SalaryService salaryservice;
	
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

}
