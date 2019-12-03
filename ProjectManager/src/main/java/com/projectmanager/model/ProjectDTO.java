package com.projectmanager.model;

/**
 * 
 * @author Ambuj Ojha
 *
 */
public class ProjectDTO {

	/** Property for Project **/
	private String project;
	
	/** Property for Project CheckBox **/
	private boolean projectCheckBox;
	
	/** Property for Start Date **/
	private String startDate;
	
	/** Property for End Date **/
	private String endDate;
	
	/** Property for Priority **/
	private String priority;
	
	/** Property for Manager **/
	private String manager;
	
	/** Property for No Of Tasks **/
	private int noOfTasks;
	
	/** Property for Completed **/
	private int completed;

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
	 * Get Project CheckBox
	 * @return boolean
	 */
	public boolean isProjectCheckBox() {
		return projectCheckBox;
	}

	/**
	 * Set Project CheckBox
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
	 * Get No Of Tasks
	 * @return int
	 */
	public int getNoOfTasks() {
		return noOfTasks;
	}

	/**
	 * Set No Of Tasks
	 * @param noOfTasks
	 */
	public void setNoOfTasks(int noOfTasks) {
		this.noOfTasks = noOfTasks;
	}

	/**
	 * Get Completed
	 * @return int
	 */
	public int getCompleted() {
		return completed;
	}

	/**
	 * Set Completed
	 * @param completed
	 */
	public void setCompleted(int completed) {
		this.completed = completed;
	}
	
}
