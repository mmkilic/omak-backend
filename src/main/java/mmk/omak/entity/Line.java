package mmk.omak.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Line {
	
	private long id;
	private Product product;
	private ProductBrand brand;
	private LineUnit unit;
	private double quantity;
	private int duration;
	private double basePrice;
	private double purchasePrice;
	private double factor;
	private double unitPrice;
	
}
