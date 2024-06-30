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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.Data;

@Entity
@Table
@TableGenerator(name="tab", initialValue=5_000, allocationSize=1)
@Data
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator="tab")
	private int id;
	@Column(unique = true)
	private String name;
	private String email;
	private String phoneNumber;
	
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateCreated;
	
	private String address;
	private String district;
	private String city;
	private String country;
	private int postCode;
	
	@JsonIgnore
	@OneToMany(mappedBy = "supplier")
	private List<Contact> contacts = new ArrayList<Contact>();
	
	@JsonIgnore
	@ManyToMany(mappedBy = "suppliers")
	private List<Product> products = new ArrayList<Product>();
	
	public Supplier update(Supplier s) {
		this.email = s.email;
		this.phoneNumber = s.phoneNumber;
		this.address = s.address;
		this.district = s.district;
		this.city = s.city;
		this.country = s.country;
		this.postCode = s.postCode;
		
		return this;
	}
}
