package com.projectmanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmanager.collection.Project;
import com.projectmanager.collection.Task;
import com.projectmanager.dao.ProjectDao;
import com.projectmanager.dao.TaskDao;
import com.projectmanager.exception.ServiceException;
import com.projectmanager.model.BaseResponse;
import com.projectmanager.model.ProjectDTO;
import com.projectmanager.service.ProjectService;
import com.projectmanager.util.AppConstants;

/**
 * 
 * @author ojhaak
 *
 */
@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private TaskDao taskDao;
	
	/**
	 * Check Project is exist or not
	 * @param project
	 * @return boolean
	 */
	public boolean isProjectExist(String project) {
		return projectDao.findById(project).isPresent();
	}
	
	/**
	 * Add Project
	 * @param projectDTO
	 * @return BaseResponse
	 * @throws ServiceException
	 */
	@Override
	public BaseResponse addProject(ProjectDTO projectDTO) throws ServiceException {
		BaseResponse response = new BaseResponse();
		if (isProjectExist(projectDTO.getProject())) {
			response.setSuccess(false);
			response.setMessage(AppConstants.PROJECT_ALREADY_EXIST_MSG);
		} else {
			Project project = new Project();
			BeanUtils.copyProperties(projectDTO, project);
			projectDao.save(project);
			response.setSuccess(true);
			response.setMessage(AppConstants.ADD_PROJECT_SUCCESS_MSG);
		}
		
		return response;
	}
	
	/**
	 * Update Project
	 * @param projectDTO
	 * @return BaseResponse
	 * @throws ServiceException
	 */
	@Override
	public BaseResponse updateProject(ProjectDTO projectDTO) throws ServiceException {
		BaseResponse response = new BaseResponse();
		Project project = new Project();
		BeanUtils.copyProperties(projectDTO, project);
		projectDao.save(project);
		response.setSuccess(true);
		response.setMessage(AppConstants.UPDATE_PROJECT_SUCCESS_MSG);
		return response;
	}

	/**
	 * Load All Projects
	 * @return List<ProjectDTO>
	 * @throws ServiceException
	 */
	@Override
	public List<ProjectDTO> loadProjects() throws ServiceException {
		List<ProjectDTO> projectDTOs = new ArrayList<>();
		List<Project> projects = projectDao.findAll();
		List<Task> tasks = taskDao.findAll();
		int taskCount;
		int completedCount;
		for(Project project: projects) {
			taskCount = 0;
			completedCount = 0;
			ProjectDTO projectDTO = new ProjectDTO();
			BeanUtils.copyProperties(project, projectDTO);
			for (Task task : tasks) {
				if (projectDTO.getProject() != null
						&& projectDTO.getProject().equals(task.getProject())) {
					taskCount++;
					if (AppConstants.COMPLETED.equals(task.getStatus())) {
						completedCount++;
					}
				}
			}
			projectDTO.setNoOfTasks(taskCount);
			projectDTO.setCompleted(completedCount);
			projectDTOs.add(projectDTO);
		}
		return projectDTOs;
	}

	/**
	 * Delete Project
	 * @param project
	 * @throws ServiceException
	 */
	@Override
	public void deleteProject(String project) throws ServiceException {
		projectDao.deleteById(project);
	}

}
