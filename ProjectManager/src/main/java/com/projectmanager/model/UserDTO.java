package com.projectmanager.model;

/**
 * 
 * @author Ambuj Ojha
 *
 */
public class UserDTO {

	/** Property for First Name **/
	private String firstName;
	
	/** Property for Last Name **/
	private String lastName;
	
	/** Property for Employee ID **/
	private String employeeId;

	/**
	 * Get First Name
	 * @return String
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set First Name
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get Last Name
	 * @return String
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set Last Name
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get Employee ID
	 * @return String
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * Set Employee ID
	 * @param employeeId
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

}
