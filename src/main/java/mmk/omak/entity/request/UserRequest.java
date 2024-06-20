package mmk.omak.entity.request;

import java.util.Set;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mmk.omak.enums.Authorities;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
	
	private int id;
	private String email;
	private String name;
	private String surname;
	@Enumerated(EnumType.STRING)
	private Set<Authorities> authorities;
	
}
