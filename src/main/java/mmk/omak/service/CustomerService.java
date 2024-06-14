package mmk.omak.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mmk.omak.entity.Customer;
import mmk.omak.exception.BadRequestException;
import mmk.omak.repostory.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerService {
	
	private final CustomerRepository customerRepo;
	
	
	public Customer getById(int id) {
		return customerRepo.findById(id).orElseThrow(() -> new BadRequestException("Customer with id " +id+ " not found."));
	}
	
	public List<Customer> getAll() {
		return customerRepo.findAll();
	}
	
	
	public Customer create(Customer customer) {
		if(customer.getId() > 0)
			throw new BadRequestException("This service is only used for new records");
		return customerRepo.save(customer);
	}
	
	public Customer update(Customer customerReq) {
		var customer = getById(customerReq.getId());
		customer.update(customerReq);
		return customerRepo.save(customer);
	}
}
