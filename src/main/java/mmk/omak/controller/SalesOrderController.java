package mmk.omak.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mmk.omak.auth.JwtService;
import mmk.omak.entity.SalesOrder;
import mmk.omak.service.SalesOrderService;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class SalesOrderController {
	
	private final SalesOrderService salesOrderService;
	private final JwtService jwtService;
	
	@GetMapping("/{id}")
	public SalesOrder getById(@PathVariable long id) {
		return salesOrderService.getById(id);
	}
	
	@GetMapping
	public List<SalesOrder> getAll() {
		return salesOrderService.getAll();
	}
	
	
	@PostMapping
	public SalesOrder create(@RequestBody SalesOrder order, HttpServletRequest request) {
		String userEmail = jwtService.extractUsername(request.getHeader(HttpHeaders.AUTHORIZATION).substring(7));
		return salesOrderService.create(order, userEmail);
	}
	
	@PostMapping("/excel")
	public void excelExportAllUsers(HttpServletResponse response, @RequestParam long oderId) {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=records_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		salesOrderService.excelOffer(response, oderId);
	}
	
	
	@PutMapping
	public SalesOrder update(@RequestBody SalesOrder order) {
		return salesOrderService.update(order);
	}
	
}
