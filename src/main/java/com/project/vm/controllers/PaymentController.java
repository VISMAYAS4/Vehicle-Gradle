package com.project.vm.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.vm.entities.Booking;
import com.project.vm.entities.Payment;
import com.project.vm.entities.Vehicle;
import com.project.vm.repositories.IBookingRepository;
import com.project.vm.repositories.IPaymentRepository;
import com.project.vm.services.PaymentService;

@RestController
@RequestMapping(path = "/api/v1")
public class PaymentController {
	@Autowired
	IPaymentRepository paymentRepository;
	@Autowired
	IBookingRepository bookingRepository;

	@Autowired
	PaymentService paymentService;

	@PostMapping("/payments")
	public ResponseEntity<Payment> addPayment(@RequestBody Payment p) {
		Payment payment =  paymentService.addPayment(p);
		return new ResponseEntity<>(payment,HttpStatus.CREATED);
	}

	@DeleteMapping("/payments/{id}")
	public ResponseEntity<String> cancelPayment(@PathVariable("id") int id) {
		paymentService.cancelPayment(id);
		return new ResponseEntity<>("Succesfully deleted",HttpStatus.OK); 
	}

	@GetMapping("/payments")
	public  ResponseEntity<List<Payment>> viewPayments(){
		List<Payment> payments = paymentService.viewPayments();
		return new ResponseEntity<>(payments,HttpStatus.OK);
	}
	
	
	@GetMapping("/paymentByBooking")
	public ResponseEntity<Payment>  viewPaymentByBooking(@RequestBody Booking booking){
		Payment payments = paymentService.viewPayment(booking);
		return new ResponseEntity<>(payments,HttpStatus.OK); 
	}

	@GetMapping("/paymentsByVehicle")
	public ResponseEntity<List<Payment>> findPaymentsByVehicle(@RequestBody Vehicle vehicle) {
		List<Payment> payments = paymentService.viewAllPayment(vehicle);
		return new ResponseEntity<>(payments,HttpStatus.OK);
	}

	@GetMapping("/totalPaymentByVehicle")
	public ResponseEntity<Double> calculateTotalPaymentByVehicle(@RequestBody Vehicle vehicle) {
		Double revenue = paymentService.calculateTotalPayment(vehicle);
		return new ResponseEntity<>(revenue,HttpStatus.OK);
	}

	@GetMapping("/calculateMonthlyRevenue/{date1}/{date2}")
	public ResponseEntity<Double> calculateTMonthlyRevenue(@PathVariable("date1") 
	@DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate  date1, @PathVariable("date2") 
	@DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate  date2) {
		Double revenue = paymentService.calculateMonthlyRevenue(date1,date2);
		return new ResponseEntity<>(revenue,HttpStatus.OK);
	}
}