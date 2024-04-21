package mmk.omak.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import mmk.omak.enums.ProductUnit;

@Entity
@Table
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true, nullable = false)
	private String code;
	private String name;
	private double listPrice;
	@Enumerated(EnumType.STRING)
	private ProductUnit unit;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateCreated;
	
	
	@ManyToOne
	private ProductCategory category;
	@ManyToOne
	private Currency currency;
	
	
	@ManyToMany(mappedBy = "products")
	private List<Supplier> suppliers = new ArrayList<Supplier>();
	@ManyToMany(mappedBy = "products")
	private List<ProductBrand> brands = new ArrayList<ProductBrand>();
}
