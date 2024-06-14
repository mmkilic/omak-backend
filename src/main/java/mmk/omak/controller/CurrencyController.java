package mmk.omak.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mmk.omak.entity.Currency;
import mmk.omak.service.CurrencyService;

@RestController
@RequestMapping("/currencies")
@RequiredArgsConstructor
public class CurrencyController {
	
	private final CurrencyService currencyService;
	
	@GetMapping("/{id}")
	public Currency getById(@PathVariable int id) {
		return currencyService.getById(id);
	}
	@GetMapping("/code")
	public Currency getByCode(@RequestParam String code) {
		return currencyService.getByCode(code);
	}
	@GetMapping
	public List<Currency> getAll() {
		return currencyService.getAll();
	}
	
	@PostMapping
	public Currency create(@RequestBody Currency currency) {
		return currencyService.create(currency);
	}
	
	
	@PutMapping
	public Currency update(@RequestBody Currency currency) {
		return currencyService.update(currency);
	}
	
}
