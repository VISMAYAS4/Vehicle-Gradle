package com.project.vm.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Pattern;

@Entity
public class Customer {
	@Id
	@SequenceGenerator(name="customer_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO ,generator = "customer_sequence")
	private int customerId;
	private String firstName;
	private String lastName;
	
	@Pattern(regexp = "^(.+)@(.+)$", message = "Provide valid email")
	private String emailId;
	
	@Pattern(regexp = "[0-9]{10}", message = "Mobile number should be 10 digits")
	private String mobileNumber;
	private String address;
	
	public Customer() {
		
	}

	public Customer(String firstName, String lastName, String emailId, String mobileNumber, String address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.address = address;
	}
	
	public Customer(int customerId, String firstName, String lastName, String emailId, String mobileNumber, String address) {
		this(firstName,lastName,emailId,mobileNumber,address);
		this.customerId = customerId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailId=" + emailId + ", mobileNumber=" + mobileNumber + ", address=" + address + "]";
	}
		
}
