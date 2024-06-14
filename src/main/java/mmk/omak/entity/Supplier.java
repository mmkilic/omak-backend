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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
	private long id;
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
	
	@JsonIgnore
	@OneToMany(mappedBy = "supplier")
	private List<Contact> contacts = new ArrayList<Contact>();
	
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "product_supplier_join", 
			  joinColumns = @JoinColumn(name = "supplier_id"), 
			  inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> products = new ArrayList<Product>();
}
