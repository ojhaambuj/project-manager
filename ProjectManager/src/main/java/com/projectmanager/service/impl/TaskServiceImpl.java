package com.projectmanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmanager.collection.Task;
import com.projectmanager.dao.TaskDao;
import com.projectmanager.exception.ServiceException;
import com.projectmanager.model.BaseResponse;
import com.projectmanager.model.TaskDTO;
import com.projectmanager.service.TaskService;
import com.projectmanager.util.AppConstants;

/**
 * 
 * @author ojhaak
 *
 */
@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskDao taskDao;
	
	/**
	 * Check Task is exist or not
	 * @param task
	 * @return boolean
	 */
	public boolean isTaskExist(String task) {
		return taskDao.findById(task).isPresent();
	}
	
	/**
	 * Add Task
	 * @param taskDTO
	 * @return BaseResponse
	 * @throws ServiceException
	 */
	@Override
	public BaseResponse addTask(TaskDTO taskDTO) throws ServiceException {
		BaseResponse response = new BaseResponse();
		if (isTaskExist(taskDTO.getTask())) {
			response.setSuccess(false);
			response.setMessage(AppConstants.TASK_ALREADY_EXIST_MSG);
		} else {
			Task task = new Task();
			BeanUtils.copyProperties(taskDTO, task);
			taskDao.save(task);
			response.setSuccess(true);
			response.setMessage(AppConstants.ADD_TASK_SUCCESS_MSG);
		}
		return response;
	}
	
	/**
	 * Update Task
	 * @param taskDTO
	 * @return BaseResponse
	 * @throws ServiceException
	 */
	@Override
	public BaseResponse updateTask(TaskDTO taskDTO) throws ServiceException {
		BaseResponse response = new BaseResponse();
		Task task = new Task();
		BeanUtils.copyProperties(taskDTO, task);
		taskDao.save(task);
		response.setSuccess(true);
		response.setMessage(AppConstants.UPDATE_TASK_SUCCESS_MSG);
		return response;
	}

	/**
	 * Load All Tasks
	 * @return List<TaskDTO>
	 * @throws ServiceException
	 */
	@Override
	public List<TaskDTO> loadTasks() throws ServiceException {
		List<TaskDTO> taskDTOs = new ArrayList<>();
		List<Task> tasks = taskDao.findAll();
		
		for(Task task: tasks) {
			TaskDTO taskDTO = new TaskDTO();
			BeanUtils.copyProperties(task, taskDTO);
			taskDTOs.add(taskDTO);
		}
		return taskDTOs;
	}

	/**
	 * Delete Task
	 * @param task
	 * @throws ServiceException
	 */
	@Override
	public void deleteTask(String task) throws ServiceException {
		taskDao.deleteById(task);
	}

	/**
	 * Get Task
	 * @param taskName
	 * @return TaskDTO
	 * @throws ServiceException
	 */
	@Override
	public TaskDTO getTask(String taskName) throws ServiceException {
		Task task = taskDao.findById(taskName).get();
		TaskDTO taskDTO = new TaskDTO();
		BeanUtils.copyProperties(task, taskDTO);
		return taskDTO;
	}

}
