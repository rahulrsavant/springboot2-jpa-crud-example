package net.guides.springboot2.springboot2jpacrudexample.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "employees")
public class Employee implements Serializable {
  
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "email_address", nullable = false)
	private String emailId;
	
	 @OneToMany(mappedBy="employee",cascade = CascadeType.ALL)
	 private List <Salary> salaryList;
	 
	 @OneToMany(mappedBy="employee",cascade = CascadeType.ALL)
	 private List <Leave> leaveList;
	


	public Employee() {
		
	}
	
	public Employee(String firstName, String lastName, String emailId) {
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
	
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public List<Salary> getSalaryList() {
		return salaryList;
	}

	public void setSalaryList(List<Salary> salaryList) {
		this.salaryList = salaryList;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", salaryList=" + salaryList + "]";
	}


	
}
