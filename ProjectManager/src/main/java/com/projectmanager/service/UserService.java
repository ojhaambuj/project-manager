package com.projectmanager.service;

import java.util.List;

import com.projectmanager.exception.ServiceException;
import com.projectmanager.model.BaseResponse;
import com.projectmanager.model.UserDTO;

/**
 * 
 * @author ojhaak
 *
 */
public interface UserService {
	
	/**
	 * Add User
	 * @param userDTO
	 * @return BaseResponse
	 * @throws ServiceException
	 */
	BaseResponse addUser(UserDTO userDTO) throws ServiceException;
	
	/**
	 * Update User
	 * @param userDTO
	 * @return BaseResponse
	 * @throws ServiceException
	 */
	BaseResponse updateUser(UserDTO userDTO) throws ServiceException;
	
	/**
	 * Load All Users
	 * @return List<UserDTO>
	 * @throws ServiceException
	 */
	List<UserDTO> loadUser() throws ServiceException;
	
	/**
	 * Delete User
	 * @param employeeId
	 * @throws ServiceException
	 */
	void deleteUser(String employeeId) throws ServiceException;
	
}
