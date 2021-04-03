package com.project.vm.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.vm.entities.Booking;
import com.project.vm.entities.Payment;
import com.project.vm.entities.Vehicle;
import com.project.vm.exceptions.NotFoundException;
import com.project.vm.repositories.IBookingRepository;
import com.project.vm.repositories.IPaymentRepository;
import com.project.vm.serviceInterfaces.IPaymentService;



@Service
public class PaymentService implements IPaymentService{

	@Autowired
	IPaymentRepository paymentRepository;

	@Autowired
	IBookingRepository bookingRepository;

	@Override
	@Transactional
	public Payment addPayment(Payment p) {
		try {
			Optional<Booking> booking = bookingRepository.findById(p.getBooking().getBookingId());		
			if(booking.isPresent()) {
				p.setBooking(booking.get());
			}
		}catch(NotFoundException e) {
			e.getMessage();	
		}		
		paymentRepository.save(p);
		return p;
	}

	@Override
	public Payment cancelPayment(int id) {
		Optional<Payment> payment = paymentRepository.findById(id);
		if(payment.isEmpty()){
			throw new NotFoundException("Payment with id " +id +" not found");
 		}
		Payment p = payment.get(); 
		paymentRepository.delete(p);	
		return p;
	}

	@Override
	public Payment viewPayment(Booking booking) {
		return paymentRepository.findPaymentsByBooking(booking);
		
	}

	@Override
	public List<Payment> viewAllPayment(Vehicle vehicle) {
		List<Payment> payments = paymentRepository.findPaymentByVehicle(vehicle);
		if(payments.isEmpty()) {
			throw new NotFoundException("No payments found");
		}
		return payments;
	}

	@Override
	public double calculateMonthlyRevenue(LocalDate d1, LocalDate d2) {
		return paymentRepository.findTotalPaymentByDates(d1, d2);

	}

	@Override
	public double calculateTotalPayment(Vehicle vehicle) {
		return paymentRepository.findTotalPaymentByVehicle(vehicle);
	}
	
	public List<Payment> viewPayments(){	
		List<Payment> payments = paymentRepository.findAll();
		if(payments.isEmpty()) {
			throw new NotFoundException("No payments found");
		}
		return payments;
	}

}
