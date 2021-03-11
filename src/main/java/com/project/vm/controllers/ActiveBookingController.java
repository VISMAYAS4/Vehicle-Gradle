package com.project.vm.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.vm.entities.Booking;
import com.project.vm.services.ActiveBookingService;

@RestController
@RequestMapping(path = "/api/v1")
public class ActiveBookingController {
	
	@Autowired
	ActiveBookingService activeBookingService;
	
	@GetMapping("/activeBookings")
	public ResponseEntity<List<Booking>> viewActiveBookings() {
		List<Booking> bookings =  activeBookingService.viewActiveBookings();
		return new ResponseEntity<>(bookings,HttpStatus.OK);
	}
	
	@GetMapping("/activeBookings/{date}")
	public ResponseEntity<List<Booking>> viewActiveBookingByBookingDate(@PathVariable("date") 
    @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate  date) {
		List<Booking> bookings =  activeBookingService.viewActiveBookingByBookingDate(date);
		return new ResponseEntity<>(bookings,HttpStatus.OK);
	}
	
	@GetMapping("/activeBookings/{from}/{to}")
	public ResponseEntity<List<Booking>> viewActiveBookingBetweenDates(@PathVariable("from") 
    @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate  from, @PathVariable("to") 
    @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate  to) {
		List<Booking> bookings =  activeBookingService.viewActiveBookingBetweenDates(from, to);
		return new ResponseEntity<>(bookings,HttpStatus.OK);
	}
	
}
