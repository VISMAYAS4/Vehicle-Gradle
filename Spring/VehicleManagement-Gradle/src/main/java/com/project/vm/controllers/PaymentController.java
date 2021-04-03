package com.project.vm.controllers;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/v1")
@Api(value = "Payment", tags = { "PaymentAPI" })

public class PaymentController {
	static final Logger log = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	IPaymentRepository paymentRepository;
	@Autowired
	IBookingRepository bookingRepository;

	@Autowired
	PaymentService paymentService;
	/**
	 * This method is for adding a payment
	 * 
	 * @param Payment
	 * @return Payment
	 * @throws NotFoundException
	 * @throws ValidationException
	 */

	@PostMapping("/payments")
	@PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
	@ApiOperation(value = "Add a payment", notes = "Provide booking_id ", response = Payment.class)

	public ResponseEntity<Payment> addPayment(
			@ApiParam(value = "Payment to be added", required = true)@RequestBody Payment p) {
		Payment payment =  paymentService.addPayment(p);
		return new ResponseEntity<>(payment,HttpStatus.CREATED);
	}
	
	/**
	 * This method is for deleting a payment by id
	 * 
	 * @return String
	 * @throws NotFoundException
	 * 
	 */
	@DeleteMapping("/payments/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "Delete a payment", notes = "Provide payment id", response = Payment.class)
	public ResponseEntity<String> cancelPayment(
			@ApiParam(value = "ID value of the payment to be deleted", required = true)@PathVariable("id") int id) {
		paymentService.cancelPayment(id);
		return new ResponseEntity<>("Succesfully deleted",HttpStatus.OK); 
	}
	
	/**
	 * This method is for getting a list of all payments
	 * 
	 * @return List<Payment>
	 * @throws NotFoundException
	 * 
	 */
	@GetMapping("/payments")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "View allpayments", response = Payment.class)
	public  ResponseEntity<List<Payment>> viewPayments(){
		List<Payment> payments = paymentService.viewPayments();
		return new ResponseEntity<>(payments,HttpStatus.OK);
	}
	
	/**
	 * This method is for fetching a payment by booking
	 * 
	 * @return Payment
	 * @throws NotFoundException
	 * 
	 */
	@GetMapping("/paymentByBooking")
	@PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
	@ApiOperation(value = "View payment by booking", notes = "Provide booking id", response = Payment.class)
	public ResponseEntity<Payment>  viewPaymentByBooking(
			@ApiParam(value = "ID value to view payment", required = true)@RequestBody Booking booking){
		Payment payments = paymentService.viewPayment(booking);
		return new ResponseEntity<>(payments,HttpStatus.OK); 
	}
	
	/**
	 * This method is for fetching a payment by vehicle
	 * 
	 * @return List<Payment>
	 * @throws NotFoundException
	 * 
	 */
	@GetMapping("/paymentsByVehicle")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "View payment by vehicle", notes = "Provide vehicle number", response = Payment.class)
	public ResponseEntity<List<Payment>> findPaymentsByVehicle(
			@ApiParam(value = "Vehicle number to view payments", required = true)@RequestBody Vehicle vehicle) {
		List<Payment> payments = paymentService.viewAllPayment(vehicle);
		return new ResponseEntity<>(payments,HttpStatus.OK);
	}
	
	/**
	 * This method is for calculating  TotalPayment by vehicle
	 * 
	 * @return Revenue
	 * @throws NotFoundException
	 * 
	 */
	@GetMapping("/totalPaymentByVehicle")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "Calculate TotalPayment by vehicle", notes = "Provide vehicle number", response = Payment.class)
	public ResponseEntity<Double> calculateTotalPaymentByVehicle(
			@ApiParam(value = "Vehicle number to calculate TotalPayment ", required = true)	@RequestBody Vehicle vehicle) {
		Double revenue = paymentService.calculateTotalPayment(vehicle);
		return new ResponseEntity<>(revenue,HttpStatus.OK);
	}
	
	/**
	 * This method is for calculating MonthlyRevenue by Date
	 * 
	 * @return Revenue
	 * @throws NotFoundException
	 * 
	 */

	@GetMapping("/calculateMonthlyRevenue/{date1}/{date2}")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "Calculate MonthlyRevenue by localdates", notes = "Provide LocalDates", response = Payment.class)
	public ResponseEntity<Double> calculateTMonthlyRevenue(
			@ApiParam(value = "localDate1 to calculate TotalPayment ", required = true)@PathVariable("date1") 
	@DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate  date1,@ApiParam(value = "localDate2 to calculate TotalPayment ", required = true) @PathVariable("date2") 
	@DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate  date2) {
		Double revenue = paymentService.calculateMonthlyRevenue(date1,date2);
		return new ResponseEntity<>(revenue,HttpStatus.OK);
	}
}