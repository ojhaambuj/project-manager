package com.projectmanager.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 
 * @author ojhaak
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDTOTest {
	
	private UserDTO userDTO = new UserDTO();

	@Test
	public void init() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method[] methods = userDTO.getClass().getDeclaredMethods();
		if (methods != null && methods.length > 0) {
			for (Method method : methods) {
				if (method.getName().startsWith("get")) {
					method.invoke(userDTO);
				}
				if (method.getName().startsWith("set")) {
					method.invoke(userDTO, method.getDefaultValue());
				}
			}
		}
	}
	
}
