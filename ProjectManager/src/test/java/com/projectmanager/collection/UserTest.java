package com.projectmanager.collection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 
 * @author sekarsk
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	
	private User user = new User();

	@Test
	public void init() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method[] methods = user.getClass().getDeclaredMethods();
		if (methods != null && methods.length > 0) {
			for (Method method : methods) {
				if (method.getName().startsWith("get")) {
					method.invoke(user);
				}
				if (method.getName().startsWith("set")) {
					method.invoke(user, method.getDefaultValue());
				}
			}
		}
	}
}
