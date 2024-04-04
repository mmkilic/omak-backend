package mmk.omak.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
	
	//Date
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateCreated;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateDeactivated;
	
	private Types type = Types.NONE;
	
	@JoinColumn(name="manager_id")
	@JsonIgnore
	@ManyToOne
	private User manager;
	@JsonIgnore
    @OneToMany(mappedBy="manager")
	private Set<User> subordinates = new HashSet<User>();
	
	public String getFullName() {
		return name + " " + surname;
	}
	public int getManagerId() {
		if(manager != null)
			return manager.getId();
		return 0;
	}
	public String getManagerFullName() {
		if(manager != null)
			return manager.getFullName();
		return "";
	}
	public String getManagerEmail() {
		if(manager != null)
			return manager.getEmail();
		return "";
	}
	
	@JsonIgnore
	@Override
	public String getUsername() {
		return email;
	}
}
