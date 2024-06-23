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
import mmk.omak.entity.Opportunity;
import mmk.omak.service.OpportunityService;

@RestController
@RequestMapping("/opportunities")
@RequiredArgsConstructor
public class OpportunityController {
	
	private final OpportunityService opportunityService;
	private final JwtService jwtService;
	
	@GetMapping("/{id}")
	public Opportunity getById(@PathVariable long id) {
		return opportunityService.getById(id);
	}
	
	@GetMapping
	public List<Opportunity> getAll() {
		return opportunityService.getAll();
	}
	
	
	@PostMapping
	public Opportunity create(@RequestBody Opportunity opportunity, HttpServletRequest request) {
		String userEmail = jwtService.extractUsername(request.getHeader(HttpHeaders.AUTHORIZATION).substring(7));
		return opportunityService.create(opportunity, userEmail);
	}
	
	
	@PutMapping
	public Opportunity update(@RequestBody Opportunity opportunity) {
		return opportunityService.update(opportunity);
	}
	
}
