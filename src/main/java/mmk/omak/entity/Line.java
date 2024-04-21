package mmk.omak.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
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
	private double quantity;
	private double unitPrice;
	@Transient
	@Setter(AccessLevel.NONE)
	private double totalPrice;
	private String duration;
	private double listPrice;
	private double purchasePrice;
	private double factor;
	
	
	@Enumerated(EnumType.STRING)
	private DeliveryStatus deliveryStatus;
	@Enumerated(EnumType.STRING)
	private ReceivingStatus reveivingStatus;
	
	
	@ManyToOne
	private ProductBrand productBrand;
	@ManyToOne
	private Currency currency;
	@ManyToOne
	private SalesOrder salesOrder;
	@ManyToOne
	private Offer offer;
	
	
	public double getTotalPrice() {
		return quantity * unitPrice;
	}
}
