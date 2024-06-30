package mmk.omak.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import mmk.omak.utility.CustomId;

@Entity
@Table
@Data
public class SalesOrder{
	@Id
	@CustomId(initialNum = 1000, tableName = "sales_order_custom_id")
	private int id;
	private double taxRate;
	private double taxAmount;
	private double amount;
	private double totalAmount;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateCreated;
	private int salesOfferId;
	
	@ManyToOne
	private Currency currency;
	@ManyToOne
	private Customer customer;
	@ManyToOne
	private Contact contact;
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
