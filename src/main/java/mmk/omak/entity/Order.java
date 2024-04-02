package mmk.omak.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Order {
	
	private long id;
	private List<Line> offerlines;
	private Customer customer;
	private User salesman;
	private double taxRate;
	private double taxAmount;
	private double amount;
	private double totalAmount;
	private LocalDateTime dateCreated;
	
}
