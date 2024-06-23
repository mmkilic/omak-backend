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
import mmk.omak.entity.Supplier;
import mmk.omak.service.SupplierService;

@RestController
@RequestMapping("/suppliers")
@RequiredArgsConstructor
public class SupplierController {
	
	private final SupplierService supplierService;
	
	@GetMapping("/{id}")
	public Supplier getById(@PathVariable int id) {
		return supplierService.getById(id);
	}
	
	@GetMapping
	public List<Supplier> getAll() {
		return supplierService.getAll();
	}
	
	
	@PostMapping
	public Supplier create(@RequestBody Supplier supplier) {
		return supplierService.create(supplier);
	}
	
	
	@PutMapping
	public Supplier update(@RequestBody Supplier supplier) {
		return supplierService.update(supplier);
	}
	
}
