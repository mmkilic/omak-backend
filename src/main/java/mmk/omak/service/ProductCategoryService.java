package mmk.omak.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mmk.omak.entity.ProductCategory;
import mmk.omak.exception.BadRequestException;
import mmk.omak.repostory.ProductCategoryRepository;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {
	
	private final ProductCategoryRepository productCategoryRepo;
	
	public ProductCategory getById(int id) {
		return productCategoryRepo.findById(id).orElseThrow(() -> new BadRequestException("SalesOrder with id " +id+ " not found."));
	}
	
	public List<ProductCategory> getAll() {
		return productCategoryRepo.findAll();
	}
	
	@Transactional
	public ProductCategory create(ProductCategory productCategory) {
		if(productCategory.getId() > 0)
			throw new BadRequestException("This service is only used for new records");
		
		return productCategoryRepo.save(productCategory);
	}
	
	@Transactional
	public ProductCategory update(ProductCategory reqProductCategory) {
		var productCategory = getById(reqProductCategory.getId());
		
		
		return productCategoryRepo.save(productCategory);
	}
	
}
