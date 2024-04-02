package mmk.omak.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Case {
	
	private long id;
	private List<Offer> offers;
	private List<Order> orders;
	private User salesman;
	private LocalDateTime dateCreated;
	
	
}
