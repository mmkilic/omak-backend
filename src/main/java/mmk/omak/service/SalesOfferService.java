package mmk.omak.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mmk.omak.component.ExcelGenerator;
import mmk.omak.entity.OfferLine;
import mmk.omak.entity.SalesOffer;
import mmk.omak.entity.SalesOrder;
import mmk.omak.enums.OfferStatus;
import mmk.omak.exception.BadRequestException;
import mmk.omak.repostory.OfferLineRepository;
import mmk.omak.repostory.SalesOfferRepository;
import mmk.omak.repostory.UserRepository;

@Service
@RequiredArgsConstructor
public class SalesOfferService {
	
	private final SalesOfferRepository offerRepo;
	private final UserRepository userRepo;
	private final OfferLineRepository lineRepo;
	private final ExcelGenerator excelGenerator;
	
	public SalesOffer getById(int id) {
		return offerRepo.findById(id).orElseThrow(() -> new BadRequestException("Currency with id " +id+ " not found."));
	}
	
	public List<SalesOffer> getAll() {
		return offerRepo.findAll();
	}
	
	@Transactional
	public SalesOffer create(SalesOffer offer, String email) {
		if(offer.getId() > 0)
			throw new BadRequestException("This service is only used for new records");
		
		if(offer.getOfferLines().isEmpty())
			throw new BadRequestException("Minimum bir satır olması gerekekiyor.");
		
		if(offer.getCustomer() == null)
			throw new BadRequestException("Teklif için müşteri seçimi zorunlu alandır.");
		
		if(offer.getCurrency() == null)
			throw new BadRequestException("Teklif için para birimi seçimi zorunlu alandır.");
		
		var salesMan = userRepo.getByEmail(email).orElseThrow(() -> new BadRequestException("Satış Personali tanımlı değil."));
		offer.setSalesman(salesMan);
		
		offer.setDateCreated(LocalDateTime.now());
		offer.setStatus(OfferStatus.OPEN);
		offer = offerRepo.save(offer);
		
		for (OfferLine line : offer.getOfferLines()) {
			line.setSalesOffer(offer);
			lineRepo.save(line);
		}
		
		return offer;
	}
	
	public void excelOffer(HttpServletResponse response, int oderId) {
		excelGenerator.excelOffer(response, getById(oderId));
	}
	
	@Transactional
	public SalesOffer update(SalesOffer theOffer) {
		var dbOffer = getById(theOffer.getId());
		
		for (OfferLine line : dbOffer.getOfferLines()) {
			if(!theOffer.getOfferLines().stream().anyMatch(l -> l.getId() == line.getId()))
				lineRepo.delete(line);
		}
		
		for (OfferLine line : theOffer.getOfferLines()) {
			line.setSalesOffer(theOffer);
			lineRepo.save(line);
		}
		
		theOffer.setStatus(OfferStatus.REVIZED);
		theOffer = offerRepo.save(theOffer);
		return dbOffer;
	}
	
	public SalesOrder offerToOrder(int offerId) {
		var offer = getById(offerId);
		offer.setStatus(OfferStatus.ORDERED);
		var order = new SalesOrder();
		order.setContact(offer.getContact());
		order.setCurrency(offer.getCurrency());
		order.setCustomer(offer.getCustomer());
		order.setSalesman(offer.getSalesman());
		order.setSalesOfferId(offerId);
		order.setTaxRate(offer.getTaxRate());
		//order.getOrderLines().add(null);
		offer = offerRepo.save(offer);
		return order;
	}
	
	public SalesOffer cancel(int offerId) {
		var offer = getById(offerId);
		offer.setStatus(OfferStatus.CANCELED);
		return offerRepo.save(offer);
	}
}
