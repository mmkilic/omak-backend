package mmk.omak.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
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
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String code;
	private String name;
	@Column(unique = true)
	private String taxId;
	private String phoneNumber;
	private String mail;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateCreated;
	
	
	@ManyToOne
	private User customerOwner;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Contact> contacts = new ArrayList<Contact>();
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Address> addresses = new ArrayList<Address>();
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Opportunity> opportunities = new ArrayList<Opportunity>();
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<SalesOrder> salesOrders = new ArrayList<SalesOrder>();
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Offer> offers = new ArrayList<Offer>();
}
