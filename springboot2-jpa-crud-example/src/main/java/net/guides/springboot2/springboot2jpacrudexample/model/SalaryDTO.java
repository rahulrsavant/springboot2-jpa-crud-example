package net.guides.springboot2.springboot2jpacrudexample.model;

import java.io.Serializable;

public class SalaryDTO implements Serializable {


	private long id;
	private long employeeId;
	private String salaryYear;
	private String salaryMonth;
	private String salaryAmount;

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
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	@Override
	public String toString() {
		return "SalaryDTO [id=" + id + ", employeeId=" + employeeId + ", salaryYear=" + salaryYear + ", salaryMonth="
				+ salaryMonth + ", salaryAmount=" + salaryAmount + "]";
	}




}
