package com.projectmanager.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanager.exception.ServiceException;
import com.projectmanager.model.BaseResponse;
import com.projectmanager.model.ProjectDTO;
import com.projectmanager.service.ProjectService;
import com.projectmanager.util.AppConstants;

/**
 * 
 * @author Ambuj Ojha
 *
 */
@RestController
public class ProjectController {
	
	/** Property for Project Service **/
	@Autowired
	private ProjectService projectService;
	
	/** Property for Logger **/
	public static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);
	
	/**
	 * Add Project
	 * @param projectDTO
	 * @return BaseResponse
	 */
	@RequestMapping(value = "/addProject", method=RequestMethod.POST)
	public BaseResponse addProject(@RequestBody ProjectDTO projectDTO) {
		LOGGER.info("addProject() adding project");
		BaseResponse response = new BaseResponse();
		try {
			return projectService.addProject(projectDTO);
		} catch (ServiceException e) {
			LOGGER.info(e.getMessage(), e);
			response.setSuccess(false);
			response.setMessage(AppConstants.PROJECT_ERROR_MSG);
			return response;
		}
	}
	
	/**
	 * Update Project
	 * @param projectDTO
	 * @return BaseResponse
	 */
	@RequestMapping(value = "/updateProject", method=RequestMethod.POST)
	public BaseResponse updateProject(@RequestBody ProjectDTO projectDTO) {
		LOGGER.info("updateProject() updating project");
		BaseResponse response = new BaseResponse();
		try {
			return projectService.updateProject(projectDTO);
		} catch (ServiceException e) {
			LOGGER.info(e.getMessage(), e);
			response.setSuccess(false);
			response.setMessage(AppConstants.PROJECT_ERROR_MSG);
			return response;
		}
	}
	
	/**
	 * Load All Projects
	 * @return List<ProjectDTO>
	 */
	@RequestMapping(value = "/loadProject", method=RequestMethod.GET)
	public List<ProjectDTO> loadProject() {
		LOGGER.info("loadProject() fetching data");
		try {
			return projectService.loadProjects();
		} catch (ServiceException e) {
			LOGGER.info(e.getMessage(), e);
			return Collections.singletonList(null);
		}
	}
	
	/**
	 * Delete Project
	 * @param projectDTO
	 */
	@RequestMapping(value = "/deleteProject", method=RequestMethod.POST)
	public void deleteProject(@RequestBody ProjectDTO projectDTO) {
		LOGGER.info("deleteProject() started");
		try {
			projectService.deleteProject(projectDTO.getProject());
		} catch (ServiceException e) {
			LOGGER.info(e.getMessage(), e);
		}
		LOGGER.info("deleteProject() ended");
	}
	
}
