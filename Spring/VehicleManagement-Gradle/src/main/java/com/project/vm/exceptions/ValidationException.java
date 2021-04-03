package com.project.vm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
/**
 * For validating if user's entered detail is in specific pattern or not as specified
 *
 */
public class ValidationException extends RuntimeException {

	public ValidationException(String message) {
		super(message);
	
	}
}