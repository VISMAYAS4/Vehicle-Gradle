package com.project.vm.serviceInterfaces;

import java.time.LocalDate;
import java.util.List;

import com.project.vm.entities.Booking;

//IBookingService holds the declarations of service methods.
public interface IBookingService {
	public Booking addBooking(Booking booking);
	public Booking cancelBooking(int id);
	public Booking updateBooking(Booking b);
	public Booking viewBooking(int id);
	public List<Booking> viewAllBooking(String name);
	public List<Booking> viewAllBookingByDate(LocalDate bookingDate);
	public List<Booking> viewAllBookingByVehicle(String vehicleNumber); 
	
}
