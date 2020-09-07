package net.guides.springboot2.springboot2jpacrudexample.model;

import java.util.Date;

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
@Table(name = "leaves")
public class Leave {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="from_date",nullable = false)
	Date fromDate;
	
	@Column(name="to_date",nullable = false)
	Date toDate;
	
	@Column(name="reason",nullable = false)
	String reason;
	
	 
		@ManyToOne(fetch= FetchType.LAZY)
		 @JoinColumn(name = "employee_id")
		private Employee employee;


		public long getId() {
			return id;
		}


		public void setId(long id) {
			this.id = id;
		}


		public Date getFromDate() {
			return fromDate;
		}


		public void setFromDate(Date fromDate) {
			this.fromDate = fromDate;
		}


		public Date getToDate() {
			return toDate;
		}


		public void setToDate(Date toDate) {
			this.toDate = toDate;
		}


		public String getReason() {
			return reason;
		}


		public void setReason(String reason) {
			this.reason = reason;
		}


		public Employee getEmployee() {
			return employee;
		}


		public void setEmployee(Employee employee) {
			this.employee = employee;
		}


		@Override
		public String toString() {
			return "Leave [id=" + id + ", fromDate=" + fromDate + ", toDate=" + toDate + ", reason=" + reason
					+ ", employee=" + employee + "]";
		}
		

}
