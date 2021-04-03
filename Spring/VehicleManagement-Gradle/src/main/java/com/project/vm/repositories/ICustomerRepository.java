package com.project.vm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.vm.entities.Booking;
import com.project.vm.entities.Customer;



@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer>{

	public Customer findCustomerByFirstName(String name);
	
	public Customer findCustomerByFirstNameAndLastName(String fname, String lname);
	
	@Query("select b.customer from  Booking b inner join b.vehicle v where v.type =  ?1")
    public List<Customer> findByVehicleType(String vtype);
	
	@Query("select b.customer from  Booking b inner join b.vehicle v where v.location =  ?1")
    public List<Customer> findByVehicleLocation(String location);
	
	public Customer findByEmailId(String emailId);

	public Customer findByMobileNumber(String mobileNumber);

	@Query("select b.customer from  Booking b inner join b.customer c where c.emailId =  ?1")
	public List<Customer> findByCustomerEmail(String emailId);


}
