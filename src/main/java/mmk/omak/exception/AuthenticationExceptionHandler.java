package mmk.omak.exception;


import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;

@RestControllerAdvice
public class AuthenticationExceptionHandler {

	//400
	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleException(BadRequestException ex) {
		return new ResponseEntity<>(new ExceptionResponse(ex), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException ex) {
		return new ResponseEntity<>(new ExceptionResponse(ex), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleException(IllegalStateException ex) {
		return new ResponseEntity<>(new ExceptionResponse(ex), HttpStatus.BAD_REQUEST);
	}
	
	//401
	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleException(BadCredentialsException ex) {
		return new ResponseEntity<>(new ExceptionResponse(ex), HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleException(UnauthorizedUserException ex) {
		return new ResponseEntity<>(new ExceptionResponse(ex), HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleException(UsernameNotFoundException ex) {
		return new ResponseEntity<>(new ExceptionResponse(ex), HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleException(InsufficientAuthenticationException ex) {
		return new ResponseEntity<>(new ExceptionResponse(ex), HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleException(AccountStatusException ex) {
		return new ResponseEntity<>(new ExceptionResponse(ex), HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleException(InvalidCsrfTokenException ex) {
		return new ResponseEntity<>(new ExceptionResponse(ex), HttpStatus.UNAUTHORIZED);
	}
	
	//404
	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleException(ObjectNotFoundException ex) {
		return new ResponseEntity<>(new ExceptionResponse(ex), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleException(NoHandlerFoundException ex) {
		return new ResponseEntity<>(new ExceptionResponse(ex), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleException(SignatureException ex) {
		return new ResponseEntity<>(new ExceptionResponse(ex), HttpStatus.NOT_FOUND);
	}
	
	//406
	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleException(AccessDeniedException ex) {
		return new ResponseEntity<>(new ExceptionResponse(ex), HttpStatus.NOT_ACCEPTABLE);
	}
	
	//410
	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleException(ExpiredJwtException ex) {
		return new ResponseEntity<>(new ExceptionResponse(ex), HttpStatus.GONE);
	}
	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleException(MalformedJwtException ex) {
		return new ResponseEntity<>(new ExceptionResponse(ex), HttpStatus.GONE);
	}
	
	//500
	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
		return new ResponseEntity<>(new ExceptionResponse(ex), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleException(RuntimeException ex) {
		return new ResponseEntity<>(new ExceptionResponse(ex), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//501
	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleException(EncryptingException ex) {
		return new ResponseEntity<>(new ExceptionResponse(ex), HttpStatus.NOT_IMPLEMENTED);
	}
	
	//502
	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleException(HttpRequestMethodNotSupportedException ex) {
		return new ResponseEntity<>(new ExceptionResponse(ex), HttpStatus.BAD_GATEWAY);
	}
	
	
}

