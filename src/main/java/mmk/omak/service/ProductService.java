package mmk.omak.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mmk.omak.entity.Product;
import mmk.omak.exception.BadRequestException;
import mmk.omak.repostory.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepository productRepo;
	
	public Product getById(long id) {
		return productRepo.findById(id).orElseThrow(() -> new BadRequestException("SalesOrder with id " +id+ " not found."));
	}
	
	public List<Product> getAll() {
		return productRepo.findAll();
	}
	
	@Transactional
	public Product create(Product product) {
		if(product.getId() > 0)
			throw new BadRequestException("This service is only used for new records");
		
		product.setDateCreated(LocalDateTime.now());
		return productRepo.save(product);
	}
	
	@Transactional
	public Product update(Product reqProduct) {
		var product = getById(reqProduct.getId());
		
		
		return productRepo.save(product);
	}
	
}
