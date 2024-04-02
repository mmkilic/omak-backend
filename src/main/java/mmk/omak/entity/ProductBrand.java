package mmk.omak.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class ProductBrand {
	
	private long id;
	private String name;
	private LocalDateTime dateCreated;
	
}
