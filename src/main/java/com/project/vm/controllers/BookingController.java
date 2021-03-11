package com.project.vm.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.vm.entities.Booking;
import com.project.vm.entities.Customer;
import com.project.vm.entities.Vehicle;
import com.project.vm.repositories.IBookingRepository;
import com.project.vm.services.BookingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(path = "/api/v1")
@Api(value = "Booking", tags = { "BookingAPI" })
public class BookingController {

	@Autowired
	IBookingRepository bookingRepository;
	
	@Autowired
	BookingService bookingService;
	
	Optional<Booking> bookings = null;

	/**
	 * This method is for adding a booking
	 * 
	 * @param Booking
	 * @return Booking
	 * @throws NotFoundException
	 * @throws ValidationException
	 */
	@PostMapping("/bookings")
	@ApiOperation(value = "Add a booking", notes = "Provide customer firstname and vehicle number", response = Booking.class)
	public ResponseEntity<Booking> addBooking(
			@ApiParam(value = "Booking to be added", required = true) @RequestBody Booking b) {
		Booking booking = bookingService.addBooking(b);
		return new ResponseEntity<>(booking,HttpStatus.CREATED);
	}
	
	/**
	 * This method is for getting a list of all bookings
	 * 
	 * @return List<Booking>
	 * @throws NotFoundException
	 * 
	 */
	@GetMapping("/bookings")
	@ApiOperation(value = "View allbookings", response = Booking.class)
	public ResponseEntity<List<Booking>> viewAllBooking(){
		List<Booking> bookings = bookingService.viewAllBookings();	
		return new ResponseEntity<>(bookings,HttpStatus.OK);
	}
	
	/**
	 * This method is for deleting a booking by id
	 * 
	 * @return String
	 * @throws NotFoundException
	 * 
	 */
	@DeleteMapping("bookings/{id}")
	@ApiOperation(value = "Delete a booking", notes = "Provide booking id", response = Booking.class)
	public ResponseEntity<String> deleteBooking(
			@ApiParam(value = "ID value of the booking to be deleted", required = true) @PathVariable("id") int id) {
		bookingService.cancelBooking(id);
		return new ResponseEntity<>("Successfuly deleted", HttpStatus.OK);
	}
	
	/**
	 * This method is for updating bookedTillDate and description of a booking
	 * 
	 * @return String
	 * @throws NotFoundException
	 * 
	 */
	@PutMapping("/bookings")
	@ApiOperation(value = "Update a booking", notes = "Provide booking id, new bookedTillDate and new description", response = Booking.class)
	public ResponseEntity<String> updateBooking(
			@ApiParam(value = "Booking to be updated", required = true) @RequestBody Booking booking) {
		bookingService.updateBooking(booking);
		return new ResponseEntity<>("Successfuly updated", HttpStatus.ACCEPTED);
	}
	
	/**
	 * This method is for getting a list of all bookings by customer first name.
	 * 
	 * @return List<Booking>
	 * @throws NotFoundException
	 * 
	 */
	@GetMapping("/bookingsByCustomer")
	@ApiOperation(value = "View booking by customer name", notes = "Provide customer firstname", response = Booking.class)
	public ResponseEntity<List<Booking>> viewBookingByCustomer(
			@ApiParam(value = "Customer name to view bookings", required = true) @RequestBody  Customer customer) {
		List<Booking> bookings = bookingService.viewAllBooking(customer);
		return new ResponseEntity<>(bookings,HttpStatus.OK);
	}
	
	/**
	 * This method is for getting a list of all bookings by vehicle number
	 * 
	 * @return List<Booking>
	 * @throws NotFoundException
	 * 
	 */
	@GetMapping("/bookingsByVehicle")
	@ApiOperation(value = "View booking by vehicle number", notes = "Provide vehicle number", response = Booking.class)
	public ResponseEntity<List<Booking>> viewBookingByVehicle(
			@ApiParam(value = "Vehicle number to view bookings", required = true) @RequestBody Vehicle vehicle) {
		List<Booking> bookings = bookingService.viewAllBookingByVehicle(vehicle);
		return new ResponseEntity<>(bookings,HttpStatus.OK);
	}
	
	/**
	 * This method is for getting a list of all bookings by booking date.
	 * 
	 * @return List<Booking>
	 * @throws NotFoundException
	 * 
	 */
	@GetMapping("/bookingsByDate/{date}")
	@ApiOperation(value = "View bookings by booking date", notes = "Provide booking date", response = Booking.class)
	public ResponseEntity<List<Booking>> viewBookingByDate(
			@ApiParam(value = "Booking date to view bookings", required = true) @PathVariable("date") 
    @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate  date) {
		List<Booking> bookings = bookingService.viewAllBookingByDate(date);
		return new ResponseEntity<>(bookings,HttpStatus.OK);
	}
	
	/**
	 * This method is for fetching a booking by id
	 * 
	 * @return Booking
	 * @throws NotFoundException
	 * 
	 */
	@GetMapping("/bookings/{id}")
	@ApiOperation(value = "View booking by id", notes = "Provide booking id", response = Booking.class)
	public ResponseEntity<Booking> viewBookingById(
			@ApiParam(value = "ID value to view booking", required = true) @PathVariable("id") int id) {
		Booking booking = bookingService.viewBooking(id);
		return new ResponseEntity<>(booking,HttpStatus.OK);
	}
	
}
