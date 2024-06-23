package mmk.omak.entity;

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
import mmk.omak.enums.DeliveryStatus;
import mmk.omak.enums.ReceivingStatus;

@Entity
@Table
@Data
public class Line {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String productCode;
	private String productDescription;
	private byte[] productImage;
	private double quantity;
	private double unitPrice;
	private String duration;
	private double listPrice;
	private double purchasePrice;
	private double factor;
	
	
	@Enumerated(EnumType.STRING)
	private DeliveryStatus deliveryStatus;
	@Enumerated(EnumType.STRING)
	private ReceivingStatus receivingStatus;
	
	
	@ManyToOne
	private ProductBrand productBrand;
	@ManyToOne
	private Currency currency;
	@JsonIgnore
	@ManyToOne
	private SalesOrder salesOrder;
	@JsonIgnore
	@ManyToOne
	private Offer offer;
	
	
	public double getTotalPrice() {
		return quantity * unitPrice;
	}
}
