package net.guides.springboot2.springboot2jpacrudexample.model;

import java.util.List;

public class EmployeeDTO {
	private long id;	
	private String firstName;	
	private String lastName;
	private String emailId;
	private  List <SalaryDTO> salaryDTO;
	

	public EmployeeDTO() {		
	}
	
	public EmployeeDTO(String firstName, String lastName, String emailId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}	

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	

	public List<SalaryDTO> getSalaryDTO() {
		return salaryDTO;
	}

	public void setSalaryDTO(List<SalaryDTO> salaryDTO) {
		this.salaryDTO = salaryDTO;
	}

	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "EmployeeDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", salaryDTO=" + salaryDTO + "]";
	}


	
}
