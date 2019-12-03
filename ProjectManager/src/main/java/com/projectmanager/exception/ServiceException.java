package com.projectmanager.exception;

/**
 * 
 * @author Ambuj Ojha
 *
 */
public class ServiceException extends Exception {

	/**
	 * Property for Serial Version ID
	 */
	private static final long serialVersionUID = 5286498866263615038L;

	/**
	 * Instantiates a new Service exception.
	 */
	public ServiceException() {
		super();
	}

	/**
	 * Instantiates a new Service exception.
	 * 
	 * @param message the message
	 * @param cause the cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new Service exception.
	 * 
	 * @param message the message
	 */
	public ServiceException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new Service exception.
	 * 
	 * @param cause the cause
	 */
	public ServiceException(Throwable cause) {
		super(cause);
	}

}
