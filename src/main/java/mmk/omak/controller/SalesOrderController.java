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
import mmk.omak.entity.SalesOrder;
import mmk.omak.service.SalesOrderService;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class SalesOrderController {
	
	private final SalesOrderService orderService;
	private final JwtService jwtService;
	
	@GetMapping("/{id}")
	public SalesOrder getById(@PathVariable int id) {
		return orderService.getById(id);
	}
	
	@GetMapping
	public List<SalesOrder> getAll() {
		return orderService.getAll();
	}
	
	
	@PostMapping
	public SalesOrder create(@RequestBody SalesOrder order, HttpServletRequest request) {
		String userEmail = jwtService.extractUsername(request.getHeader(HttpHeaders.AUTHORIZATION).substring(7));
		return orderService.create(order, userEmail);
	}
	
	
	@PutMapping
	public SalesOrder update(@RequestBody SalesOrder order) {
		return orderService.update(order);
	}
	
}
