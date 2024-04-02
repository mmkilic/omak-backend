package mmk.omak.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ExceptionResponse{
	
	private LocalDateTime timestamp;
	private String error;
	private String message;
	private String stackTrace;
	
	public ExceptionResponse(Exception ex) {
		this.timestamp = LocalDateTime.now();
		this.error = ex.getClass().getSimpleName();
		this.message = ex.getMessage();
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		this.stackTrace = sw.toString();
		
		/*this.stackTrace = Arrays.asList(ex.getStackTrace())
							.stream()
							.map(StackTraceElement::toString)
							.collect(Collectors.joining(" \r\n "));*/
	}

}
