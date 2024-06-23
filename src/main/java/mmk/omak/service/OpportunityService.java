package mmk.omak.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mmk.omak.entity.Opportunity;
import mmk.omak.exception.BadRequestException;
import mmk.omak.repostory.OpportunityRepository;
import mmk.omak.repostory.UserRepository;

@Service
@RequiredArgsConstructor
public class OpportunityService {
	
	private final OpportunityRepository opportunityRepo;
	private final UserRepository userRepo;
	
	public Opportunity getById(long id) {
		return opportunityRepo.findById(id).orElseThrow(() -> new BadRequestException("SalesOrder with id " +id+ " not found."));
	}
	
	public List<Opportunity> getAll() {
		return opportunityRepo.findAll();
	}
	
	@Transactional
	public Opportunity create(Opportunity opportunity, String email) {
		if(opportunity.getId() > 0)
			throw new BadRequestException("This service is only used for new records");
		
		if(opportunity.getCustomer() == null)
			throw new BadRequestException("Teklif için müşteri seçimi zorunlu alandır.");
		
		var salesMan = userRepo.getByEmail(email).orElseThrow(() -> new BadRequestException("Satış Personali tanımlı değil."));
		opportunity.setSalesman(salesMan);
		opportunity.setDateCreated(LocalDateTime.now());
		
		return opportunityRepo.save(opportunity);
	}
	
	@Transactional
	public Opportunity update(Opportunity reqOpp) {
		var opportunity = getById(reqOpp.getId());
		opportunity.setCustomer(reqOpp.getCustomer() == null ? opportunity.getCustomer() : reqOpp.getCustomer());
		opportunity.setSalesman(reqOpp.getSalesman() == null ? opportunity.getSalesman() : reqOpp.getSalesman());
		
		return opportunityRepo.save(opportunity);
	}
	
}
