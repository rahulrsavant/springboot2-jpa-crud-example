package net.guides.springboot2.springboot2jpacrudexample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "salaries")
public class Salary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "salary_year", nullable = true)
	private String salaryYear;
	
	@Column(name = "salary_month", nullable = true)
	private String salaryMonth;
	
	@Column(name = "salary_amount", nullable = true)
	private String salaryAmount;

	 //@ManyToOne(cascade = CascadeType.ALL)
	@ManyToOne(fetch= FetchType.LAZY)
	 @JoinColumn(name = "employee_id")
	private Employee employee;
	 
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Salary() {
		
	}
	
	public Salary(String salaryYear, String salaryMonth, String salaryAmount) {
		this.salaryYear = salaryYear;
		this.salaryMonth = salaryMonth;
		this.salaryAmount = salaryAmount;
	}
	


	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	
	
	public String getSalaryYear() {
		return salaryYear;
	}

	public void setSalaryYear(String salaryYear) {
		this.salaryYear = salaryYear;
	}

	
	public String getSalaryMonth() {
		return salaryMonth;
	}

	public void setSalaryMonth(String salaryMonth) {
		this.salaryMonth = salaryMonth;
	}


	public String getSalaryAmount() {
		return salaryAmount;
	}

	public void setSalaryAmount(String salaryAmount) {
		this.salaryAmount = salaryAmount;
	}

	@Override
	public String toString() {
		return "Salary [id=" + id + ", salaryYear=" + salaryYear + ", salaryMonth=" + salaryMonth + ", salaryAmount="
				+ salaryAmount + ", employee=" + employee + "]";
	}


}
