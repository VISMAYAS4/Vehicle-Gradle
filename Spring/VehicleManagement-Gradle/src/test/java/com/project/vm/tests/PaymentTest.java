package com.project.vm.tests;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.vm.entities.Booking;
import com.project.vm.entities.Customer;
import com.project.vm.entities.Driver;
import com.project.vm.entities.Payment;
import com.project.vm.entities.Vehicle;
import com.project.vm.services.PaymentService;


@SpringBootTest
class PaymentTest {
	Payment payment;
	Customer customer;
	Driver driver;
	Vehicle vehicle;
	Booking booking;
	
	@Autowired
	PaymentService paymentService;
	
	@BeforeEach
	void setUp() {
		customer = new Customer(32,"Test","one","test@gmail.com", "9876543210", "#23,Bangalore");
		driver = new Driver(34,"Raj","Sharma","#45,Bangalore","12345672","raj@gmail.com","FG2303");
		vehicle = new Vehicle(33,"KA05828", driver, "Car", "SUV", "Bangalore", "---", 4, 35.00, 1000);
		booking = new Booking(39,customer, vehicle, LocalDate.of(2021, 03, 05), LocalDate.of(2021, 03, 06), "----", 200);
		payment = new Payment("NetBanking", LocalDate.of(2021, 03, 05), booking, "Successful");
	}
	
//	@Test
	void testAddPayment() {
		Payment p= paymentService.addPayment(payment);
		System.out.println(p);
	}

//	@Test
	void testCancelPayment() {
		paymentService.cancelPayment(40);
	}

//	@Test
	void testViewPayment() {
		Payment p = paymentService.viewPayment(booking);
		System.out.println(p);
	}

//	@Test
	void testViewAllPayments() {
		List<Payment> payments = paymentService.viewPayments();
		System.out.println(payments);
	}
	
//	@Test
	void testViewPaymentsByVehicle() {
		List<Payment> payments = paymentService.viewAllPayment(vehicle);
		System.out.println(payments);
	}
	
//	@Test
	void testCalculateMonthlyRevenue() {
		double revenue = paymentService.calculateMonthlyRevenue(LocalDate.of(2021, 03, 05), LocalDate.of(2021, 04, 05));
		System.out.println(revenue);
	}

	@Test
	void testCalculateTotalPayment() {
		double totalPayment = paymentService.calculateTotalPayment(vehicle);
		System.out.println(totalPayment);
	}

}
