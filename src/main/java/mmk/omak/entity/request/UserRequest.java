package mmk.omak.entity.request;

import java.util.Set;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mmk.omak.enums.Authorities;
import mmk.omak.enums.Departments;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
	
	private int id;
	private String email;
	private String name;
	private String surname;
	private String password;
	@Enumerated(EnumType.STRING)
	private Set<Authorities> authorities;
	@Builder.Default
	@Enumerated(EnumType.STRING)
	private Departments department = Departments.NONE;
	private String managerEmail;
	
}
