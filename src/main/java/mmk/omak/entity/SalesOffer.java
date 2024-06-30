package mmk.omak.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import mmk.omak.enums.OfferStatus;
import mmk.omak.utility.CustomId;

@Entity
@Table
@Data
public class SalesOffer{
	@Id
	@CustomId(initialNum = 5000, tableName = "sales_offer_custom_id")
	private int id;
	private double amount;
	private double taxRate;
	@Enumerated(EnumType.STRING)
	private OfferStatus status;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateCreated;
	
	
	@ManyToOne
	private Customer customer;
	@ManyToOne
	private Currency currency;
	@ManyToOne
	private Contact contact;
	@ManyToOne
	private User salesman;
	
	
	
	@OneToMany(mappedBy = "salesOffer")
	private List<Line> offerlines = new ArrayList<Line>();
	
	public double getTaxtAmount() {
		return amount * taxRate;
	}
	public double getTotalAmount() {
		return amount + getTaxtAmount();
	}
	
	public SalesOffer update(SalesOffer o) {
		this.taxRate = o.taxRate;
		this.currency = o.currency != null ? o.currency : this.currency;
		this.customer = o.customer != null ? o.customer : this.customer;
		return this;
	}
}
