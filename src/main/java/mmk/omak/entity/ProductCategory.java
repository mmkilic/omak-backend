package mmk.omak.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class ProductCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	
	@JsonIgnore
	@JoinColumn(name="child_id")
	@ManyToOne
	private ProductCategory parent;
	
	
	@JsonIgnore
    @OneToMany(mappedBy="parent")
	private Set<ProductCategory> child = new HashSet<ProductCategory>();
	@JsonIgnore
    @OneToMany(mappedBy="category")
	private List<Product> products = new ArrayList<Product>();
}
