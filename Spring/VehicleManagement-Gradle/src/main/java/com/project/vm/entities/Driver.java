package com.project.vm.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Driver {
	@Id
	@SequenceGenerator(name="driver_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO ,generator = "driver_sequence")
	private int driverId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String mobileNumber;
	private String address;
	private String licenseNo;
	
	public Driver() {

	}

	public Driver(String firstName, String lastName, String emailId, String mobileNumber, String address,
			String licenseNo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.licenseNo = licenseNo;
	}
	
	public Driver(int driverId, String firstName, String lastName, String emailId, String mobileNumber, String address,
			String licenseNo) {
	 this(firstName, lastName, emailId, mobileNumber, address, licenseNo);
	 this.driverId = driverId;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
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

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	@Override
	public String toString() {
		return "Driver [driverId=" + driverId + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId="
				+ emailId + ", mobileNumber=" + mobileNumber + ", address=" + address + ", licenseNo=" + licenseNo
				+ "]";
	}
	
	
}
