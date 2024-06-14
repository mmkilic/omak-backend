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
import jakarta.persistence.TableGenerator;
import lombok.Data;

@Entity
@Table
@TableGenerator(name="tab", initialValue=1_000, allocationSize=1)
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator="tab")
	private int id;
	@Column(unique = true)
	private String name;
	private String phoneNumber;
	private String email;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateCreated;
	
	private String address;
	private String district;
	private String city;
	private String country;
	private int postCode;
	
	@ManyToOne
	private User customerOwner;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Contact> contacts = new ArrayList<Contact>();
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Opportunity> opportunities = new ArrayList<Opportunity>();
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<SalesOrder> salesOrders = new ArrayList<SalesOrder>();
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Offer> offers = new ArrayList<Offer>();
	
	
	public Customer update(Customer c) {
		this.name = c.name;
		this.phoneNumber = c.phoneNumber;
		this.email = c.email;
		
		return this;
	}
}
