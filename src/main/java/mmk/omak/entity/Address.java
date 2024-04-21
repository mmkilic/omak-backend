package mmk.omak.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String address;
	private String district;
	private String city;
	private String country;
	private int postCode;
	
	
	@ManyToOne
	private Customer customer;
	@ManyToOne
	private Supplier supplier;
	
}
