package com.project.vm.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.vm.entities.Booking;
import com.project.vm.entities.Customer;
import com.project.vm.entities.Driver;
import com.project.vm.entities.Vehicle;
import com.project.vm.exceptions.DeletionException;
import com.project.vm.exceptions.NotFoundException;
import com.project.vm.exceptions.ValidationException;
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
		customer = new Customer();
		vehicle = new Vehicle();
		booking = new Booking();
	}
	

//	@Test
	void testAddBooking() {
		customer.setFirstName("Shreya");
		customer.setLastName("Sinha");
		vehicle.setVehicleNumber("KA037777");
		booking = new Booking(customer, vehicle,LocalDate.now(), LocalDate.of(2021, 03, 18), "Booking for 3 days", 200);
		Booking b= bookingService.addBooking(booking);
		assertNotNull(b);
	}
	
//	@Test
	void testViewBooking() {
		Booking b = bookingService.viewBooking(2);
		System.out.println(b);
		assertNotNull(b);
	}
	
//	@Test
	void testViewBookingByCustomer() {
		List<Booking> bookings = bookingService.viewAllBooking("Abhi");
		assertNotNull(bookings);
	}
	
	@Test
	void testViewBookingByVehicle() {
		List<Booking> bookings = bookingService.viewAllBookingByVehicle("DL057860");
		assertNotNull(bookings);
	}
	
///	@Test
	void testViewBookingByBookingDate() {
		List<Booking> bookings = bookingService.viewAllBookingByDate(LocalDate.of(2021, 03, 15));
		assertNotNull(bookings);
	}
	
//	@Test
	void testAddBookingWithInvalidDates() {
		customer.setFirstName("Test");
		customer.setLastName("Cust");
		vehicle.setVehicleNumber("AF786023");
		booking = new Booking(customer, vehicle,LocalDate.of(2021, 03, 9), LocalDate.of(2021, 03, 10), "Booking for one day", 200);
		Exception exception = assertThrows(ValidationException.class, () -> {
			bookingService.addBooking(booking);
	    });
		String expectedMessage = "before current date";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}

	

//	@Test
	void testDeleteBooking() {
		int id = 3;
		Booking b = bookingService.cancelBooking(id);
		assertNotNull(b);
	}
	
//	@Test
	void testDeleteInvalidBooking() {
		int id = 0;
		Exception exception = assertThrows(NotFoundException.class, () -> {
			bookingService.cancelBooking(id);
	    });
	    String expectedMessage = "not found";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
//	@Test
	void testDeleteBookingWithPayment() {
		int id = 2;
		Exception exception = assertThrows(DeletionException.class, () -> {
			bookingService.cancelBooking(id);
	    });
	    String expectedMessage = "cannot be deleted";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}


	
//	@Test
	void testViewBookingByInvalidId() {
		int id = 0;
		Exception exception = assertThrows(NotFoundException.class, () -> {
			bookingService.viewBooking(id);
	    });
	    String expectedMessage = "not found";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}

//	@Test
	void testUpdateBooking() {
		booking.setBookingId(2);
		booking.setBookedTillDate(LocalDate.of(2021, 03, 18));
		booking.setBookingDescription("Booking for 3 days");
		Booking b = bookingService.updateBooking(booking);
		assertEquals("Booking for 3 days", b.getBookingDescription());
		assertEquals(LocalDate.of(2021, 03, 18), b.getBookedTillDate());
	}

	
	
//	@Test
	void testViewBookingByInvalidCustomer() {
		Exception exception = assertThrows(NotFoundException.class, () -> {
			bookingService.viewAllBooking("xxxxx");
	    });
	    String expectedMessage = "No bookings found";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}



	
//	@Test
	void testViewBookingByInvalidVehicle() {
		Exception exception = assertThrows(NotFoundException.class, () -> {
			bookingService.viewAllBookingByVehicle("xxxxx");
	    });
	    String expectedMessage = "not found";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}


	
//	@Test
	void testViewBookingByInvalidDate() {
		Exception exception = assertThrows(NotFoundException.class, () -> {
			bookingService.viewAllBookingByDate(LocalDate.of(2021, 03, 30));
	    });
	    String expectedMessage = "No bookings found";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}

}
