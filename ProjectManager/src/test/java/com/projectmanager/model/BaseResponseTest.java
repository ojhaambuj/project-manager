package com.projectmanager.model;

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
public class BaseResponseTest {
	
	private BaseResponse baseResponse = new BaseResponse();
	
	@Test
	public void init() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method[] methods = baseResponse.getClass().getDeclaredMethods();
		if (methods != null && methods.length > 0) {
			for (Method method : methods) {
				if (method.getName().startsWith("is") 
						|| method.getName().startsWith("get")) {
					method.invoke(baseResponse);
				}
				if (method.getName().equals("setSuccess")) {
					method.invoke(baseResponse, true);
				} else if (method.getName().startsWith("set")) {
					method.invoke(baseResponse, method.getDefaultValue());
				}
			}
		}
	}
	
}
