package net.guides.springboot2.springboot2jpacrudexample.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.guides.springboot2.springboot2jpacrudexample.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2jpacrudexample.model.Employee;
import net.guides.springboot2.springboot2jpacrudexample.model.Salary;
import net.guides.springboot2.springboot2jpacrudexample.model.SalaryDTO;
import net.guides.springboot2.springboot2jpacrudexample.repository.EmployeeRepository;

@Service
public class SalaryService {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Salary salaryDTO2Entity(SalaryDTO saldto) {
		Salary salary=new Salary();	
		Employee employee=null;
		
		long emp_id=saldto.getEmployeeId();
		
		try {
			employee = employeeRepository.findById(emp_id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + emp_id));
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		
		salary.setEmployee(employee);
		salary.setId(saldto.getId());
		salary.setSalaryAmount(saldto.getSalaryAmount());
		salary.setSalaryMonth(saldto.getSalaryMonth());
		salary.setSalaryYear(saldto.getSalaryYear());
		return salary;	 
	}
	
	//public <List>SalaryDTO salaryList2SalaryListDTO(<List>Salary salarylist){
	public List<SalaryDTO> salaryListEntity2SalaryListDTO(List<Salary> salarylist){	
		
		List<SalaryDTO> salarydtolist=new ArrayList<SalaryDTO>();
		
		for (Iterator<Salary> iterator = salarylist.iterator(); iterator.hasNext();) {
			Salary salary = (Salary) iterator.next();
			SalaryDTO salarydto=new SalaryDTO();
			if(null!=salary.getEmployee())
			{
			long employee_id=salary.getEmployee().getId();					
			salarydto.setEmployeeId(employee_id);
			}else {
				System.out.println("Employee is null...................................");
			}
			
			salarydto.setId(salary.getId());
			salarydto.setSalaryAmount(salary.getSalaryAmount());
			salarydto.setSalaryMonth(salary.getSalaryMonth());
			salarydto.setSalaryYear(salary.getSalaryYear());	
			//========================================================

			
			salarydtolist.add(salarydto);
					
		}
		return salarydtolist;		
	}
	
	public SalaryDTO salaryEntityToDTO(Salary salary)
	{
		SalaryDTO salarydto =new SalaryDTO();
		salarydto.setEmployeeId(salary.getEmployee().getId());
		salarydto.setId(salary.getId());
		salarydto.setSalaryAmount(salary.getSalaryAmount());
		salarydto.setSalaryMonth(salary.getSalaryMonth());
		salarydto.setSalaryYear(salary.getSalaryYear());
		return salarydto;
		
	}
	
	public List<Salary> salaryListDTO2SalaryListEntity(List<SalaryDTO> salaryDTOList){	
		
		List<Salary> salarylist=new ArrayList<Salary>();
		
		for (Iterator<SalaryDTO> iterator = salaryDTOList.iterator(); iterator.hasNext();) {
			SalaryDTO salarydto = (SalaryDTO) iterator.next();
			Salary salary=new Salary();
			Employee employee=null;
			
			
			try {
				employee = employeeRepository.findById(salarydto.getEmployeeId())
						.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + salarydto.getEmployeeId()));
			} catch (ResourceNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
					
			salary.setEmployee(employee);
			salary.setId(salarydto.getId());
			salary.setSalaryAmount(salarydto.getSalaryAmount());
			salary.setSalaryMonth(salarydto.getSalaryMonth());
			salary.setSalaryYear(salarydto.getSalaryYear());	
			salarylist.add(salary);
					
		}
		return salarylist;		
	}
}
