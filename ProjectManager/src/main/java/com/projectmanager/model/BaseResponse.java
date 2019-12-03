package com.projectmanager.model;

/**
 * 
 * @author Ambuj Ojha
 *
 */
public class BaseResponse {

	/** Property for Success **/
	private boolean success;
	
	/** Property for Message **/
	private String message;
	
	/**
	 * Get Success
	 * @return boolean
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Set Success
	 * @param success
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * Get Message
	 * @return String
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Set Message
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
