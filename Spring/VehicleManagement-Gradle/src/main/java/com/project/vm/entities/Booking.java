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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@ApiModel(description = "Details about the Booking")
public class Booking {
	@Id
	@SequenceGenerator(name="booking_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO ,generator = "booking_sequence")
	@ApiModelProperty(notes = "Booking id for a booking")
	private int bookingId;
	

	@OneToOne(cascade = CascadeType.PERSIST )
	@JoinColumn(name = "customerId")
	@ApiModelProperty(notes = "Customer for booking [specify customer first name and last name]")
	private Customer customer;
	
	@OneToOne(cascade = CascadeType.PERSIST )
	@JoinColumn(name = "vehicleId")
	@ApiModelProperty(notes = "Vehicle for booking [specify vehicle number]")
	private Vehicle vehicle;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(notes = "Booking date", example = "22-02-2022")
	private LocalDate bookingDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(notes = "Booking till date", example = "22-02-2022")
	private LocalDate bookedTillDate;
	
	@ApiModelProperty(notes = "Booking description")
	private String bookingDescription;
	@ApiModelProperty(notes = "Total cost for booking")
	private double totalCost;
	@ApiModelProperty(notes = "Distance to be travelled")
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

	public void setTotalCost(double distance) {
		this.totalCost = (distance * this.vehicle.getChargesPerKm()) + this.vehicle.getFixedCharges();
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
