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
import com.projectmanager.model.ProjectDTO;
import com.projectmanager.service.ProjectService;

/**
 * 
 * @author sekarsk
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectControllerTest {
	
	@InjectMocks
	private ProjectController projectController;
	
	@Mock
	private ProjectService projectService;
	
	@Test
	public void addProjectTC01() {
		ProjectDTO projectDTO = new ProjectDTO();
		try {
			projectController.addProject(projectDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void addProjectTC02() {
		ProjectDTO projectDTO = new ProjectDTO();
		try {
			Mockito.doThrow(ServiceException.class).when(projectService).addProject(projectDTO);
			projectController.addProject(projectDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void updateProjectTC01() {
		ProjectDTO projectDTO = new ProjectDTO();
		try {
			projectController.updateProject(projectDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void updateProjectTC02() {
		ProjectDTO projectDTO = new ProjectDTO();
		try {
			Mockito.doThrow(ServiceException.class).when(projectService).updateProject(projectDTO);
			projectController.updateProject(projectDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void loadProjectTC01() {
		try {
			projectController.loadProject();
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void loadProjectTC02() {
		try {
			Mockito.doThrow(ServiceException.class).when(projectService).loadProjects();
			projectController.loadProject();
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void deleteProjectTC01() {
		ProjectDTO projectDTO = new ProjectDTO();
		try {
			projectController.deleteProject(projectDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void deleteProjectTC02() {
		ProjectDTO projectDTO = new ProjectDTO();
		try {
			Mockito.doThrow(ServiceException.class).when(projectService).deleteProject(projectDTO.getProject());
			projectController.deleteProject(projectDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
}
