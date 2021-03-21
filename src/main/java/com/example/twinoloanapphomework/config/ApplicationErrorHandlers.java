package com.example.twinoloanapphomework.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationErrorHandlers {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(final MethodArgumentNotValidException ex) {
		final Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			final String field = ((FieldError)error).getField();
			final String message = error.getDefaultMessage();
			errors.put(field, message);
		});

		return errors;
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(Exception.class)
	public Map<String, String> handleOtherExceptions(final Exception ex) {
		final Map<String, String> errors = new HashMap<>();
		errors.put("error", ex.getMessage());
		return errors;
	}
}
