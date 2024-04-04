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
public class Product {
	
	private long id;
	@Column(unique = true)
	private String code;
	private String name;
	private ProductCategory category;
	private List<ProductBrand> brand;
	private List<ProductSupplier> supplier;
	private LocalDateTime dateCreated;
	
}
