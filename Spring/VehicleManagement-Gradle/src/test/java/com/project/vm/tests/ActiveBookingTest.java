package com.project.vm.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.vm.entities.Booking;
import com.project.vm.services.ActiveBookingService;


@SpringBootTest
class ActiveBookingTest {

	@Autowired
	ActiveBookingService activeBookingService;
	
	@Test
	void testViewActiveBookings() {
		List<Booking> bookings =  activeBookingService.viewActiveBookings();
		assertEquals(6, bookings.size());
		System.out.println(bookings);
		
	}
	
//	@Test
	void testViewActiveBookingByBookingDate() {
		List<Booking> bookings =  activeBookingService.viewActiveBookingByBookingDate(LocalDate.of(2021, 03, 04));
		System.out.println(bookings);
	}
	
//	@Test
	void testViewActiveBookingBetweenDates() {
		List<Booking> bookings =  activeBookingService.viewActiveBookingBetweenDates(LocalDate.of(2021, 03, 04),LocalDate.of(2021, 03, 06));
		System.out.println(bookings);
	}

}
