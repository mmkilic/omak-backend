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
import mmk.omak.entity.Product;
import mmk.omak.service.ProductService;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	
	@GetMapping("/{id}")
	public Product getById(@PathVariable long id) {
		return productService.getById(id);
	}
	
	@GetMapping
	public List<Product> getAll() {
		return productService.getAll();
	}
	
	
	@PostMapping
	public Product create(@RequestBody Product product) {
		return productService.create(product);
	}
	
	
	@PutMapping
	public Product update(@RequestBody Product product) {
		return productService.update(product);
	}
	
}
