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

import com.projectmanager.collection.Project;
import com.projectmanager.collection.Task;
import com.projectmanager.dao.ProjectDao;
import com.projectmanager.dao.TaskDao;
import com.projectmanager.model.ProjectDTO;
import com.projectmanager.util.AppConstants;

/**
 * 
 * @author AmbujOjha
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceImplTest {
	
	@InjectMocks
	private ProjectServiceImpl projectServiceImpl;
	
	@Mock
	private ProjectDao projectDao;
	
	@Mock
	private TaskDao taskDao;
	
	@Test
	public void addProjectTC01() {
		ProjectDTO projectDTO = new ProjectDTO();
		try {
			projectServiceImpl.addProject(projectDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void addProjectTC02() {
		ProjectDTO projectDTO = new ProjectDTO();
		try {
			Project project = new Project();
			project.setProject("12345");
			Optional<Project> optional = Optional.of(project);
			Mockito.when(projectDao.findById("12345")).thenReturn(optional);
			projectServiceImpl.addProject(projectDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void updateProjectTC01() {
		ProjectDTO projectDTO = new ProjectDTO();
		try {
			projectServiceImpl.updateProject(projectDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void loadProjectsTC01() {
		List<Project> projects = new ArrayList<>();
		Project project = new Project();
		project.setProject("Sample Project");
		projects.add(project);
		
		List<Task> tasks = new ArrayList<>();
		Task task = new Task();
		task.setTask("Sample Task");
		task.setProject("Sample Project");
		task.setStatus(AppConstants.COMPLETED);
		tasks.add(task);
		try {
			Mockito.when(projectDao.findAll()).thenReturn(projects);
			Mockito.when(taskDao.findAll()).thenReturn(tasks);
			projectServiceImpl.loadProjects();
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void deleteProject() {
		String project = "Sample Project";
		try {
			projectServiceImpl.deleteProject(project);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
}
