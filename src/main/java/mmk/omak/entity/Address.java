package mmk.omak.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Address {
	
	private long id;
	private String address;
	private String district;
	private String city;
	private String country;
	private int postCode;
	
}
