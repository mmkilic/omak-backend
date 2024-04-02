package mmk.omak.entity.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
	private String email;
	private String name;
	private String surname;
	private String managerEmail;
	private String password;
	private String confirmationPassword;
	private String regionalId;
	private String phoneNumber;
}
