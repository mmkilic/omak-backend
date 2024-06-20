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
import mmk.omak.entity.Offer;
import mmk.omak.service.OfferService;

@RestController
@RequestMapping("/offers")
@RequiredArgsConstructor
public class OfferController {
	
	private final OfferService offerService;
	private final JwtService jwtService;
	
	@GetMapping("/{id}")
	public Offer getById(@PathVariable long id) {
		return offerService.getById(id);
	}
	
	@GetMapping
	public List<Offer> getAll() {
		return offerService.getAll();
	}
	
	@PostMapping
	public Offer create(@RequestBody Offer offer, HttpServletRequest request) {
		String userEmail = jwtService.extractUsername(request.getHeader(HttpHeaders.AUTHORIZATION).substring(7));
		return offerService.create(offer, userEmail);
	}
	
	
	@PutMapping
	public Offer update(@RequestBody Offer offer) {
		return offerService.update(offer);
	}
	
}
