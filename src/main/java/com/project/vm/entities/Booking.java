package com.project.vm.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Booking {
	@Id
	@SequenceGenerator(name="booking_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO ,generator = "booking_sequence")
	private int bookingId;
	

	@OneToOne(cascade = CascadeType.PERSIST )
	@JoinColumn(name = "customerId")
	private Customer customer;
	
	@OneToOne(cascade = CascadeType.PERSIST )
	@JoinColumn(name = "vehicleId")
	private Vehicle vehicle;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	//@Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])[-](0?[1-9]|1[012])[-][0-9]{4}$", message = "Date format should 'DD-MM-YYYY'")
	private LocalDate bookingDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
	//@Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])[-](0?[1-9]|1[012])[-][0-9]{4}$", message = "Date format should 'DD-MM-YYYY'")
	private LocalDate bookedTillDate;
	private String bookingDescription;
	private double totalCost;
	
	//@Size(min = 50, message = "Distance should be more than 50kms")
	private double distance;
	
	public Booking() {

	}

	

	public Booking(Customer customer, Vehicle vehicle, LocalDate bookingDate, LocalDate bookedTillDate,
			String bookingDescription, double distance) {
		super();
		this.customer = customer;
		this.vehicle = vehicle;
		this.bookingDate = bookingDate;
		this.bookedTillDate = bookedTillDate;
		this.bookingDescription = bookingDescription;
		this.distance = distance;
		setTotalCost(distance);
	}

	public Booking(int bookingId,Customer customer, Vehicle vehicle, LocalDate bookingDate, LocalDate bookedTillDate,
			String bookingDescription, double distance) {
		this(customer, vehicle, bookingDate, bookedTillDate, bookingDescription, distance);
		this.bookingId = bookingId;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public LocalDate getBookedTillDate() {
		return bookedTillDate;
	}

	public void setBookedTillDate(LocalDate bookedTillDate) {
		this.bookedTillDate = bookedTillDate;
	}

	public String getBookingDescription() {
		return bookingDescription;
	}

	public void setBookingDescription(String bookingDescription) {
		this.bookingDescription = bookingDescription;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		//this.totalCost = totalCost;
		this.totalCost = (this.distance * this.vehicle.getChargesPerKm()) + this.vehicle.getFixedCharges();
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}



	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", customer=" + customer + ", vehicle=" + vehicle + ", bookingDate="
				+ bookingDate + ", bookedTillDate=" + bookedTillDate + ", bookingDescription=" + bookingDescription
				+ ", totalCost=" + totalCost + ", distance=" + distance + "]";
	}
	
	
	
}
