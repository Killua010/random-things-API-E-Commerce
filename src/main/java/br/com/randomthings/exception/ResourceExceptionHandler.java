package br.com.randomthings.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// intercepta exceções de @Controller em toda a aplicação
@ControllerAdvice
public class ResourceExceptionHandler {
	
	// delega a esse metodo um tratamento de uma exceção especifica
	@ExceptionHandler(ObjectNotFoundException.class)// trata erros dessa exceção
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		
	}	
	
	@ExceptionHandler(StrategyValidation.class)
	public ResponseEntity<StandardError> validation(StrategyValidation e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.errors.toString(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		
	}
	
	@ExceptionHandler(UnsupportedFile.class)
	public ResponseEntity<StandardError> unsupportedFile(UnsupportedFile e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(err);
		
	}
}
