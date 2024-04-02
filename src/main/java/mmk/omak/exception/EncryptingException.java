package mmk.omak.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
public class EncryptingException extends RuntimeException{
	private static final long serialVersionUID = -2072244877949991929L;
	
	public EncryptingException(String message) {
		super(message);
	}

}
