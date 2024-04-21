package mmk.omak.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Currency {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true, nullable = false)
	private String code;
	private String name;
	private double exchangeByEuro;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "currency")
	private List<Offer> offers = new ArrayList<Offer>();
	@JsonIgnore
	@OneToMany(mappedBy = "currency")
	private List<SalesOrder> salesOrders = new ArrayList<SalesOrder>();
	@JsonIgnore
	@OneToMany(mappedBy = "currency")
	private List<Line> lines = new ArrayList<Line>();
	@JsonIgnore
	@OneToMany(mappedBy = "currency")
	private List<Product> products = new ArrayList<Product>();
}
