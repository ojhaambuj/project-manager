package com.projectmanager.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanager.exception.ServiceException;
import com.projectmanager.model.BaseResponse;
import com.projectmanager.model.TaskDTO;
import com.projectmanager.service.TaskService;
import com.projectmanager.util.AppConstants;

/**
 * 
 * @author Ambuj Ojha
 *
 */
@RestController
public class TaskController {
	
	/** Property for Task Service **/
	@Autowired
	private TaskService taskService;
	
	/** Property for Logger **/
	public static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);
	
	/**
	 * Add Task
	 * @param taskDTO
	 * @return BaseResponse
	 */
	@RequestMapping(value = "/addTask", method=RequestMethod.POST)
	public BaseResponse addTask(@RequestBody TaskDTO taskDTO) {
		LOGGER.info("addTask() adding task");
		BaseResponse response = new BaseResponse();
		try {
			return taskService.addTask(taskDTO);
		} catch (ServiceException e) {
			LOGGER.info(e.getMessage(), e);
			response.setSuccess(false);
			response.setMessage(AppConstants.TASK_ERROR_MSG);
			return response;
		}
	}
	
	/**
	 * Update Task
	 * @param taskDTO
	 * @return BaseResponse
	 */
	@RequestMapping(value = "/updateTask", method=RequestMethod.POST)
	public BaseResponse updateTask(@RequestBody TaskDTO taskDTO) {
		LOGGER.info("updateTask() updating task");
		BaseResponse response = new BaseResponse();
		try {
			return taskService.updateTask(taskDTO);
		} catch (ServiceException e) {
			LOGGER.info(e.getMessage(), e);
			response.setSuccess(false);
			response.setMessage(AppConstants.TASK_ERROR_MSG);
			return response;
		}
	}
	
	/**
	 * Load All Tasks
	 * @return List<TaskDTO>
	 */
	@RequestMapping(value = "/loadTask", method=RequestMethod.GET)
	public List<TaskDTO> loadTask() {
		LOGGER.info("loadTask() fetching task");
		try {
			return taskService.loadTasks();
		} catch (ServiceException e) {
			LOGGER.info(e.getMessage(), e);
			return Collections.singletonList(null);
		}
	}
	
	/**
	 * Delete Task
	 * @param taskDTO
	 */
	@RequestMapping(value = "/deleteTask", method=RequestMethod.POST)
	public void deleteTask(@RequestBody TaskDTO taskDTO) {
		LOGGER.info("deleteTask() started");
		try {
			taskService.deleteTask(taskDTO.getTask());
		} catch (ServiceException e) {
			LOGGER.info(e.getMessage(), e);
		}
		LOGGER.info("deleteTask() ended");
	}
	
	/**
	 * Get Task
	 * @param task
	 * @return TaskDTO
	 */
	@RequestMapping(value = "/getTask", method=RequestMethod.GET)
	public TaskDTO getTask(@RequestParam String task) {
		LOGGER.info("getTask() Get Task by Id");
		try {
			return taskService.getTask(task);
		} catch (ServiceException e) {
			LOGGER.info(e.getMessage(), e);
			return new TaskDTO();
		}
	}

}
