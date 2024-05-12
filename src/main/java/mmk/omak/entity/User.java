package mmk.omak.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mmk.omak.entity.request.UserRequest;
import mmk.omak.enums.Authorities;
import mmk.omak.enums.Types;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails{
	private static final long serialVersionUID = -6209670748128028671L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//Credential
	@Column(unique=true, nullable = false)
	private String email;
	private String name;
	private String surname;
	@JsonIgnore
	private String password;
	private boolean enabled;
	@JsonIgnore
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	@JsonIgnore
	private boolean credentialsNonExpired;
	@Enumerated(EnumType.STRING)
	private Set<Authorities> authorities;
	private Types type = Types.NONE;
	
	//Date
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateCreated;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateDeactivated;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "customerOwner")
	private List<Customer> customers = new ArrayList<Customer>();
	@JsonIgnore
	@OneToMany(mappedBy = "salesman")
	private List<Opportunity> opportunities = new ArrayList<Opportunity>();
	@JsonIgnore
	@OneToMany(mappedBy = "salesman")
	private List<SalesOrder> salesOrders = new ArrayList<SalesOrder>();
	@JsonIgnore
	@OneToMany(mappedBy = "salesman")
	private List<Offer> offers = new ArrayList<Offer>();
	
	
	
	public String getFullName() {
		return name + " " + surname;
	}
	
	public User update(UserRequest ur) {
		this.setEmail(ur.getEmail());
		this.setName(ur.getName());
		this.setSurname(ur.getSurname());
		this.setAuthorities(ur.getAuthorities());
		this.setType(ur.getType());
		return this;
	}
	public User update(User u) {
		if(u.getEmail() != null && !u.getEmail().isBlank())
			this.setEmail(u.getEmail());
		if(u.getName() != null && !u.getName().isBlank())
			this.setName(u.getName());
		if(u.getSurname() != null && !u.getSurname().isBlank())
			this.setSurname(u.getSurname());
		this.setEnabled(u.isEnabled());
		//this.setAccountNonExpired(u.isAccountNonExpired());
		this.setAccountNonLocked(u.isAccountNonLocked());
		//this.setCredentialsNonExpired(u.isCredentialsNonExpired());
		if(u.getAuthorities() != null)
			this.setAuthorities(u.getAuthorities());
		if(u.getType() != null)
			this.setType(u.getType());
		return this;
	}
	
	@JsonIgnore
	@Override
	public String getUsername() {
		return email;
	}
}
