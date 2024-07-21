package mmk.omak.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import mmk.omak.enums.CostingTypes;

@Entity
@Table
@Data
public class OfferLine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String productCode;
	private String productDescription;
	private double quantity;
	private String duration;
	private double purchasingCost;
	@Enumerated(EnumType.STRING)
	private CostingTypes costingType;
	private double factor;
	
	
	@ManyToOne
	private ProductBrand productBrand;
	@JsonIgnore
	@ManyToOne
	private SalesOffer salesOffer;
	
	
	public double getUnitPrice() {
		return BigDecimal.valueOf(factor).multiply(BigDecimal.valueOf(purchasingCost)).doubleValue();
	}
	public double getTotalPrice() {
		return BigDecimal.valueOf(quantity).multiply(BigDecimal.valueOf(getUnitPrice())).doubleValue();
	}
}
