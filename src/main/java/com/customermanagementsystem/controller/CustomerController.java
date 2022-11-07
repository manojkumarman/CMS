package com.customermanagementsystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customermanagementsystem.Exception.CustomerException;
import com.customermanagementsystem.model.CustomerEntity;
import com.customermanagementsystem.repository.CustomerRepository;



@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class CustomerController {


	@Autowired
	private CustomerRepository customerRepository;
	
	//get all customers
	@GetMapping("/getcustomer")
	public List<CustomerEntity> getAllEmployees(){
		return (List<CustomerEntity>)customerRepository.findAll();
	}
	
	//create employee rest api
	@PostMapping("/getcustomer")
	public CustomerEntity createCustomer(@RequestBody CustomerEntity customerEntity) {
		return customerRepository.save(customerEntity);
	}
	
	//update customer rest api
	@PutMapping("/getcustomer/{id}")
	public ResponseEntity<CustomerEntity> updateCustomer(@PathVariable Long id, @RequestBody CustomerEntity customerDetails){
		
		CustomerEntity customerEntity = customerRepository.findById(id)
				.orElseThrow(() -> new CustomerException("Employee not exist with id:"+id));
		
		customerEntity.setFirstName(customerDetails.getFirstName());
		customerEntity.setLastName(customerDetails.getLastName());
		customerEntity.setAddress(customerDetails.getAddress());
		customerEntity.setCity(customerDetails.getCity());
		customerEntity.setState(customerDetails.getState());
		
		CustomerEntity updateCustomer = customerRepository.save(customerEntity);
		return ResponseEntity.ok(updateCustomer);
		
	}
	
	//delete customer rest api
	@DeleteMapping("/getcustomer/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteCustomer(@PathVariable Long id){
		
		CustomerEntity customerEntity = customerRepository.findById(id)
				.orElseThrow(() -> new CustomerException("Employee not exist with id:"+id));
		
		customerRepository.delete(customerEntity);
		
		Map<String,Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
