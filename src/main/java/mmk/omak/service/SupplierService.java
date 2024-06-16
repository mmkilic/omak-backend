package mmk.omak.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mmk.omak.entity.Supplier;
import mmk.omak.exception.BadRequestException;
import mmk.omak.repostory.SupplierRepository;

@Service
@RequiredArgsConstructor
public class SupplierService {
	
	private final SupplierRepository supplierRepo;
	
	
	public Supplier getById(int id) {
		return supplierRepo.findById(id).orElseThrow(() -> new BadRequestException("Supplier with id " +id+ " not found."));
	}
	
	public List<Supplier> getAll() {
		return supplierRepo.findAll();
	}
	
	
	public Supplier create(Supplier supplier) {
		if(supplier.getId() > 0)
			throw new BadRequestException("This service is only used for new records");
		supplier.setDateCreated(LocalDateTime.now());
		return supplierRepo.save(supplier);
	}
	
	public Supplier update(Supplier supplierReq) {
		var supplier = getById(supplierReq.getId());
		supplier.update(supplierReq);
		return supplierRepo.save(supplier);
	}
}
