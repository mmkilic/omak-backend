package mmk.omak.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Bank {
	
	private long id;
	private String name;
	private String iban;
	private String unit;
	
}
