package mmk.omak.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mmk.omak.entity.ProductBrand;
import mmk.omak.exception.BadRequestException;
import mmk.omak.repostory.ProductBrandRepository;

@Service
@RequiredArgsConstructor
public class ProductBrandService {
	
	private final ProductBrandRepository productBrandRepo;
	
	public ProductBrand getById(int id) {
		return productBrandRepo.findById(id).orElseThrow(() -> new BadRequestException("SalesOrder with id " +id+ " not found."));
	}
	
	public List<ProductBrand> getAll() {
		return productBrandRepo.findAll();
	}
	
	@Transactional
	public ProductBrand create(ProductBrand productBrand) {
		if(productBrand.getId() > 0)
			throw new BadRequestException("This service is only used for new records");
		
		return productBrandRepo.save(productBrand);
	}
	
	@Transactional
	public ProductBrand update(ProductBrand reqProductBrand) {
		var productBrand = getById(reqProductBrand.getId());
		
		
		return productBrandRepo.save(productBrand);
	}
	
}
