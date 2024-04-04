package mmk.omak.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class ProductSupplier {
	
	private long id;
	private String name;
	private LocalDateTime dateCreated;
	private List<Contact> contacts;
	private Address address;
	private List<Bank> banks;
	
}
