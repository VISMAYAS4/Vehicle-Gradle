package com.project.vm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.vm.entities.Driver;



public interface IDriverRepository extends JpaRepository<Driver, Integer>{

	Driver findByFirstNameAndLastName(String firstName, String lastName);

}
