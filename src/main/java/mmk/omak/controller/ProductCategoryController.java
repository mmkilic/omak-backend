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
import mmk.omak.entity.ProductCategory;
import mmk.omak.service.ProductCategoryService;

@RestController
@RequestMapping("/product-categories")
@RequiredArgsConstructor
public class ProductCategoryController {
	
	private final ProductCategoryService productCategoryService;
	
	@GetMapping("/{id}")
	public ProductCategory getById(@PathVariable int id) {
		return productCategoryService.getById(id);
	}
	
	@GetMapping
	public List<ProductCategory> getAll() {
		return productCategoryService.getAll();
	}
	
	
	@PostMapping
	public ProductCategory create(@RequestBody ProductCategory productCategory) {
		return productCategoryService.create(productCategory);
	}
	
	
	@PutMapping
	public ProductCategory update(@RequestBody ProductCategory productCategory) {
		return productCategoryService.update(productCategory);
	}
	
}
