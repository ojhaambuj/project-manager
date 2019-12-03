package com.projectmanager.service;

import java.util.List;

import com.projectmanager.exception.ServiceException;
import com.projectmanager.model.BaseResponse;
import com.projectmanager.model.TaskDTO;

/**
 * 
 * @author ojhaak
 *
 */
public interface TaskService {
	
	/**
	 * Add Task
	 * @param taskDTO
	 * @return BaseResponse
	 * @throws ServiceException
	 */
	BaseResponse addTask(TaskDTO taskDTO) throws ServiceException;
	
	/**
	 * Update Task
	 * @param taskDTO
	 * @return BaseResponse
	 * @throws ServiceException
	 */
	BaseResponse updateTask(TaskDTO taskDTO) throws ServiceException;
	
	/**
	 * Load All Tasks
	 * @return List<TaskDTO>
	 * @throws ServiceException
	 */
	List<TaskDTO> loadTasks() throws ServiceException;
	
	/**
	 * Delete Task
	 * @param task
	 * @throws ServiceException
	 */
	void deleteTask(String task) throws ServiceException;
	
	/**
	 * Get Task
	 * @param taskName
	 * @return TaskDTO
	 * @throws ServiceException
	 */
	TaskDTO getTask(String taskName) throws ServiceException;
	
}
