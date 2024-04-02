package mmk.omak.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Product {
	
	private long id;
	private String name;
	private List<ProductBrand> brand;
	private LocalDateTime dateCreated;
	
}
