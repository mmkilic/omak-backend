package mmk.omak.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import mmk.omak.enums.OfferStatus;

@Entity
@Table
@Data
public class Offer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private double taxRate;
	private double taxAmount;
	private double amount;
	private double totalAmount;
	@Enumerated(EnumType.STRING)
	private OfferStatus status;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateCreated;
	
	
	@ManyToOne
	private Opportunity opportunity;
	@ManyToOne
	private Customer customer;
	@ManyToOne
	private User salesman;
	@ManyToOne
	private Currency currency;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "offer")
	private List<Line> offerlines = new ArrayList<Line>();
}
