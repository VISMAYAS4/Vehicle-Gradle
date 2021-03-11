package com.project.vm.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.vm.entities.Booking;
import com.project.vm.entities.Customer;
import com.project.vm.entities.Driver;
import com.project.vm.entities.Vehicle;
import com.project.vm.services.BookingService;

@SpringBootTest
class BookingTest {
	
	Customer customer;
	Driver driver;
	Vehicle vehicle;
	Booking booking;
	
	@Autowired
	BookingService bookingService;
	
	@BeforeEach
	void setUp() {
		customer = new Customer("Test","one","test@gmail.com", "9876543210", "#11,Bangalore");
		driver = new Driver("Sid","","#22,Bangalore","12345672","raj@gmail.com","FG2303");
		vehicle = new Vehicle("KA05828", driver, "SUV", "4-wheeler", "Bangalore", "---", 6, 35.00, 1000);
		booking = new Booking(customer, vehicle, LocalDate.of(2021, 03, 9), LocalDate.of(2021, 03, 10), "Booking for one day", 200);
	}
	
//	@Test
	void testAddBooking() {
		Booking b= bookingService.addBooking(booking);
		System.out.println(b);
	}

//	@Test
	void testViewAllBookings() {
		List<Booking> bookings = bookingService.viewAllBookings();
		System.out.println(bookings);
		//assertEquals(3, bookings.size());
	}

//	@Test
	void testDeleteBooking() {
		bookingService.cancelBooking(39);
	}

//	@Test
	void testViewBooking() {
		Booking b = bookingService.viewBooking(3);
		System.out.println(b);
	}

//	@Test
	void testUpdateBooking() {
		booking = new Booking(9, customer, vehicle, LocalDate.of(2021, 03, 05), LocalDate.of(2021, 03, 06), "xxxx", 200);
		Booking b = bookingService.updateBooking(booking);
		assertEquals("xxxx", b.getBookingDescription());
		System.out.println(b);
	}

//	@Test
	void testViewBookingByCustomer() {
		List<Booking> bookings = bookingService.viewAllBooking(customer);
		System.out.println(bookings);
	}

//	@Test
	void testViewBookingByVehicle() {
		List<Booking> bookings = bookingService.viewAllBookingByVehicle(vehicle);
		System.out.println(bookings);
	}

//	@Test
	void testViewBookingByBookingDate() {
		List<Booking> bookings = bookingService.viewAllBookingByDate(LocalDate.of(2021, 03, 04));
		System.out.println(bookings);
	}

}
