package com.springboot.crud.exception;

public class ResourceNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6002080044094174243L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}
