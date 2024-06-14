package mmk.omak.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mmk.omak.entity.Currency;
import mmk.omak.exception.BadRequestException;
import mmk.omak.repostory.CurrencyRepository;

@Service
@RequiredArgsConstructor
public class CurrencyService {
	
	private final CurrencyRepository currencyRepo;
	
	
	public Currency getById(int id) {
		return currencyRepo.findById(id).orElseThrow(() -> new BadRequestException("Currency with id " +id+ " not found."));
	}
	public Currency getByCode(String code) {
		return currencyRepo.getByCode(code).orElseThrow(() -> new BadRequestException("Currency with code " +code+ " not found."));
	}
	public List<Currency> getAll() {
		return currencyRepo.findAll();
	}
	
	
	public Currency create(Currency currency) {
		if(currency.getId() > 0)
			throw new BadRequestException("This service is only used for new records");
		return currencyRepo.save(currency);
	}
	
	public Currency update(Currency reqCurrency) {
		var currency = getById(reqCurrency.getId());
		currency.update(reqCurrency);
		return currencyRepo.save(currency);
	}
}
