package com.projectmanager.service;

import java.util.List;

import com.projectmanager.exception.ServiceException;
import com.projectmanager.model.BaseResponse;
import com.projectmanager.model.ProjectDTO;

/**
 * 
 * @author ojhaak
 *
 */
public interface ProjectService {
	
	/**
	 * Add Project
	 * @param projectDTO
	 * @return BaseResponse
	 * @throws ServiceException
	 */
	BaseResponse addProject(ProjectDTO projectDTO) throws ServiceException;
	
	/**
	 * Update Project
	 * @param projectDTO
	 * @return BaseResponse
	 * @throws ServiceException
	 */
	BaseResponse updateProject(ProjectDTO projectDTO) throws ServiceException;
	
	/**
	 * Load All Projects
	 * @return List<ProjectDTO>
	 * @throws ServiceException
	 */
	List<ProjectDTO> loadProjects() throws ServiceException;
	
	/**
	 * Delete Project
	 * @param project
	 * @throws ServiceException
	 */
	void deleteProject(String project) throws ServiceException;
	
}
