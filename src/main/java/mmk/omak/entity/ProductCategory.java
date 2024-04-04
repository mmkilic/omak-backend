package mmk.omak.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class ProductCategory {
	
	private long id;
	private String name;
	
	@JsonIgnore
	@JoinColumn(name="child_id")
	@ManyToOne
	private ProductCategory parent;
	@JsonIgnore
    @OneToMany(mappedBy="parent")
	private Set<ProductCategory> child = new HashSet<ProductCategory>();
	
}
