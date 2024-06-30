package mmk.omak.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mmk.omak.entity.Line;
import mmk.omak.entity.SalesOffer;
import mmk.omak.entity.SalesOrder;
import mmk.omak.enums.OfferStatus;
import mmk.omak.exception.BadRequestException;
import mmk.omak.repostory.LineRepository;
import mmk.omak.repostory.SalesOfferRepository;
import mmk.omak.repostory.UserRepository;

@Service
@RequiredArgsConstructor
public class SalesOfferService {
	
	private final SalesOfferRepository offerRepo;
	private final UserRepository userRepo;
	private final LineRepository lineRepo;
	
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
		
		if(offer.getOfferlines().isEmpty())
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
		
		return calculate(offer);
	}
	
	@Transactional
	public SalesOffer update(SalesOffer reqOffer) {
		var offer = getById(reqOffer.getId());
		
		offer.setStatus(OfferStatus.REVIZED);
		offer.update(reqOffer);
		offer = offerRepo.save(offer);
		
		return calculate(offer);
	}
	
	public SalesOrder convertToOrder(SalesOffer reqOffer) {
		var offer = getById(reqOffer.getId());
		offer.setStatus(OfferStatus.ORDERED);
		offer = offerRepo.save(offer);
		return null;
	}
	
	public SalesOffer canceled(SalesOffer reqOffer) {
		var offer = getById(reqOffer.getId());
		offer.setStatus(OfferStatus.CANCELED);
		return offerRepo.save(offer);
	}
	
	private SalesOffer calculate(SalesOffer offer) {
		double salesAmount = 0;
		for (Line line : offer.getOfferlines()) {
			salesAmount += line.getTotalPrice();
			line.setCurrency(offer.getCurrency());
			line.setSalesOffer(offer);
			lineRepo.save(line);
		}
		offer.setAmount(salesAmount);
		return offer;
	}
}
