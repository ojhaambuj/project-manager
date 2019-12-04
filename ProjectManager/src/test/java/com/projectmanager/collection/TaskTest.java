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
public class TaskTest {
	
	private Task task = new Task();
	
	@Test
	public void init() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method[] methods = task.getClass().getDeclaredMethods();
		if (methods != null && methods.length > 0) {
			for (Method method : methods) {
				if (method.getName().startsWith("is") 
						|| method.getName().startsWith("get")) {
					method.invoke(task);
				}
				if (method.getName().equals("setParentTaskCheckBox")) {
					method.invoke(task, true);
				} else if (method.getName().startsWith("set")) {
					method.invoke(task, method.getDefaultValue());
				}
			}
		}
	}
	
}
