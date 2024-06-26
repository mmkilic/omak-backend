package mmk.omak.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class ProductBrand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	
	@OneToMany(mappedBy = "productBrand")
	private List<Line> lines = new ArrayList<Line>();
	
	
	@JsonIgnore
	@ManyToMany(mappedBy = "brands")
	private List<Product> products = new ArrayList<Product>();
}
