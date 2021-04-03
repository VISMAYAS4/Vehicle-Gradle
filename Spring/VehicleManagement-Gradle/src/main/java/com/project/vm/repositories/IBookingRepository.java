package com.project.vm.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.vm.entities.Booking;
import com.project.vm.entities.Customer;
import com.project.vm.entities.Vehicle;



@Repository
public interface IBookingRepository extends JpaRepository<Booking, Integer>{

	public List<Booking> findByCustomer(Customer customer);

	public List<Booking> findByVehicle(Vehicle vehicle);

	public List<Booking> findByBookingDate(LocalDate bDate);
	
	@Query("select b from  Booking b inner join b.customer c where c.emailId =  ?1")
    public List<Booking> findByCustomerEmail(String emailId);
}
