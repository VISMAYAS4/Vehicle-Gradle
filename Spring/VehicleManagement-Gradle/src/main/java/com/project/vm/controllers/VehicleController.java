package com.project.vm.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.vm.entities.Booking;
import com.project.vm.entities.Driver;
import com.project.vm.entities.Vehicle;
import com.project.vm.services.VehicleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/v1")
@Api(value = "Vehicle", tags = { "VehicleAPI" })
public class VehicleController {
	
	static final Logger log = LoggerFactory.getLogger(VehicleController.class);
	
	@Autowired
	VehicleService vehicleService;
	
	@PostMapping("/vehicles")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle v) {
		Vehicle vehicle =  vehicleService.addVehicle(v);
		return new ResponseEntity<>(vehicle,HttpStatus.CREATED);
	}
	
	@PutMapping("/vehicles")
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional
	public ResponseEntity<String> updateVehicle(@RequestBody Vehicle v) {
		vehicleService.updateVehicle(v);
		return new ResponseEntity<>("Successfuly updated", HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/vehicles/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteVehicle(@PathVariable("id") int id) {
		vehicleService.cancelVehicle(id);
		return new ResponseEntity<>("Successfuly deleted", HttpStatus.OK);
	}
	
	@GetMapping("/vehicles/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Vehicle> viewVehicle(@PathVariable("id") int id) {
		Vehicle vehicle = vehicleService.viewVehicle(id);
		return new ResponseEntity<>(vehicle,HttpStatus.OK);
		
	}
	
	@GetMapping("/vehicles")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Vehicle>> viewAllVehicle(@RequestBody Driver driver) {
		List<Vehicle> vehicles = vehicleService.viewAllVehicle(driver);
		return new ResponseEntity<>(vehicles,HttpStatus.OK);
		
	}
	
	@GetMapping("/viewAllVehicles")
	@PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
	@ApiOperation(value = "View allbookings", response = Booking.class)
	public ResponseEntity<List<Vehicle>> viewAllBooking(){
		List<Vehicle> vehicles = vehicleService.viewAllVehicles();	
		return new ResponseEntity<>(vehicles,HttpStatus.OK);
	}
}
