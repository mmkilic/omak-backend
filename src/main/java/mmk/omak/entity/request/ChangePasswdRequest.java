package mmk.omak.entity.request;

import lombok.Data;

@Data
public class ChangePasswdRequest {
	private String email;
	private String currentPassword;
	private String newPassword;
	private String confirmationPassword;
}
