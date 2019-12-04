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
public class ProjectTest {
	
	private Project project = new Project();
	
	@Test
	public void init() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method[] methods = project.getClass().getDeclaredMethods();
		if (methods != null && methods.length > 0) {
			for (Method method : methods) {
				if (method.getName().startsWith("is") 
						|| method.getName().startsWith("get")) {
					method.invoke(project);
				}
				if (method.getName().equals("setProjectCheckBox")) {
					method.invoke(project, true);
				} else if (method.getName().startsWith("set")) {
					method.invoke(project, method.getDefaultValue());
				}
			}
		}
	}
}
