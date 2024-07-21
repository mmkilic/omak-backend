package mmk.omak.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
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
import mmk.omak.enums.ContactTypes;

@Entity
@Table
@Data
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, unique = true)
	private String email;
	private String name;
	private String surname;
	private String title;
	private String phoneNumber;
	@Enumerated(EnumType.STRING)
	private ContactTypes type;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime dateCreated;
	private boolean keyContact;
	private String note;
	
	
	@ManyToOne
	private Customer customer;
	@ManyToOne
	private Supplier supplier;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "contact")
	private List<SalesOrder> salesOrders = new ArrayList<SalesOrder>();
	@JsonIgnore
	@OneToMany(mappedBy = "contact")
	private List<SalesOffer> salesOffers = new ArrayList<SalesOffer>();
	
	public String getFullName() {
		return name + " " + surname;
	}
	
	public Contact update(Contact c) {
		this.name = c.name;
		this.surname = c.surname;
		this.title = c.title;
		this.phoneNumber = c.phoneNumber;
		this.type = c.type;
		this.keyContact = c.keyContact;
		this.note = c.note;
		
		return this;
	}
}
