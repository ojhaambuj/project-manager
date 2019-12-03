package com.projectmanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmanager.collection.User;
import com.projectmanager.dao.UserDao;
import com.projectmanager.exception.ServiceException;
import com.projectmanager.model.BaseResponse;
import com.projectmanager.model.UserDTO;
import com.projectmanager.service.UserService;
import com.projectmanager.util.AppConstants;

/**
 * 
 * @author ojhaak
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * Check Employee Id is exist or not
	 * @param employeeId
	 * @return boolean
	 */
	public boolean isUserExist(String employeeId) {
		return userDao.findById(employeeId).isPresent();
	}

	/**
	 * Add User
	 * @param userDTO
	 * @return BaseResponse
	 * @throws ServiceException
	 */
	public BaseResponse addUser(UserDTO userDTO) throws ServiceException {
		BaseResponse response = new BaseResponse();
		if (isUserExist(userDTO.getEmployeeId())) {
			response.setSuccess(false);
			response.setMessage(AppConstants.USER_ALREADY_EXIST_MSG);
		} else {
			User user = new User();
			BeanUtils.copyProperties(userDTO, user);
			userDao.save(user);
			response.setSuccess(true);
			response.setMessage(AppConstants.ADD_USER_SUCCESS_MSG);
		}
		return response;
	}
	
	/**
	 * Update User
	 * @param userDTO
	 * @return BaseResponse
	 * @throws ServiceException
	 */
	public BaseResponse updateUser(UserDTO userDTO) throws ServiceException {
		BaseResponse response = new BaseResponse();
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		userDao.save(user);
		response.setSuccess(true);
		response.setMessage(AppConstants.UPDATE_USER_SUCCESS_MSG);
		return response;
	}

	/**
	 * Load All Users
	 * @return List<UserDTO>
	 * @throws ServiceException
	 */
	@Override
	public List<UserDTO> loadUser() throws ServiceException {
		List<UserDTO> userDTOs = new ArrayList<>();
		List<User> users = userDao.findAll();
		
		for(User user: users) {
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(user, userDTO);
			userDTOs.add(userDTO);
		}
		return userDTOs;
	}

	/**
	 * Delete User
	 * @param employeeId
	 * @throws ServiceException
	 */
	@Override
	public void deleteUser(String employeeId) throws ServiceException {
		userDao.deleteById(employeeId);
	}
	
}
