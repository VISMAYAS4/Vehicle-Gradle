package com.project.vm.serviceInterfaces;

import java.time.LocalDate;
import java.util.List;

import com.project.vm.entities.Booking;
import com.project.vm.entities.Payment;
import com.project.vm.entities.Vehicle;



public interface IPaymentService {
	public Payment addPayment(Payment payment);
	public Payment cancelPayment(int id);
	public Payment viewPayment(Booking booking);
	public List<Payment> viewAllPayment(Vehicle vehicle);
	public double calculateMonthlyRevenue(LocalDate d1, LocalDate d2);
	public double calculateTotalPayment(Vehicle vehicle);
}
