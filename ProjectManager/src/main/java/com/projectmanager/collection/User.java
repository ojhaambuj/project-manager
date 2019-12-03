package com.projectmanager.collection;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author ojhaak
 *
 */
@Document(collection = "user")
public class User implements Serializable {

	/**
	 * Property for Serial Version UID
	 */
	private static final long serialVersionUID = -8603627556654023852L;

	/** Property for Employee ID **/
	@Id
	private String employeeId;

	/** Property for First Name **/
	private String firstName;

	/** Property for Last Name **/
	private String lastName;

	/** Property for Created By **/
	@CreatedBy
	private String createdBy;

	/** Property for Created On **/
	@CreatedDate
	private Date createdOn;

	/** Property for Last Updated By **/
	@LastModifiedBy
	private String lastUpdatedBy;

	/** Property for Last Updated On **/
	@LastModifiedDate
	private Date lastUpdatedOn;

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
	 * Get Created By
	 * @return String
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Set Created By
	 * @param createdBy
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Get Created On
	 * @return String
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * Set Created On
	 * @param createdOn
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * Get Last Updated By
	 * @return String
	 */
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	/**
	 * Set Last Updated By
	 * @param lastUpdatedBy
	 */
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	/**
	 * Get Last Updated On
	 * @return String
	 */
	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	/**
	 * Set Last Updated On
	 * @param lastUpdatedOn
	 */
	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

}
