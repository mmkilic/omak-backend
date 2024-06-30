package mmk.omak.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mmk.omak.component.ExcelGenerator;
import mmk.omak.entity.Line;
import mmk.omak.entity.SalesOrder;
import mmk.omak.exception.BadRequestException;
import mmk.omak.repostory.LineRepository;
import mmk.omak.repostory.SalesOrderRepository;
import mmk.omak.repostory.UserRepository;

@Service
@RequiredArgsConstructor
public class SalesOrderService {
	
	private final SalesOrderRepository orderRepo;
	private final UserRepository userRepo;
	private final LineRepository lineRepo;
	private final ExcelGenerator excelGenerator;
	
	public SalesOrder getById(int id) {
		return orderRepo.findById(id).orElseThrow(() -> new BadRequestException("SalesOrder with id " +id+ " not found."));
	}
	
	public List<SalesOrder> getAll() {
		return orderRepo.findAll();
	}
	
	@Transactional
	public SalesOrder create(SalesOrder order, String email) {
		if(order.getId() > 0)
			throw new BadRequestException("This service is only used for new records");
		
		if(order.getOrderlines().isEmpty())
			throw new BadRequestException("Minimum bir satır olması gerekekiyor.");
		
		if(order.getCustomer() == null)
			throw new BadRequestException("Teklif için müşteri seçimi zorunlu alandır.");
		
		if(order.getCurrency() == null)
			throw new BadRequestException("Teklif için para birimi seçimi zorunlu alandır.");
		
		var salesMan = userRepo.getByEmail(email).orElseThrow(() -> new BadRequestException("Satış Personali tanımlı değil."));
		order.setSalesman(salesMan);
		
		order.setDateCreated(LocalDateTime.now());
		order = orderRepo.save(order);
		
		return calculate(order);
	}
	
	public void excelOffer(HttpServletResponse response, int oderId) {
		excelGenerator.excelOffer(response, getById(oderId));
	}
	
	@Transactional
	public SalesOrder update(SalesOrder reqOrder) {
		var order = getById(reqOrder.getId());
		order.update(reqOrder);
		order = orderRepo.save(order);
		
		return calculate(order);
	}
	
	private SalesOrder calculate(SalesOrder order) {
		double salesAmount = 0;
		for (Line line : order.getOrderlines()) {
			salesAmount += line.getTotalPrice();
			line.setCurrency(order.getCurrency());
			line.setSalesOrder(order);
			lineRepo.save(line);
		}
		order.setAmount(salesAmount);
		return order;
	}
}
