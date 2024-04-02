package mmk.omak.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnauthorizedUserException extends RuntimeException{
	private static final long serialVersionUID = -6700099419185504588L;
	
	public UnauthorizedUserException(String message) {
		super(message);
	}
}
