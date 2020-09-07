package net.guides.springboot2.springboot2jpacrudexample.service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.guides.springboot2.springboot2jpacrudexample.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2jpacrudexample.model.Employee;
import net.guides.springboot2.springboot2jpacrudexample.model.Leave;
import net.guides.springboot2.springboot2jpacrudexample.model.LeaveDTO;
import net.guides.springboot2.springboot2jpacrudexample.repository.EmployeeRepository;

@Service
public class LeaveService {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Leave leaveDTO2Entity(LeaveDTO leavedto) {
		Leave leave=new Leave();	
		Employee employee=null;
		
		long emp_id=leavedto.getEmployeeId();
		
		try {
			employee = employeeRepository.findById(emp_id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + emp_id));
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		
		leave.setEmployee(employee);
		leave.setId(leavedto.getId());
		leave.setFromDate(leavedto.getFromDate());
		leave.setToDate(leavedto.getToDate());
		leave.setReason(leavedto.getReason());
		return leave;	 
	}
	
	//public <List>LeaveDTO salaryList2SalaryListDTO(<List>Leave leavelist){
	public List<LeaveDTO> leaveListEntity2LeaveListDTO(List<Leave> leavelist){	
		
		List<LeaveDTO> leavedtolist=new ArrayList<LeaveDTO>();
		
		for (Iterator<Leave> iterator = leavelist.iterator(); iterator.hasNext();) {
			Leave leave = (Leave) iterator.next();
			LeaveDTO leavedto=new LeaveDTO();
			if(null!=leave.getEmployee())
			{
			long employee_id=leave.getEmployee().getId();					
			leavedto.setEmployeeId(employee_id);
			}else {
				System.out.println("Employee is null...................................");
			}
			
			leavedto.setId(leave.getId());
			
			leavedto.setFromDate(leave.getFromDate());
			leavedto.setToDate(leave.getToDate());
			leavedto.setReason(leave.getReason());
			//===================================			
			leavedtolist.add(leavedto);
					
		}
		return leavedtolist;		
	}
	
	public LeaveDTO leaveEntityToDTO(Leave leave)
	{
		LeaveDTO leavedto =new LeaveDTO();
		leavedto.setEmployeeId(leave.getEmployee().getId());
		leavedto.setId(leave.getId());
		
		leavedto.setFromDate(leave.getFromDate());
		leavedto.setToDate(leave.getToDate());
		leavedto.setReason(leave.getReason());
	
		return leavedto;
		
	}
	
	public List<Leave> leaveListDTO2LeaveListEntity(List<LeaveDTO> leaveDTOList){	
		
		List<Leave> leavelist=new ArrayList<Leave>();
		
		for (Iterator<LeaveDTO> iterator = leaveDTOList.iterator(); iterator.hasNext();) {
			LeaveDTO leavedto = (LeaveDTO) iterator.next();
			Leave leave=new Leave();
			Employee employee=null;
			
			
			try {
				employee = employeeRepository.findById(leavedto.getEmployeeId())
						.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + leavedto.getEmployeeId()));
			} catch (ResourceNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
					
			leave.setEmployee(employee);
			leave.setId(leavedto.getId());
	
			leave.setFromDate(leavedto.getFromDate());
			leave.setToDate(leavedto.getToDate());
			leave.setReason(leavedto.getReason());
	
			leavelist.add(leave);
					
		}
		return leavelist;		
	}
}
