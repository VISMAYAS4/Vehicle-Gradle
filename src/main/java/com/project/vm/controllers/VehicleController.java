package com.project.vm.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.vm.entities.Driver;
import com.project.vm.entities.Vehicle;
import com.project.vm.services.VehicleService;

@RestController
@RequestMapping(path = "/api/v1")
public class VehicleController {
	
	@Autowired
	VehicleService vehicleService;
	
	@PostMapping("/vehicles")
	public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle v) {
		Vehicle vehicle =  vehicleService.addVehicle(v);
		return new ResponseEntity<>(vehicle,HttpStatus.CREATED);
	}
	
	@PutMapping("/vehicles")
	@Transactional
	public ResponseEntity<String> updateVehicle(@RequestBody Vehicle v) {
		vehicleService.updateVehicle(v);
		return new ResponseEntity<>("Successfuly updated", HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/vehicles/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") int id) {
		vehicleService.cancelVehicle(id);
		return new ResponseEntity<>("Successfuly deleted", HttpStatus.OK);
	}
	
	@GetMapping("/vehicles/{id}")
	public ResponseEntity<Vehicle> viewVehicle(@PathVariable("id") int id) {
		Vehicle vehicle = vehicleService.viewVehicle(id);
		return new ResponseEntity<>(vehicle,HttpStatus.OK);
		
	}
	
	@GetMapping("/vehicles")
	public ResponseEntity<List<Vehicle>> viewAllVehicle(@RequestBody Driver driver) {
		List<Vehicle> vehicles = vehicleService.viewAllVehicle(driver);
		return new ResponseEntity<>(vehicles,HttpStatus.OK);
		
	}
}
