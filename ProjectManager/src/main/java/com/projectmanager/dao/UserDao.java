package com.projectmanager.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.projectmanager.collection.User;

/**
 * 
 * @author Ambuj Ojha
 *
 */
public interface UserDao extends MongoRepository<User, String> {
		

}
