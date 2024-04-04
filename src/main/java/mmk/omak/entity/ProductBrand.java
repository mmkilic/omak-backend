package mmk.omak.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import mmk.omak.enums.Origins;

@Entity
@Table
@Data
public class ProductBrand {
	
	private long id;
	private String name;
	private Origins origin;
	
}
