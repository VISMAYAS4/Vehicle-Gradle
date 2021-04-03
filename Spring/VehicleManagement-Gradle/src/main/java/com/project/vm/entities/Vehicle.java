package com.project.vm.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Vehicle {
	@Id
	@SequenceGenerator(name="vehicle_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO ,generator = "vehicle_sequence")
	private int vehicleId;
	private String vehicleNumber;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "driverId")
	private Driver driver;
	private String type;
	private String category;
	private String description;
	private String location;
	private int capacity;
	private double chargesPerKm;
	private double fixedCharges;
	
	public Vehicle() {
		
	}

	public Vehicle(String vehicleNumber, Driver driver, String type, String category, String location, String description, int capacity,
			double chargesPerKm, double fixedCharges) {
		super();
		this.vehicleNumber = vehicleNumber;
		this.driver = driver;
		this.type = type;
		this.category = category;
		this.description = description;
		this.location = location;
		this.capacity = capacity;
		this.chargesPerKm = chargesPerKm;
		this.fixedCharges = fixedCharges;
	}
	
	public Vehicle(int vehicleId, String vehicleNumber, Driver driver, String type, String category, String location, String description, int capacity,
			double chargesPerKm, double fixedCharges) {
		this(vehicleNumber, driver, type, category, location, description, capacity, chargesPerKm, fixedCharges);
		this.vehicleId = vehicleId;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capapcity) {
		this.capacity = capapcity;
	}

	public double getChargesPerKm() {
		return chargesPerKm;
	}

	public void setChargesPerKm(double chargesPerKm) {
		this.chargesPerKm = chargesPerKm;
	}

	public double getFixedCharges() {
		return fixedCharges;
	}

	public void setFixedCharges(double fixedCharges) {
		this.fixedCharges = fixedCharges;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", vehicleNumber=" + vehicleNumber + ", driver=" + driver + ", type="
				+ type + ", category=" + category + ", description=" + description + ", location=" + location
				+ ", capacity=" + capacity + ", chargesPerKm=" + chargesPerKm + ", fixedCharges=" + fixedCharges
				+ "]";
	}
	
	
}
