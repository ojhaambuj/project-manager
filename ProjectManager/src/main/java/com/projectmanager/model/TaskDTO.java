package com.projectmanager.model;

/**
 * 
 * @author Ambuj Ojha
 *
 */
public class TaskDTO {

	/** Property for Task **/
	private String task;
	
	/** Property for Project **/
	private String project;
	
	/** Property for Parent Task CheckBox **/
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
	 * Get Parent Task CheckBox
	 * @return boolean
	 */
	public boolean isParentTaskCheckBox() {
		return parentTaskCheckBox;
	}

	/**
	 * Set Parent Task CheckBox
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

}
