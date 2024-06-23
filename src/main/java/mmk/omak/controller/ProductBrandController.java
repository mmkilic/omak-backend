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
import mmk.omak.entity.ProductBrand;
import mmk.omak.service.ProductBrandService;

@RestController
@RequestMapping("/product-brands")
@RequiredArgsConstructor
public class ProductBrandController {
	
	private final ProductBrandService productBrandService;
	
	@GetMapping("/{id}")
	public ProductBrand getById(@PathVariable int id) {
		return productBrandService.getById(id);
	}
	
	@GetMapping
	public List<ProductBrand> getAll() {
		return productBrandService.getAll();
	}
	
	
	@PostMapping
	public ProductBrand create(@RequestBody ProductBrand productBrand) {
		return productBrandService.create(productBrand);
	}
	
	
	@PutMapping
	public ProductBrand update(@RequestBody ProductBrand productBrand) {
		return productBrandService.update(productBrand);
	}
	
}
