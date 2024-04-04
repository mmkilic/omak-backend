package mmk.omak.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Customer {
	
	private long id;
	private String code;
	private String name;
	@Column(unique = true)
	private String taxId;
	private String phoneNumber;
	private String mail;
	private List<Contact> contacts;
	private Address address;
	private LocalDateTime dateCreated;
	private User salesman;
	private List<Bank> banks;
	
}
