package com.example.course.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.course.service.exceptions.ResourceNotFoundException;

@ControllerAdvice //intercepta as exceções que acontecerem para que o objeto possa executar um possivel tratamento
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)// annotation informa que o método resourceNotFound irá interceptar qualquer exceção do tipo ResourceNotFoundException
	// e irá fazer o tratamento conforme o método
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
