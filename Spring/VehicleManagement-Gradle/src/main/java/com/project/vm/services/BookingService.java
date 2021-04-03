package com.project.vm.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.vm.entities.Booking;
import com.project.vm.entities.Customer;
import com.project.vm.entities.Payment;
import com.project.vm.entities.Vehicle;
import com.project.vm.exceptions.DeletionException;
import com.project.vm.exceptions.NotFoundException;
import com.project.vm.exceptions.ValidationException;
import com.project.vm.repositories.IBookingRepository;
import com.project.vm.repositories.ICustomerRepository;
import com.project.vm.repositories.IPaymentRepository;
import com.project.vm.repositories.IVehicleRepository;
import com.project.vm.serviceInterfaces.IBookingService;


//BookingService class provides definition to the methods declared in IBookingService interface.
@Service
public class BookingService implements IBookingService{
	@Autowired
	IBookingRepository bookingRepository;

	@Autowired
	IPaymentRepository paymentRepository;

	@Autowired
	ICustomerRepository customerRepository;

	@Autowired
	IVehicleRepository vehicleRepository;

	Optional<Booking> booking = null;

	//	addBooking method inserts booking details.
	@Override
	@Transactional
	public Booking addBooking(Booking b) throws ValidationException{
		LocalDate currentDate = LocalDate.now();
		if(b.getBookedTillDate().isBefore(currentDate) || b.getBookingDate().isBefore(currentDate)) {
			throw new ValidationException("Booking dates cannot be before current date");
		}	
		Vehicle vehicle = vehicleRepository.findVehicleByVehicleNumber(b.getVehicle().getVehicleNumber());
		Customer customer = customerRepository.findCustomerByFirstNameAndLastName(b.getCustomer().getFirstName(),b.getCustomer().getLastName());
		if(vehicle == null) {
			throw new NotFoundException("Vehicle with vehicle number " +b.getVehicle().getVehicleNumber() +" not found.");
		}
		if(customer == null ) {
			throw new NotFoundException("Customer with name  " +b.getCustomer().getFirstName() +" "
					+b.getCustomer().getLastName() +" not found.");
		}
		b.setCustomer(customer);
		b.setVehicle(vehicle);
		bookingRepository.save(b);
		b.setTotalCost(b.getDistance());
		return b;
	}

	//	cancelBooking method deletes a booking by id.
	@Override
	public Booking cancelBooking(int id) {
		Booking b1 = this.viewBooking(id);
		Payment payment = paymentRepository.findPaymentsByBooking(b1);
		if(payment != null) {
			throw new DeletionException("Booking with payment cannot be deleted");
		}
		bookingRepository.delete(b1);
		return b1;
	}

	//	updateBooking method updates bookedTillDate and bookingDescription of a booking by id.
	@Override
	@Transactional
	public Booking updateBooking(Booking b) {
		Booking booking1 = this.viewBooking(b.getBookingId());
		booking1.setBookingDate(b.getBookingDate());
		booking1.setBookedTillDate(b.getBookedTillDate());
		booking1.setBookingDescription(b.getBookingDescription());
		return booking1;
	}

	//	viewAllBooking method returns a list of bookings based on Customer.
	@Override
	public List<Booking> viewAllBooking(String name) {
		Customer c = customerRepository.findCustomerByFirstName(name);
		List<Booking> bookings = bookingRepository.findByCustomer(c);
		if(bookings.isEmpty()) {
			throw new NotFoundException("No bookings found for customer : " +name);
		}
		return bookings;

	}

	//	viewAllBookingByVehicle method returns a list of bookings based on Vehicle.
	@Override
	public List<Booking> viewAllBookingByVehicle(String vehicleNumber) {
		Vehicle vehicle = vehicleRepository.findVehicleByVehicleNumber(vehicleNumber);
		List<Booking> bookings = bookingRepository.findByVehicle(vehicle);
		if(bookings.isEmpty()) {
			throw new NotFoundException("No bookings found for vehicle :" +vehicleNumber);
		}
		return bookings;
	}

	//	viewAllBookingByDate method returns a list of bookings based on bookingDate.
	@Override
	public List<Booking> viewAllBookingByDate(LocalDate bDate) {
		List<Booking> bookings = bookingRepository.findByBookingDate(bDate);
		if(bookings.isEmpty()) {
			throw new NotFoundException("No bookings found for date : " +bDate);
		}
		return bookings;
	}

	//	viewBooking method finds booking by Id.
	@Override
	public Booking viewBooking(int id) {
		booking = bookingRepository.findById(id);
		if(booking.isEmpty()) {
			throw new NotFoundException("Booking with id " +id +" not found.");
		}
		return booking.get();
	}

	//	viewAllBookings returns a list of all bookings
	public List<Booking> viewAllBookings() {
		List<Booking> bookings = bookingRepository.findAll();
		if(bookings.isEmpty()) {
			throw new NotFoundException("No bookings found");
		}
		return bookings;
	}
	
	//	viewBookingsByCustomerEmail returns a list of all bookings by customer email
	public List<Booking> viewBookingsByCustomerEmail(String email){
		List<Booking> bookings = bookingRepository.findByCustomerEmail(email);
		if(bookings.isEmpty()) {
			throw new NotFoundException("No bookings found");
		}
		return bookings;
	}
	
}
