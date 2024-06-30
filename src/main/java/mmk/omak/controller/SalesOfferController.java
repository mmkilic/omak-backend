package mmk.omak.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import mmk.omak.auth.JwtService;
import mmk.omak.entity.SalesOffer;
import mmk.omak.service.SalesOfferService;

@RestController
@RequestMapping("/offers")
@RequiredArgsConstructor
public class SalesOfferController {
	
	private final SalesOfferService offerService;
	private final JwtService jwtService;
	
	@GetMapping("/{id}")
	public SalesOffer getById(@PathVariable int id) {
		return offerService.getById(id);
	}
	
	@GetMapping
	public List<SalesOffer> getAll() {
		return offerService.getAll();
	}
	
	@PostMapping
	public SalesOffer create(@RequestBody SalesOffer offer, HttpServletRequest request) {
		String userEmail = jwtService.extractUsername(request.getHeader(HttpHeaders.AUTHORIZATION).substring(7));
		return offerService.create(offer, userEmail);
	}
	
	
	@PutMapping
	public SalesOffer update(@RequestBody SalesOffer offer) {
		return offerService.update(offer);
	}
	
}
