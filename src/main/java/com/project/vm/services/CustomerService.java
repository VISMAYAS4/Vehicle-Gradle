package com.project.vm.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.vm.entities.Booking;
import com.project.vm.entities.Customer;
import com.project.vm.entities.User;
import com.project.vm.exceptions.AlreadyExistsException;
import com.project.vm.exceptions.DeletionException;
import com.project.vm.exceptions.NotFoundException;
import com.project.vm.exceptions.ValidationException;
import com.project.vm.repositories.IBookingRepository;
import com.project.vm.repositories.ICustomerRepository;
import com.project.vm.repositories.IUserRepository;
import com.project.vm.serviceInterfaces.ICustomerService;



@Service
public class CustomerService implements ICustomerService{
	@Autowired
	ICustomerRepository customerRepository;
	
	@Autowired
	IBookingRepository bookingRepository;
	
	@Autowired 
	IUserRepository userRepository;
	
	
	// addCustomer methods adds Customer details
	@Override
	@Transactional
	public Customer addCustomer(Customer c) throws ValidationException{
		
		final String emailPattern = "^(.+)@(.+)$";
		final String mobilePattern = "[0-9]{10}";

		String email = c.getEmailId();
		if (email.matches(emailPattern)) {
			
			Customer existingemail = customerRepository.findByEmailId(c.getEmailId());
			if(existingemail != null) {
				throw new AlreadyExistsException("EmailId Already Exists!");
			}
		}
		
		else {
			throw new ValidationException("Invalid Email");
		}
		
		String mobileNo = c.getMobileNumber();
		if (mobileNo.matches(mobilePattern)) {
			
			Customer existingmobileno = customerRepository.findByMobileNumber(c.getMobileNumber());
			if(existingmobileno != null) {
				throw new AlreadyExistsException("Mobile Number Already Exists!");
			}
		}
		
		else {
			throw new ValidationException("Invalid Mobile Number");
		}
		
		Customer customer = customerRepository.findCustomerByFirstNameAndLastName(c.getFirstName(),c.getLastName());		
			if(customer == null){
				customerRepository.save(c);
			}else
				throw new AlreadyExistsException("Customer "+ c.getFirstName()  +" " +c.getLastName() + " already exists.");
			return customer;
	}
	
	// removeCustomer method deletes a customer by cutsomer_id
	@Override
	public Customer removeCustomer(int id){
		Optional<Customer> customer = customerRepository.findById(id);
		if(customer.isEmpty()){
			throw new NotFoundException("Customer with id " +id +" not found");
 		}
		Customer c = customer.get(); 
		List<Booking> booking = bookingRepository.findByCustomer(c);
		if(!(booking.isEmpty())) {
			throw new  DeletionException("Customer with boooking cannot be deleted");
		}
		customerRepository.delete(c);	
		return c;
	}
	
	// updateCustomer methods updates customer EmailId by customer_id
	@Override
	@Transactional
	public Customer updateCustomer(Customer customer) {
		if(customer != null) {
			Customer c = viewCustomer(customer.getCustomerId());
			c.setAddress(customer.getAddress());
		return c;	
		}
		else
			throw new NotFoundException("Customer Does Not Exist");
	}
	
	// viewCustomer methods returns customer details by customer_id
	@Override
	public Customer viewCustomer(int id)
	{
		Optional<Customer> customer = customerRepository.findById(id);
		if(customer.isEmpty()){
			throw new NotFoundException("Customer with id " +id +" not found");
 		}
		return customer.get();
	}
	
	// viewCustomers returns list of all the customers
	public List<Customer> viewCustomers()
	{	
		List<Customer> customers = customerRepository.findAll();
		if(customers.isEmpty()) {
			throw new NotFoundException("No customers found");
		}
		return customers;
	}
	
	//viewAllCustomer returns list of customers by vehicle type
	@Override
	public List<Customer> viewAllCustomer(String vtype) {
		List<Customer> customers = customerRepository.findByVehicleType(vtype);
		if(customers.isEmpty()){
			throw new NotFoundException("Customer not found");
 		}
		return customers;		
	}
	
	// viewAllCustomersByLocation returns list of customers by vehicle location
	@Override
	public List<Customer> viewAllCustomersByLocation(String location) {
		List<Customer> customers = customerRepository.findByVehicleLocation(location);
		if(customers.isEmpty()){
			throw new NotFoundException("Customer not found");
 		}
		return customers;		
	}
	
	
	
	public Customer addCustomer(String password,Customer customer) {
		Customer cust = customerRepository.findCustomerByFirstNameAndLastName(customer.getFirstName(),customer.getLastName());		
		if(cust == null){
			User user = new User();
			user.setRole("customer");
			user.setUserName(customer.getFirstName());
			user.setPassword(password);
			customerRepository.save(customer);
			userRepository.save(user);
		}
		else
			throw new AlreadyExistsException("Customer "+ customer.getFirstName()  +" " +customer.getLastName() + " already exists.");
		return customer;
	}
	
}