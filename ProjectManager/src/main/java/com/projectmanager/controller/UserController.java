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
import com.projectmanager.model.UserDTO;
import com.projectmanager.service.UserService;
import com.projectmanager.util.AppConstants;

/**
 * 
 * @author Ambuj Ojha
 *
 */
@RestController
public class UserController {
	
	/** Property for User Service **/
	@Autowired
	private UserService userService;
	
	/** Property for Logger **/
	public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	/**
	 * Add User
	 * @param user
	 * @return BaseResponse
	 */
	@RequestMapping(value = "/addUser", method=RequestMethod.POST)
	public BaseResponse addUser(@RequestBody UserDTO user) {
		LOGGER.info("addUser() adding user");
		BaseResponse response = new BaseResponse();
		try {
			return userService.addUser(user);
		} catch (ServiceException e) {
			LOGGER.info(e.getMessage(), e);
			response.setSuccess(false);
			response.setMessage(AppConstants.USER_ERROR_MSG);
			return response;
		}
	}
	
	/**
	 * Update User
	 * @param user
	 * @return BaseResponse
	 */
	@RequestMapping(value = "/updateUser", method=RequestMethod.POST)
	public BaseResponse updateUser(@RequestBody UserDTO user) {
		LOGGER.info("updateUser() updating user");
		BaseResponse response = new BaseResponse();
		try {
			return userService.updateUser(user);
		} catch (ServiceException e) {
			LOGGER.info(e.getMessage(), e);
			response.setSuccess(false);
			response.setMessage(AppConstants.USER_ERROR_MSG);
			return response;
		}
	}
	
	/**
	 * Load All Users
	 * @return List<UserDTO>
	 */
	@RequestMapping(value = "/loadUser", method=RequestMethod.GET)
	public List<UserDTO> loadUser() {
		LOGGER.info("loadUser() fetching all user data");
		try {
			return userService.loadUser();
		} catch (ServiceException e) {
			LOGGER.info(e.getMessage(), e);
			return Collections.singletonList(null);
		}
	}
	
	/**
	 * Delete User
	 * @param user
	 */
	@RequestMapping(value = "/deleteUser", method=RequestMethod.POST)
	public void deleteUser(@RequestBody UserDTO user) {
		LOGGER.info("deleteUser() started");
		try {
			userService.deleteUser(user.getEmployeeId());
		} catch (ServiceException e) {
			LOGGER.info(e.getMessage(), e);
		}
		LOGGER.info("deleteUser() ended");
	}
}
