package mmk.omak.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Opportunity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateCreated;
	
	@ManyToOne
	private Customer customer;
	@ManyToOne
	private User salesman;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "opportunity")
	private List<Offer> offers = new ArrayList<Offer>();
	@JsonIgnore
	@OneToMany(mappedBy = "opportunity")
	private List<SalesOrder> salesOrders = new ArrayList<SalesOrder>();
	
}
