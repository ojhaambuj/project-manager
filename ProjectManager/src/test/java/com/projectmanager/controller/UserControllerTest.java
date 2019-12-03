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
import com.projectmanager.model.UserDTO;
import com.projectmanager.service.UserService;

/**
 * 
 * @author ambujojha
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	
	@InjectMocks
	private UserController userController;
	
	@Mock
	private UserService userService;
	
	@Test
	public void addUserTC01() {
		UserDTO userDTO = new UserDTO();
		try {
			userController.addUser(userDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void addUserTC02() {
		UserDTO userDTO = new UserDTO();
		try {
			Mockito.doThrow(ServiceException.class).when(userService).addUser(userDTO);
			userController.addUser(userDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void updateUserTC01() {
		UserDTO userDTO = new UserDTO();
		try {
			userController.updateUser(userDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void updateUserTC02() {
		UserDTO userDTO = new UserDTO();
		try {
			Mockito.doThrow(ServiceException.class).when(userService).updateUser(userDTO);
			userController.updateUser(userDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void loadUserTC01() {
		try {
			userController.loadUser();
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void loadUserTC02() {
		try {
			Mockito.doThrow(ServiceException.class).when(userService).loadUser();
			userController.loadUser();
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void deleteUserTC01() {
		UserDTO userDTO = new UserDTO();
		try {
			userController.deleteUser(userDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void deleteUserTC02() {
		UserDTO userDTO = new UserDTO();
		try {
			Mockito.doThrow(ServiceException.class).when(userService).deleteUser(userDTO.getEmployeeId());
			userController.deleteUser(userDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
}
