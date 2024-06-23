package mmk.omak.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mmk.omak.entity.Customer;
import mmk.omak.service.CustomerService;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
	
	private final CustomerService customerService;
	
	@GetMapping("/{id}")
	public Customer getById(@PathVariable int id) {
		return customerService.getById(id);
	}
	
	@GetMapping
	public List<Customer> getAll() {
		return customerService.getAll();
	}
	
	
	@PostMapping
	public Customer create(@RequestBody Customer customer) {
		return customerService.create(customer);
	}
	
	
	@PutMapping
	public Customer update(@RequestBody Customer customer) {
		return customerService.update(customer);
	}
	
}
