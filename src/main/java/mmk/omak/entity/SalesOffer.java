package mmk.omak.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

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
	private double taxRate;
	@Enumerated(EnumType.STRING)
	private OfferStatus status;
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
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
	private List<OfferLine> offerLines = new ArrayList<OfferLine>();
	
	public double getAmount() {
		BigDecimal sum = BigDecimal.ZERO;
		
		for (OfferLine l : offerLines) {
			sum = sum.add(BigDecimal.valueOf(l.getTotalPrice()));
		}
		
		return sum.doubleValue();
	}
	public double getTaxAmount() {
		return BigDecimal.valueOf(getAmount()).multiply(BigDecimal.valueOf(taxRate)).doubleValue();
	}
	public double getTotalAmount() {
		return BigDecimal.valueOf(getAmount()).add(BigDecimal.valueOf(getTaxAmount())).doubleValue();
	}
	
}
