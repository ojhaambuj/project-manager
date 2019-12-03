package com.projectmanager.controller;

import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.projectmanager.exception.ServiceException;
import com.projectmanager.model.TaskDTO;
import com.projectmanager.service.TaskService;

/**
 * 
 * @author ojhaak
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskControllerTest {
	
	@InjectMocks
	private TaskController taskController;
	
	@Mock
	private TaskService taskService;
	
	@Test
	public void addTaskTC01() {
		TaskDTO taskDTO = new TaskDTO();
		try {
			taskController.addTask(taskDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void addTaskTC02() {
		TaskDTO taskDTO = new TaskDTO();
		try {
			Mockito.doThrow(ServiceException.class).when(taskService).addTask(taskDTO);
			taskController.addTask(taskDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void updateTaskTC01() {
		TaskDTO taskDTO = new TaskDTO();
		try {
			taskController.updateTask(taskDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void updateTaskTC02() {
		TaskDTO taskDTO = new TaskDTO();
		try {
			Mockito.doThrow(ServiceException.class).when(taskService).updateTask(taskDTO);
			taskController.updateTask(taskDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void loadTaskTC01() {
		try {
			taskController.loadTask();
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void loadTaskTC02() {
		try {
			Mockito.doThrow(ServiceException.class).when(taskService).loadTasks();
			taskController.loadTask();
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void deleteTaskTC01() {
		TaskDTO taskDTO = new TaskDTO();
		try {
			taskController.deleteTask(taskDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void deleteTaskTC02() {
		TaskDTO taskDTO = new TaskDTO();
		try {
			Mockito.doThrow(ServiceException.class).when(taskService).deleteTask(taskDTO.getTask());
			taskController.deleteTask(taskDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void getTaskTC01() {
		TaskDTO taskDTO = new TaskDTO();
		try {
			taskController.getTask(taskDTO.getTask());
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void getTaskTC02() {
		TaskDTO taskDTO = new TaskDTO();
		try {
			Mockito.doThrow(ServiceException.class).when(taskService).getTask((taskDTO.getTask()));
			taskController.getTask(taskDTO.getTask());
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
}
