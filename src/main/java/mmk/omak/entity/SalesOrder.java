package mmk.omak.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class SalesOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private double taxRate;
	private double taxAmount;
	private double amount;
	private double totalAmount;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateCreated;
	
	
	@ManyToOne
	private Opportunity opportunity;
	@ManyToOne
	private Currency currency;
	@ManyToOne
	private Customer customer;
	@ManyToOne
	private User salesman;
	
	
	@OneToMany(mappedBy = "salesOrder")
	private List<Line> orderlines = new ArrayList<Line>();
	
	
	public SalesOrder update(SalesOrder o) {
		this.taxRate = o.taxRate;
		this.currency = o.currency != null ? o.currency : this.currency;
		this.customer = o.customer != null ? o.customer : this.customer;
		return this;
	}
	
	
	public double getDeliveryPercentage() {
		if(orderlines == null || orderlines.isEmpty())
			return 0;
		return Math.round(100.0 * orderlines.stream().mapToInt(o -> o.getDeliveryStatus().getValue()).sum() / (double) orderlines.size());
	}
	
	public double getRecievingPercentage() {
		if(orderlines == null || orderlines.isEmpty())
			return 0;
		return Math.round(100.0 * orderlines.stream().mapToInt(o -> o.getReceivingStatus().getValue()).sum() / (double) orderlines.size());
	}
}
