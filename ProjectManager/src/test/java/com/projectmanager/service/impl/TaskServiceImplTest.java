package com.projectmanager.service.impl;

import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.projectmanager.collection.Task;
import com.projectmanager.dao.TaskDao;
import com.projectmanager.model.TaskDTO;

/**
 * 
 * @author sekarsk
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceImplTest {
	
	@InjectMocks
	private TaskServiceImpl taskServiceImpl;
	
	@Mock
	private TaskDao taskDao;
	
	@Test
	public void addTask01() {
		TaskDTO taskDTO = new TaskDTO();
		try {
			taskServiceImpl.addTask(taskDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void addTask02() {
		TaskDTO taskDTO = new TaskDTO();
		try {
			Task task = new Task();
			task.setTask("12345");
			Optional<Task> optional = Optional.of(task);
			Mockito.when(taskDao.findById("12345")).thenReturn(optional);
			taskServiceImpl.addTask(taskDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void updateTask() {
		TaskDTO taskDTO = new TaskDTO();
		try {
			taskServiceImpl.updateTask(taskDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void loadTasks() {
		List<Task> taskList = new ArrayList<>();
		Task task = new Task();
		task.setTask("12345");
		taskList.add(task);
		try {
			Mockito.when(taskDao.findAll()).thenReturn(taskList);
			taskServiceImpl.loadTasks();
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void deleteTask() {
		String task = "12345";
		try {
			taskServiceImpl.deleteTask(task);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void getTask() {
		String taskName = "12345";
		try {
			Task task = new Task();
			task.setTask("12345");
			Optional<Task> optional = Optional.of(task);
			Mockito.when(taskDao.findById(taskName)).thenReturn(optional);
			taskServiceImpl.getTask(taskName);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
}
