package mmk.omak.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Contact {
	
	private long id;
	private String name;
	private String surname;
	private String mail;
	private String phoneNumber;
	private LocalDateTime dateCreated;
	
}
