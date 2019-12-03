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
 * @author ojhaak
 *
 */
@Document(collection = "project")
public class Project implements Serializable {

	/**
	 * Property for Serial Version UID
	 */
	private static final long serialVersionUID = 398297007397555987L;

	/** Property for Project **/
	@Id
	private String project;

	/** Property for Project Check Box**/
	private boolean projectCheckBox;

	/** Property for Start Date **/
	private String startDate;

	/** Property for End Date **/
	private String endDate;

	/** Property for Priority **/
	private String priority;

	/** Property for Manager **/
	private String manager;

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
	 * Get Project
	 * @return String
	 */
	public String getProject() {
		return project;
	}

	/**
	 * Set Project
	 * @param project
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * Get Project Check Box
	 * @return boolean
	 */
	public boolean isProjectCheckBox() {
		return projectCheckBox;
	}

	/**
	 * Set Project Check Box
	 * @param projectCheckBox
	 */
	public void setProjectCheckBox(boolean projectCheckBox) {
		this.projectCheckBox = projectCheckBox;
	}

	/**
	 * Get Start Date
	 * @return String
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * Set Start Date
	 * @param startDate
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * Get End Date
	 * @return String
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * Set End Date
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * Get Priority
	 * @return String
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * Set Priority
	 * @param priority
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * Get Manager
	 * @return String
	 */
	public String getManager() {
		return manager;
	}

	/**
	 * Set Manager
	 * @param manager
	 */
	public void setManager(String manager) {
		this.manager = manager;
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
