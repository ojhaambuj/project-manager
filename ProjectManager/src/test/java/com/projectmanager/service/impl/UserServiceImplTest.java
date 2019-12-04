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

import com.projectmanager.collection.User;
import com.projectmanager.dao.UserDao;
import com.projectmanager.model.UserDTO;

/**
 * 
 * @author ojhaak
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private UserDao userDao;
	
	@Test
	public void addUserTC01() {
		UserDTO userDTO = new UserDTO();
		try {
			userServiceImpl.addUser(userDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void addUserTC02() {
		UserDTO userDTO = new UserDTO();
		try {
			User user = new User();
			user.setEmployeeId("12345");
			Optional<User> optional = Optional.of(user);
			Mockito.when(userDao.findById("12345")).thenReturn(optional);
			userServiceImpl.addUser(userDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void updateUser() {
		UserDTO userDTO = new UserDTO();
		try {
			userServiceImpl.updateUser(userDTO);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void loadUser() {
		List<User> userList = new ArrayList<>();
		User user = new User();
		user.setEmployeeId("12345");
		userList.add(user);
		try {
			Mockito.when(userDao.findAll()).thenReturn(userList);
			userServiceImpl.loadUser();
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void deleteUser() {
		String employeeId = "12345";
		try {
			userServiceImpl.deleteUser(employeeId);
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
}
