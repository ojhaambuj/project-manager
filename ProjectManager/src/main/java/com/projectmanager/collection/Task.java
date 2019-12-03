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
@Document(collection = "task")
public class Task implements Serializable {

	/**
	 * Property for Serial Version UID
	 */
	private static final long serialVersionUID = -4441064053842740838L;

	/** Property for Task **/
	@Id
	private String task;

	/** Property for Project **/
	private String project;

	/** Property for Parent Task Check Box **/
	private boolean parentTaskCheckBox;

	/** Property for Priority **/
	private String priority;

	/** Property for Parent Task **/
	private String parentTask;

	/** Property for Start Date **/
	private String startDate;

	/** Property for End Date **/
	private String endDate;

	/** Property for User **/
	private String user;
	
	/** Property for Status **/
	private String status;

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
	 * Get Task
	 * @return String
	 */
	public String getTask() {
		return task;
	}

	/**
	 * Set Task
	 * @param task
	 */
	public void setTask(String task) {
		this.task = task;
	}

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
	 * Get Parent Task Check Box
	 * @return boolean
	 */
	public boolean isParentTaskCheckBox() {
		return parentTaskCheckBox;
	}

	/**
	 * Set Parent Task Check Box
	 * @param parentTaskCheckBox
	 */
	public void setParentTaskCheckBox(boolean parentTaskCheckBox) {
		this.parentTaskCheckBox = parentTaskCheckBox;
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
	 * Get Parent Task
	 * @return String
	 */
	public String getParentTask() {
		return parentTask;
	}

	/**
	 * Set Parent Task
	 * @param parentTask
	 */
	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
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
	 * Get User
	 * @return String
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Set User
	 * @param user
	 */
	public void setUser(String user) {
		this.user = user;
	}
	
	/**
	 * Get Status
	 * @return String
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Set Status
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
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
