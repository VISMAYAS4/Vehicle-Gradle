package com.project.vm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
/**
 * To check if the user is already existing or not
 *
 */
public class DeletionException extends RuntimeException{

	public DeletionException(String message) {
		super(message);

	}

}
